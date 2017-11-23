package com.leimingtech.service.module.goods.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leimingtech.core.common.Encodes;
import com.leimingtech.core.common.StringUtils;
import com.leimingtech.core.entity.GoodsSpec;
import com.leimingtech.core.entity.base.GoodsPlatform;
import com.leimingtech.core.jackson.JsonUtils;
import com.leimingtech.service.module.goods.dao.GoodsPlatformDao;
import com.leimingtech.service.module.goods.service.GoodsPlatformService;
import com.leimingtech.service.module.goods.service.GoodsSpecService;
import com.leimingtech.service.utils.ImageSetUtils;
import com.leimingtech.service.utils.page.Pager;

/**
 * 平台商品库
 *  Created by young on 2016/1/27.
 */
@Service
public class GoodsPlatformServiceImpl implements GoodsPlatformService{

    @Autowired
    private GoodsPlatformDao goodsPlatformDao;
    @Autowired
    private GoodsSpecService goodsSpecService;

    /**
     * 通过goodsid查找goods
     *
     * @param goodsId
     * @return
     */
    @Override
    public GoodsPlatform findGoodsById(String goodsId) {
        return goodsPlatformDao.findGoodsById(goodsId);
    }

    /**
     * 分页查询获得list
     *
     * @param pager
     * @return
     */
    @Override
    public Pager findGoodsPagerList(Pager pager) {
    	List<GoodsPlatform> list=goodsPlatformDao.findGoodsPagerList(pager);
		pager.setResult(list);
		return pager;
    }

    /**
     * 保存
     *
     * @param goods
     */
    @Override
    public String saveGoods(GoodsPlatform goods,String goodsSpecJson) {
        goods = enGoods(goods);
        goods.setCreateTime(System.currentTimeMillis());
        goodsPlatformDao.saveGoods(goods);
        //保存至goodsspec
        saveToGoodsSpec(goods, goodsSpecJson);
        String goodsId = goods.getGoodsId();
        // 生成缩略图
        String imagesMore = goods.getGoodsImageMore();
        if(StringUtils.isNotEmpty(imagesMore)){
            ImageSetUtils.setImages(imagesMore);
        }
        return goodsId;
    }

    /**
     * 对商品的个别字段进行解码
     * @param goods
     * @return
     */
    private GoodsPlatform enGoods(GoodsPlatform goods){
        goods.setSpecName(Encodes.unescapeHtml(goods.getSpecName()));
        goods.setGoodsBody(Encodes.unescapeHtml(goods.getGoodsBody()));
        goods.setGoodsSpec(Encodes.unescapeHtml(goods.getGoodsSpec()));
        goods.setGoodsAttr(Encodes.unescapeHtml(goods.getGoodsAttr()));
        goods.setGoodsColImg(Encodes.unescapeHtml(goods.getGoodsColImg()));
        return goods;
    }

    /**
     * 修改商品
     *
     * @param goods
     */
    @Override
    public void updateGoods(GoodsPlatform goods) {
        //收藏商品时更新商品的收藏数量
        goodsPlatformDao.updateGoods(goods);
        goods = enGoods(goods);
        goods.setUpdateTime(System.currentTimeMillis());
        goodsPlatformDao.updateGoods(goods);
    }

    /**
     * 删除商品
     *
     * @param goodsId
     */
    @Override
    public void deleteGoods(String goodsId) {
        goodsPlatformDao.deleteGoods(goodsId);
    }

    /**
     * 通过一定条件的条件,查找某个商品,
     * 这个方法只会返回一个商品,
     * 使用方法:
     * 新建一个goods对象,在这个对象中
     * 一定要设置goodsid这个属性
     * 可以选择set属性:storeId,goodsState
     * 使用这个方法会根据你所设置的条件去查询,
     * 如果没有返回null
     *
     * @param goods
     */
    @Override
    public GoodsPlatform findGoodsByCondition(GoodsPlatform goods) {
        return goodsPlatformDao.findGoodsByCondition(goods);
    }

    /**
     * 根据商品字段获取商品的数量
     *
     * @param goods
     * @return
     */
    @Override
    public int countGoods(GoodsPlatform goods) {
        goods.setCreateTime(System.currentTimeMillis());
        return goodsPlatformDao.countGoods(goods);
    }


    //存入goodsspec表
    private void saveToGoodsSpec(GoodsPlatform goods, String goodsSpecJson){
        //在保存之前首先删除goodsSpec表中关于这个goodsId的数据
        goodsSpecService.deleteGoodsSpecByGoodsId(goods.getGoodsId());
        if(goodsSpecJson != null && !goodsSpecJson.trim().equals("")){
            //准备创建表shop_goods_sepc的实体类对象
            List<Object> goodsSpecs = JsonUtils.readJsonList(goodsSpecJson, GoodsSpec.class);
            //循环获得goodsspec
            for(int i = 0; i < goodsSpecs.size(); i++){
                GoodsSpec goodsSpec = (GoodsSpec) goodsSpecs.get(i);
                //设置商品id
                goodsSpec.setGoodsId(goods.getGoodsId());
                //设置销售量为0
                goodsSpec.setSpecSalenum(0);
                //保存goodsspecs
                goodsSpecService.saveGoodsSpec(goodsSpec);
                if(i == 0){
                    //设置商品specid
                    goods.setSpecId(goodsSpec.getGoodsSpecId());
                }
            }
        }else{
            GoodsSpec goodsSpec = new GoodsSpec();
            //设置商品id
            goodsSpec.setGoodsId(goods.getGoodsId());
            //价格
            goodsSpec.setSpecGoodsPrice(goods.getGoodsStorePrice());
            //库存
            goodsSpec.setSpecGoodsStorage(goods.getGoodsTotalStorage());
            //货号
            goodsSpec.setSpecGoodsSerial(goods.getGoodsSerial());
            //设置销售量为0
            goodsSpec.setSpecSalenum(0);
            //保存goodsspecs
            goodsSpecService.saveGoodsSpec(goodsSpec);
            //设置商品specid
            goods.setSpecId(goodsSpec.getGoodsSpecId());
        }

        //再次修改goods表
        //判断是否存有goodsspec
        if(goods.getSpecId() != null){
            GoodsPlatform tagGoods = new GoodsPlatform();
            tagGoods.setGoodsId(goods.getGoodsId());
            tagGoods.setSpecId(goods.getSpecId());
            updateGoods(goods);
        }
    }
}

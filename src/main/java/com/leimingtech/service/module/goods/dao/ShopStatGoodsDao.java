package com.leimingtech.service.module.goods.dao;

import com.leimingtech.core.entity.GoodsGeneralCount;
import com.leimingtech.core.entity.GoodsStatCount;
import com.leimingtech.core.entity.base.Brand;
import com.leimingtech.core.entity.base.ShopStatGoods;
import com.leimingtech.service.utils.page.Pager;

import java.util.List;
import java.util.Map;

/**
 * @项目名称：leimingtech-seller
 * @类名称：ShopStatGoodsDao
 * @类描述：
 * @创建人：gyh
 * @创建时间：2015年7月23日 下午16:22:28
 * @修改备注：
 */
public interface ShopStatGoodsDao {
    /**
     * 保存商品记录
     *
     * @param shopStatGoods
     */
    void save(ShopStatGoods shopStatGoods);

    /**
     * 删除品牌
     *
     * @param sId
     */
    void delete(String sId);

    /**
     * 获取条数
     *
     * @param pager
     * @return
     */
    int findCount(Pager pager);

    /**
     * 获取分页数据
     *
     * @param pager
     * @return
     */
    List<ShopStatGoods> findPageList(Pager pager);

    /**
     * 获取全部数据
     *
     * @return
     */
    List<ShopStatGoods> findList();

    /**
     * 根据id商品浏览记录
     *
     * @param shopStatGoods
     * @return
     */
    Brand findbyIds(ShopStatGoods shopStatGoods);

    /**
     * 时间段，店铺id查找内容
     *
     * @param map
     * @return
     */
    List<GoodsGeneralCount> findStatbytime(Map<String, Object> map);
}

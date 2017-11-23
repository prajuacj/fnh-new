package com.leimingtech.service.module.trade.dao.mapper;

import java.math.BigDecimal;
import java.util.List;

import com.leimingtech.core.entity.base.EvaluateGoods;
import com.leimingtech.core.orm.mybatis.SqlMapper;
import com.leimingtech.service.utils.page.Pager;

/**
 * @author llf
 * @Package com.leimingtech.admin.module.trade.dao.mapper
 * @Description:
 * @date 2014/11/12 11:12
 */

@SqlMapper
public interface EvaluateGoodsMapper {

//    List<EvaluateGoods> findPageList(Map<String,Object> map);
	
	public List<EvaluateGoods> findPageList(Pager pager);

    int findCount(EvaluateGoods evaluateGoods);

    void delete(String id);
    
    void updateById(EvaluateGoods evaluateGoods);
    
    /**
     * 保存数据
     * @param evaluateGoods
     */
    public void saveEvaluate(EvaluateGoods evaluateGoods);
    
    /**
     * 获得某个商品的平均分数
     * @param goodsId
     */
    public BigDecimal getAverageScoreByGooodsId(String goodsId);
}

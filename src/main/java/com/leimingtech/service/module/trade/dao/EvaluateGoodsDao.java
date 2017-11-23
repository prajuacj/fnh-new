package com.leimingtech.service.module.trade.dao;


import com.leimingtech.core.entity.base.EvaluateGoods;
import com.leimingtech.service.utils.page.Pager;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author llf
 * @Package com.leimingtech.admin.module.trade.dao
 * @Description:
 * @date 2014/11/12 13:00
 */
public interface EvaluateGoodsDao {

    List<EvaluateGoods> findPageList(Pager pager);

    /**
     * 总条数
     *
     * @param evaluateGoods
     * @return
     */
    int findCount(EvaluateGoods evaluateGoods);

    /**
     * 删除
     *
     * @param id
     */
    void delete(String id);


    void updateById(EvaluateGoods evaluateGoods);

    /**
     * 保存数据
     *
     * @param evaluateGoods
     */
    void saveEvaluate(EvaluateGoods evaluateGoods);

    /**
     * 获得某个商品的平均分数
     *
     * @param goodsId
     */
    BigDecimal getAverageScoreByGooodsId(String goodsId);
}

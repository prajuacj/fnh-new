package com.leimingtech.service.module.submall.dao.mapper;

import java.util.List;
import java.util.Map;

import com.leimingtech.core.entity.base.Goods;
import com.leimingtech.core.entity.base.GoodsCombinHaoHuo;
import com.leimingtech.core.entity.base.GoodsCombinRelation;
import com.leimingtech.core.entity.vo.IndexHaohuoGoodsVo;
import com.leimingtech.core.orm.mybatis.SqlMapper;
import com.leimingtech.service.utils.page.Pager;

@SqlMapper
public interface GoodsCombinHaohuoMapper {
	
	List<GoodsCombinHaoHuo> queryHaohuo();
	
	GoodsCombinHaoHuo queryHaohuoById(String combinCode);
	
	List<Goods> queryGoodsByHaohuoId(String combinCode);
	
	List<Goods> queryGoodsByHaohuoIdAndLimit6(String combinCode);
	
	List<GoodsCombinHaoHuo> queryHaohuoByIndex();
	
	List<IndexHaohuoGoodsVo> queryHaohuoAndGoodsByIndex();
	
	List<GoodsCombinHaoHuo> queryHaohuoByPager(Pager pager);
	
	int savaHaohuo(GoodsCombinHaoHuo goodsCombinHaoHuo);
	
	int deleteHaohuoById(String code);
	
	int deleteHaohuoRelationById(String code);
	
	List<Goods> queryGoodsByPager(Pager pager);
	
	int saveHaohuoGoods(List<GoodsCombinRelation> addList);
	
	int deleteHaohuoGoods(Map<String,String> paramMap);
}

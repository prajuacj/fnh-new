package com.gofar.leiming.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.leimingtech.core.entity.base.Goods;
import com.leimingtech.service.module.goods.service.GoodsService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:context/applicationContext.xml")
public class GoodsTest {
	
	@Resource
	private GoodsService goodsService;
	
	@Test
	public void testFindGoodById(){
		String goodsId="023594a0de384d11819a4a40500f70af";
		
		Goods goods=goodsService.findGoodById(goodsId);
	}
}

package com.leimingtech.service.module.cart.service.impl;

import com.leimingtech.core.common.NumberUtils;
import com.leimingtech.core.entity.GoodsClass;
import com.leimingtech.core.entity.GoodsSpec;
import com.leimingtech.core.entity.Transport;
import com.leimingtech.core.entity.base.*;
import com.leimingtech.core.entity.searchbean.CouponSearch;
import com.leimingtech.core.entity.vo.CartCouponVo;
import com.leimingtech.core.entity.vo.CartVo;
import com.leimingtech.core.entity.vo.OrderVo;
import com.leimingtech.core.state.goods.GoodsState;
import com.leimingtech.service.module.cart.dao.CartDao;
import com.leimingtech.service.module.cart.service.CartService;
import com.leimingtech.service.module.coupon.service.CouponMemberService;
import com.leimingtech.service.module.coupon.service.CouponService;
import com.leimingtech.service.module.goods.service.GoodsClassService;
import com.leimingtech.service.module.goods.service.GoodsService;
import com.leimingtech.service.module.goods.service.GoodsSpecService;
import com.leimingtech.service.module.member.service.MemberService;
import com.leimingtech.service.module.promote.service.PromoteService;
import com.leimingtech.service.module.store.service.StoreService;
import com.leimingtech.service.module.trade.service.TransportService;
import com.leimingtech.service.utils.goods.GoodsUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

@Service
@Slf4j
public class CartServiceImpl implements CartService {

	@Resource
	private CartDao cartDao;
	
	@Resource
	private GoodsSpecService goodsSpecService;
	
	@Resource
	private TransportService transportService;
	
	@Resource
    private CouponMemberService couponMemberService;
	
	@Resource
	private CouponService couponService;
	
	@Resource 
	private GoodsService goodsService;
	
	@Resource
	private MemberService memberService;
	
	@Resource
	private StoreService storeService;
	
	@Resource
	private PromoteService promoteService;
	
	@Resource
	private GoodsClassService goodsClassService;
	
	/**
	 * 通过用户id查询购物车
	 * @param memberId 用户id
	 * @return
	 */
	public List<Cart> queryBuyCart(String memberId) {
		Cart cart = new Cart();
		cart.setMemberId(memberId);
		List<Cart> list = cartDao.queryBuyCart(cart);
		return list;
	}

	/**
	 * 保存购物车
	 * @param goodsId 商品id
	 * @param memberId 用户id
	 * @param count 商品数量
	 * @param goodSpecId 商品规格id
	 * @param saveType 加入类型(加入购物车:0/立即购买:1)
	 * @return
	 */
	public String saveCart(String goodsId,String memberId,Integer count,String goodSpecId,Integer saveType) {
		Goods goods = goodsService.findGoodById(goodsId); //商品信息
        GoodsSpec goodsSpec = goodsSpecService.findByGoodsSpecId(goodSpecId); //商品规格信息
		GoodsClass goodsClass = goodsClassService.findById(goods.getGcId()); //通过商品分类id查询商品所在分类
        //新建一个商品规格,通过GoodsUtils.getSepcMapAndColImgToGoodsSpec方法查询出规格的图片和值
		GoodsSpec spec = GoodsUtils.getSepcMapAndColImgToGoodsSpec(goods,goodsSpec);
		
		//返回值默认为0,返回0的时候加入商品总数量大于100,并且将商品数量修改为100,返回-1,购买本店铺下的商品
		String result = "0";
		/**
		 * 判断是否购买自己店铺的商品
		 */
		Store store = storeService.findById(goods.getStoreId());
		if(store!=null){
			if(store.getMemberId().equals(memberId)){ //购买自己店铺下的商品
				result = "-1";
				return result;
			}
		}
		if(!result.equals("-1")) {
			Cart cart = new Cart();
			cart.setGoodsId(goods.getGoodsId());
			cart.setMemberId(memberId);
			if (StringUtils.isNotEmpty(spec.getColImg()) && !"null".equals(spec.getColImg())) {
				cart.setGoodsImages(spec.getColImg());
			} else {
				if (goods.getGoodsImage() != null) {
					//存储商品默认图片
					cart.setGoodsImages(goods.getGoodsImage().split(",")[0]);
				} else {
					//若商品没有默认图片存储空字段
					cart.setGoodsImages("");
				}
			}
			cart.setGoodsName(goods.getGoodsName());
			cart.setGoodsPrice(goodsSpec.getSpecGoodsPrice().doubleValue());
			cart.setStoreId(goods.getStoreId());
			cart.setStoreName(goods.getStoreName());
			cart.setSpecId(spec.getGoodsSpecId());
			//获取分类的层级path
			String[] gcIds = goodsClass.getGcIdpath().split(",");
			cart.setFirstGcId(gcIds[0]); //将商品所在分类的一级分类的id存入购物车
			//新建一个字段存储新的规格格式
			String specInfo = "";
			//遍历规格map,取出键值对,拼接specInfo
			Map<String, String> map = spec.getSepcMap();
			if (map != null) {
				Set<String> set = map.keySet();
				for (String str : set) {
					specInfo += str + ":" + map.get(str) + "&nbsp;";
				}
			}
			cart.setSpecInfo(specInfo);

			try {
				List<Cart> list = cartDao.queryBuyCart(cart); //判断是否存在相同购物车信息,包括商品id,用户id,和规格id
				if (list != null && list.size() > 0) {//如果有数据  就更新 否则就添加
					for (Cart carts : list) {
						Integer num = 0;
						//判断是否为立即购买
						if (saveType == 1) {  //若为立即购买,直接将购物车数量更改为当前购买数量
							num = count;
						} else { //若为加入购物车,将原有商品数量加上新加入的商品数量
							num = carts.getGoodsNum().intValue() + count;
						}
						//加入购物车同一商品数量不能超过100
						if (num <= 100) {
							cart.setGoodsNum(num.shortValue());
						} else {//若超过100将返回0
							return result;
						}
						cart.setCartId(carts.getCartId());
						cartDao.updateCart(cart);
					}
					result = cart.getCartId();
				} else {
					cart.setGoodsNum(count.shortValue());
					cartDao.saveCart(cart);
					result = cart.getCartId();
				}
			} catch (Exception e) {
				log.error("加入购物车异常" + e.getMessage());
			}
		}
		return result;
	}

	
	/**
	 * 删除购物车 
	 * @param cartId 购物车id
	 * @return
	 */
	@Override
	public int deleteCart(String cartId) {
		int result = 0;
		try {
			cartDao.deleteCart(cartId);
			result = 1;
		} catch (Exception e) {
			log.error("删除购物车异常"+e.getMessage());
		}
		return result;
	}
	
	/**
	 * 根据用户id,商品id,商品规格id删除购物车
	 * @param memberId 用户id
	 * @param goodsId 商品id
	 * @param specId 规格id
	 */
	@Override
	public void deleteByMGS(String memberId, String goodsId, String specId) {
		cartDao.deleteByMGS(memberId, goodsId, specId);
	}

	

	/**
	 * 修改购物车数量
	 * @param cartId 购物车id
	 * @param count 商品数量
	 * @return 返回int类型,1为成功,0为失败
	 */
	@Override
	public int updatecart(String cartId, Integer count) {
		int result = 0;
		try {
			Cart cart = cartDao.queryCartById(cartId);
			if(cart!=null){
				cart.setCartId(cartId);
				cart.setGoodsNum(count.shortValue());
				cartDao.updateCartNum(cart);
				result = 1;
			}
		} catch (Exception e) {
			log.error("更新购物车异常"+e.getMessage());
		}
		return result;
	}

	/**
	 * 购物车下单  根据用户id和cartids 查询商品 根据店铺id,和商品一级分类id排序
	 * @param cartIds 需提供购物车id(可多条,用","隔开),用户id
	 * @param memberId 用户id
	 * @return
	 */
	@Override
	public List<Cart> queryCartByIds(String cartIds,String memberId) {

		cartIds = "'"+cartIds.replaceAll(",", "','")+"'";
		Cart cart = new Cart();
		cart.setCartIds(cartIds);
		cart.setMemberId(memberId);
		List<Cart> list = cartDao.queryByCartIds(cart);
		return list;
	}
	
	/**
	 * 根据cartid 查询商品
	 * @Title: queryCartById 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param cart
	 * @param @return    设定文件 
	 * @return List<Cart>    返回类型 
	 * @throws
	 */
	public Cart queryCartById(String cartId){
		return cartDao.queryCartById(cartId);
	}
	
	/**
	 * 批量查询购物车,不支持分单
	 * @param cartIds 多个购物车id,多个之间以逗号隔开
	 * @param memberId 用户id
	 * @return 返回一个CartVo,购物车集合保存在cartVo中
	 */
	public CartVo queryCartByCartIds(String cartIds,String memberId){
		CartVo cartVo = new CartVo();
		if(StringUtils.isNotEmpty(cartIds) && !"null".equals(cartIds)){
			String[] cartId = cartIds.split(",");
			List<Cart> cartList = new ArrayList<Cart>();
			int goodsNum = 0; //商品总数量
            double goodsPrice = 0.0; //商品总价
            String storeName = "";
            //新建一个字符串,用来存储多个id字段
			String ids = "";
            //遍历购物车id数组
			for(String id : cartId){
				Cart cart = cartDao.queryCartById(id);
				//更新cartVo店铺id
				cartVo.setStoreId(cart.getStoreId());

				storeName = cart.getStoreName();
				ids += cart.getCartId()+",";
				goodsNum += cart.getGoodsNum(); 
                goodsPrice += cart.getGoodsPrice() * cart.getGoodsNum();
				cartList.add(cart);
			}
			cartVo.setCartIds(ids);
			cartVo.setStoreName(storeName);
			cartVo.setGoodsNum(goodsNum);
            cartVo.setGoodsTotalPrice(goodsPrice);
			cartVo.setList(cartList);
		}
		return cartVo;
	}

	/**
	 * 通过多个购物车id查询购物车,分单
	 * @param cartIds 返回一个分单后的CartVo集合,一个CartVo为一个订单
	 * @return
	 */
	@Override
	public List<CartVo> queryVOListByCartIds(String cartIds) {
		
		//通过多个购物车id查询购物车数据
		List<Cart> cartList = new ArrayList<Cart>();
		if(StringUtils.isNotEmpty(cartIds) && !"null".equals(cartIds)){
			String[] cartId = cartIds.split(",");
			//遍历购物车id数组
			for(String id : cartId){
				Cart cart = cartDao.queryCartById(id);
				cartList.add(cart);
			}
		}
		//拆单,将购物车数据存入map,键为店铺id,值为店铺名称,得到所有店铺的信息(唯一,去重)
		Map<String,String> storeMap = new HashMap<String,String>();
		if(cartList!=null&&cartList.size()!=0) {
			for (Cart cart : cartList) {
				if(cart!=null) {
					storeMap.put(cart.getStoreId(), cart.getStoreName());
				}
			}
		}
		Set<String> storeIds = storeMap.keySet();
		//新建一个cartVo,存储相同店铺的购物车信息
		List<CartVo> cartVoList = new ArrayList<CartVo>();
		if(storeIds!=null&&storeIds.size()!=0){
			//遍历店铺map的店铺id
			for (String id : storeIds) {
				//创建一个新的cartVo
				CartVo cartVo = new CartVo();
				//将店铺id存入cartVo
				cartVo.setStoreId(id);
				//将店铺名称存入cartVo
				cartVo.setStoreName(storeMap.get(id));
				//创建一个新的Cart集合
				List<Cart> list = new ArrayList<Cart>();

				//新建一个字符串,用来存储多个id字段
				String ids = "";

				int goodsNum = 0; //商品种类数量
				Double goodsTotalPrice = 0d; //购物车商品总价

				//遍历所有购物车数据
				for(Cart cart:cartList){
					//如果购物车的店铺id与当前店铺id一致,将购物车信息存入购物车集合
					if(cart.getStoreId().equals(id)){
						ids += cart.getCartId()+",";
						goodsNum += cart.getGoodsNum();
						goodsTotalPrice += (NumberUtils.getBigDecimal(String.valueOf(cart.getGoodsNum())).
								multiply(NumberUtils.getBigDecimal(cart.getGoodsPrice().toString()))).doubleValue();
						list.add(cart);
					}
				}
				//将得到的购物车集合,商品数量,商品总价,商品总运费放入
				cartVo.setList(list);
				cartVo.setGoodsNum(goodsNum);
				cartVo.setGoodsTotalPrice(goodsTotalPrice);
				cartVo.setCartIds(ids);
				//将cartVo放入cartVoList
				cartVoList.add(cartVo);
			}
		}
		return cartVoList;
	}
	
	/**
	 * 通过用户id查询购物车
	 * @param memberId 
	 * @return 
	 */
	@Override
	public List<CartVo> queryCartByMemberID(String memberId) {
		//通过memberId查询购物车数据
		List<Cart> cartList = this.queryBuyCart(memberId);
		//拆单,将购物车数据存入map,键为店铺id,值为店铺名称,得到所有店铺的信息(唯一,去重)
		Map<String,String> storeMap = new HashMap<String,String>();
		for(Cart cart:cartList){
			storeMap.put(cart.getStoreId(), cart.getStoreName());
		}
		Set<String> storeIds = storeMap.keySet();
		//新建一个cartVo,存储相同店铺的购物车信息
		List<CartVo> cartVoList = new ArrayList<CartVo>();
		
	    //遍历店铺map的店铺id
		for (String id : storeIds) {
			//创建一个新的cartVo
			CartVo cartVo = new CartVo();
			//将店铺id存入cartVo
			cartVo.setStoreId(id);
			//将店铺名称存入cartVo
			cartVo.setStoreName(storeMap.get(id));
			//创建一个新的Cart集合
			List<Cart> list=new ArrayList<Cart>();
			
			//新建一个字符串,用来存储多个id字段
			String ids = "";
			
			int goodsNum = 0; //商品种类数量
			Double goodsTotalPrice = 0d; //购物车商品总价
			
			//遍历所有购物车数据
			for(Cart cart:cartList){
				//如果购物车的店铺id与当前店铺id一致,将购物车信息存入购物车集合
				if(cart.getStoreId().equals(id)){
					list.add(cart);
					ids += cart.getCartId()+",";
					goodsNum += cart.getGoodsNum();
		            goodsTotalPrice += (NumberUtils.getBigDecimal(String.valueOf(cart.getGoodsNum())).
		                    multiply(NumberUtils.getBigDecimal(cart.getGoodsPrice().toString()))).doubleValue();
				}
			}
			//将得到的购物车集合,商品数量,商品总价,商品总运费放入
			cartVo.setList(list);
			cartVo.setGoodsNum(goodsNum);
	        cartVo.setGoodsTotalPrice(goodsTotalPrice);
	        cartVo.setCartIds(ids);
	        //将cartVo放入cartVoList
			cartVoList.add(cartVo);
		}
		return cartVoList;
	}

	/**
	 * 将商品信息存入cart实体中
	 * @param goodsId 商品id
	 * @param goodsSpecId 商品规格id
	 */
	@Override
	public Cart copyGoodsToCart(String goodsId,String goodsSpecId){
		Goods goods = goodsService.findGoodById(goodsId); //商品信息
        GoodsSpec goodsSpec = goodsSpecService.findByGoodsSpecId(goodsSpecId); //商品规格信息
        GoodsClass goodsClass = goodsClassService.findById(goods.getGcId()); //通过商品分类id查询商品所在分类
        //新建一个商品规格,通过GoodsUtils.getSepcMapAndColImgToGoodsSpec方法查询出规格的图片和值
		GoodsSpec spec = GoodsUtils.getSepcMapAndColImgToGoodsSpec(goods,goodsSpec);
		
		Cart cart = new Cart();
		cart.setGoodsId(goods.getGoodsId());
		if(StringUtils.isNotEmpty(spec.getColImg()) && !"null".equals(spec.getColImg())){
			cart.setGoodsImages(spec.getColImg());
		}else{
			if(goods.getGoodsImage()!=null){
				//存储商品默认图片
				cart.setGoodsImages(goods.getGoodsImage().split(",")[0]);
			}else{
				//若商品没有默认图片存储空字段
				cart.setGoodsImages("");
			}
		}
		cart.setGoodsName(goods.getGoodsName());
		cart.setGoodsPrice(goodsSpec.getSpecGoodsPrice().doubleValue());
		cart.setStoreId(goods.getStoreId());
		cart.setStoreName(goods.getStoreName());
		cart.setSpecId(spec.getGoodsSpecId());
		//获取分类的层级path
		String[] gcIds = goodsClass.getGcIdpath().split(",");
		cart.setFirstGcId(gcIds[0]); //将商品所在分类的一级分类的id存入购物车
		//新建一个字段存储新的规格格式
		String specInfo = "";
		//遍历规格map,取出键值对,拼接specInfo
		Map<String,String> map = spec.getSepcMap();
		if(map!=null){
			Set<String> set = map.keySet();
			for(String str : set){
				specInfo += str + ":" + map.get(str) + "&nbsp;";
			}
		}
		cart.setSpecInfo(specInfo);
		return cart;
	}
	
	/**
	 * 登录保存购物车信息
	 * @param cartVo
	 */
	@Override
	public void addLoginCart(CartVo cartVo,String memberId) {
		for(Cart cart : cartVo.getList()){
			/**
			 * 判断是否购买自己店铺下的商品,若是,过滤掉
			 */
			Store store = storeService.findById(cart.getStoreId());
			if(store!=null){
				if(!store.getMemberId().equals(memberId)){ //不是自己店铺的商品
					cart.setMemberId(memberId);
					List<Cart> list = cartDao.queryBuyCart(cart); //判断是否存在相同购物车信息,包括商品id,用户id,和规格id
					if(list!=null && list.size()>0){//如果有数据  就更新 否则就添加
						for(Cart carts : list){
							Integer num = carts.getGoodsNum()+cart.getGoodsNum();
							//判断购物车中同一商品数量是否超过100
							if(num>100){ //超过,直接设置为100
								num = 100;
							}
							cart.setGoodsNum(num.shortValue());
							cart.setCartId(carts.getCartId());
							cartDao.updateCart(cart);
						}
					}else{
						cart.setGoodsNum(cart.getGoodsNum().shortValue());
						cartDao.saveCart(cart);
					}
				}
			}
		}
	}
	
	/**
	 * 根据用户id和店铺id查询购物车
	 * @param memberId
	 * @param StoreId
	 * @return
	 */
	@Override
	public List<Cart> queryCartByStoreId(String memberId, String StoreId) {
		return cartDao.queryCartByStoreId(memberId, StoreId);
	}
	
	/**
     * 获取到订单的总数，总金额
     * @param cartList 购物车集合
     * @return
     */
	@Override
	public CartVo getCartVoByCart(List<Cart> cartList) {
		CartVo cartVo = new CartVo();
        int goodsNum = 0; //商品种类数量
        Double goodsTotalPrice = 0d;
        if(cartList != null){
        	for(Cart cart:cartList){
            	goodsNum += cart.getGoodsNum();
                goodsTotalPrice += (NumberUtils.getBigDecimal(String.valueOf(cart.getGoodsNum())).
                        multiply(NumberUtils.getBigDecimal(cart.getGoodsPrice().toString()))).doubleValue();
            }
        }
        
        cartVo.setList(cartList);
        cartVo.setGoodsNum(goodsNum);
        cartVo.setGoodsTotalPrice(goodsTotalPrice);
        return cartVo;
	}
	
	/**
     * 获取订单总金额和订单总数量
     * @param cartVoList CartVo集合
     * @return 返回一个Map<String,Object>集合,键:"goodsNum",商品总数量;"goodsTotalPrice",订单总价格(不含运费)
     */
	@Override
	public Map<String, Object> getTotalPrice(List<CartVo> cartVoList) {
		Map<String,Object> map = new HashMap<String, Object>();
    	int goodsNum = 0;
    	double goodsTotalPrice = 0d;
    	for(CartVo cartVo : cartVoList){
    		goodsNum += cartVo.getGoodsNum();
    		goodsTotalPrice += cartVo.getGoodsTotalPrice();
    	}
    	map.put("goodsNum", goodsNum);
    	map.put("goodsTotalPrice", goodsTotalPrice);
    	return map;
	}
	
	/**
	 * 计算订单最后应付金额
	 * @param cartIds 购物车的id
	 * @param freight 运费信息
	 * @param couponId 优惠券id
	 * @param cityId 城市id
	 * @param isPd 是否使用余额
	 * @param memberId 用户id
	 * @return
	 */
	@Override
	public Map<String,Object> queryTotalPrice(String cartIds,String freight, String couponId, String cityId, String isPd, String memberId){
		Map<String,Object> map = new HashMap<String, Object>();
		
		//根据购物车id获取购物车信息,分单
		List<CartVo> cartVoList = this.queryVOListByCartIds(cartIds);
		
		/**
		 * 优惠券使用信息
		 */
		//新建一个优惠券金额
		double couponPrice = 0.0;
		//判断是否使用优惠券
		if(StringUtils.isNotBlank(couponId)){ //使用,查询优惠券信息
			Coupon coupon = couponService.getCouponById(couponId);
			couponPrice = coupon.getCouponPrice().doubleValue();
		}

		/**
		 * 计算运费
		 */
//		String cityid = null;
//		if(StringUtils.isNotBlank(cityId)){
//			cityid =  Integer.valueOf(cityId);
//		}
		this.getFreightForCartVo(cartVoList, freight, cityId);
		
		/**
		 * 计算相关价格
		 */
		//新建一个商品总价
		double totalGoodsPrice = 0.0;
		//订单优惠后的金额
		double realPrice = 0.0;
		//新建一个总运费
		double totalFreight = 0.0;
		for(CartVo cartVo : cartVoList){
			OrderVo orderVo = new OrderVo();
			orderVo.setGoodsAmount(BigDecimal.valueOf(cartVo.getGoodsTotalPrice()));
			orderVo.setStoreId(cartVo.getStoreId());
			//将店铺运费信息存入运费字段
			orderVo.setShippingFee(BigDecimal.valueOf(cartVo.getGoodsTotalFreight()));
			/**
			 * 订单优惠相关
			 */
			promoteService.calcuPrice(orderVo, null);

			realPrice += (orderVo.getGoodsAmount().add(orderVo.getShippingFee()).subtract(orderVo.getPromoPrice())).doubleValue();
					
			//====================全平台促销时使用 start============//
			/*StrategyCondition condition = new StrategyCondition();
			//将店铺运费信息存入运费字段	
			condition.setOrderFreight(cartVo.getGoodsTotalFreight());
			
			realPrice += calculateService.Calculate(cartVo.getGoodsTotalPrice(), condition);*/
			//====================end================//
			
			totalFreight += cartVo.getGoodsTotalFreight();
			totalGoodsPrice += cartVo.getGoodsTotalPrice();
		}
		
		
		//新建一个订单应付金额
		//double realTotlaPrice=0.0;
		//实际需要支付的金额
		double totalPrice = 0.0;
		//订单优惠后的金额,减去使用优惠券的金额
		if((realPrice-couponPrice)>0){ //判断优惠后的金额减去优惠券金额是否不为负数
			totalPrice = realPrice - couponPrice;
			//realTotlaPrice = realPrice - couponPrice;
		}
		
		/**
		 * 是否余额支付
		 */
		//新建一个余额支付金额
		double predepositAmount = 0.0;
		//判断是否使用余额支付
		if(Integer.valueOf(isPd)==1){ //使用余额支付
			//实时查询用户信息
			Member member = memberService.findById(memberId);
			 //判断余额是否充足
			if(member.getAvailablePredeposit().doubleValue()>totalPrice){ //余额充足
				predepositAmount = totalPrice;
			    totalPrice = 0.0; //liudongjie 注掉
			}else{ //余额不足
				predepositAmount = member.getAvailablePredeposit().doubleValue();
				double totalPr = totalPrice-member.getAvailablePredeposit().doubleValue();
				totalPrice = totalPr;
			}
		}
		
        //cartVo.set优惠金额 goodsTotalPrice - realPrice
		//存储订单实际支付金额
		map.put("totalPrice", totalPrice);
		//存储订单总运费
		map.put("totalFreight", totalFreight);
		//存储订单优惠券金额
		map.put("couponPrice", couponPrice);
		//存储订单优惠金额
		map.put("conditionPrice", totalGoodsPrice + totalFreight - realPrice);
		//商品总价
		map.put("totalGoodsPrice", totalGoodsPrice);
		//余额支付金额
		map.put("predepositAmount", predepositAmount);
		//订单应付金额
		//map.put("realTotlaPrice",realTotlaPrice);
		return map;
	}
	
	/**
	 * 查询商品库存,和商品价格是否变动
	 * @param cartIds 
	 * @return 返回一个Map<String,Object>,键为类型:understock:库存不足,pricechange:价格变动,值为两个List<Cart>
	 */
	@Override
	public Map<String, Object> orderValidation(String cartIds) {
		//获取CartVo集合,分单,一个CartVo为一个订单
		List<CartVo> cartVoList = this.queryVOListByCartIds(cartIds);
		Map<String, Object> map = new HashMap<String, Object>();
		//新建一个存储库存不足商品的list
		List<Cart> stockList = new ArrayList<Cart>();
		//新建一个价格变动商品的list
		List<Cart> priceList = new ArrayList<Cart>();
		//新建一个商品规格异常的List
		List<Cart> specList = new ArrayList<Cart>();
		//新建一个商品下架的List
		List<Cart> goodsShowList = new ArrayList<Cart>();
		
		for(CartVo cartVo : cartVoList){
			for(Cart cart : cartVo.getList()){
				Goods goods = goodsService.findGoodById(cart.getGoodsId());
				//判断商品状态
				if(goods!=null){
					if(goods.getGoodsState()==GoodsState.GOODS_OPEN_STATE&&
							goods.getGoodsShow()==GoodsState.GOODS_ON_SHOW&&
							goods.getGoodsStoreState()==GoodsState.GOODS_STORE_OPEN){  //商品状态正常
						GoodsSpec goodsSpec = goodsSpecService.findByGoodsSpecId(cart.getSpecId());
						if(goodsSpec == null){
							specList.add(cart);
						}else{
							//库存不足
							if(cart.getGoodsNum()>goodsSpec.getSpecGoodsStorage()){
								stockList.add(cart);
							}
							//商品价格变动
							if(cart.getGoodsPrice()!=goodsSpec.getSpecGoodsPrice().doubleValue()){
								priceList.add(cart);
							}
						}
					}else{
						goodsShowList.add(cart);
					}
				}else{
					specList.add(cart);
				}
				
			}
		}
		map.put("understock", stockList); //商品库存不足
		map.put("pricechange", priceList); //商品价格变动
		map.put("specnotfund", specList); //商品规格异常
		map.put("goodsshow", goodsShowList); //商品状态异常
		return map;
	}
	
	/**
	 * 通过cartIds,查询运费,收货地址id可为空
	 * @param cartIds 购物车的id
	 * @param cityId 如果二级地区id(cityId)设为null,可为空,为空时返回的价格是默认的运费模板(全国)的价格标准
	 * @return Map<String,Object> 键为店铺id,值为对应运费的map
	 */
	public Map<String,Object> queryFreightByCartIds(String cityId,String cartIds){
		Map<String,Object> map = new HashMap<String, Object>();
		List<CartVo> cartVoList = this.queryVOListByCartIds(cartIds);
		//遍历cartVo,一个cartVo对应一个店铺下的所有购物车数据
		for(CartVo cartVo:cartVoList){
			//新建一个店铺不包邮商品的数量
			int transGoodsNum = 0;
			for(Cart cart:cartVo.getList()){
				Goods goods = goodsService.findGoodById(cart.getGoodsId());
				if(goods.getGoodsTransfeeCharge()==0){ //商品为买家承担运费
					transGoodsNum += cart.getGoodsNum();
				}
			}

			//新建一个map存储运费,key为运输类型,value为运费
			Map<String,Double> transportMap = new HashMap<String, Double>();
			String storeId = cartVo.getStoreId();
			if(transGoodsNum > 0){ //如果商品中有需要运费的商品,计算运费
				transportMap = getFreight(storeId, cityId, transGoodsNum);
			}
			//将店铺id为键,transportMap为值存入map
			map.put(storeId, transportMap);
		}
		return map;
	}


	private Map<String, Double> getFreight(String storeId, String cityId, int transGoodsNum){
		Map<String,Double> transportMap = new HashMap<String, Double>();
		//通过店铺id获得店铺下的运费模板
		Transport transport = transportService.getDefTransportByStoreId(storeId);
		if(transport!=null){ //判断店铺是否使用运费模板
			if(transport.getTransportExtendList()!=null){ //判断运费模板下是否有具体模板

				List<GroupContiner> group = group(transport.getTransportExtendList());

				for (int i = 0; i < group.size(); i++) {
					GroupContiner groupContiner = group.get(i);
					String transportAreaIds = groupContiner.getTransAreaIds();
					List<TransportExtend> transportList = groupContiner.getTransList();
					String transType = groupContiner.getTransType();
					Double freight = new Double(0);


					for (int j = 0; j < transportList.size(); j++) {
						TransportExtend transportExtend = transportList.get(j);
						if(transportAreaIds.contains("," + cityId + ",")){
							String areaId = transportExtend.getAreaId();
							if(areaId.contains("," + cityId + ",")){
								freight = totalFreight(transGoodsNum, transportExtend);
								break;
							}
						} else {
							if(transportExtend.getAreaName().equals("全国")){
								freight = totalFreight(transGoodsNum, transportExtend);
							}
						}
					}
					transportMap.put(transType, freight);
				}

			}
		}

		return transportMap;
	}
	
	/**
	 * 通过cartIds,查询优惠券
	 * @param cartIds 多个购物车id
	 * @param memberId 用户id
	 * @return 返回一个优惠券集合
	 */
	public List<CouponMember> queryCouponByCartIds(String cartIds,String memberId){
		//通过多个购物车id查询cartVo(分单)
		List<CartVo> cartVoList = this.queryVOListByCartIds(cartIds);
		//新建一个CouponMember集合,用来存储店铺下可用的优惠券
		List<CouponMember> list = new ArrayList<CouponMember>();
		CouponSearch couponSearch = new CouponSearch();
		if(cartVoList.size()==1){ //判断购买的只有一个店铺下的商品的时候使用优惠券
			for(CartVo cartVo : cartVoList){ //遍历cartVoList
				couponSearch.setMemberId(memberId);
				couponSearch.setStoreId(cartVo.getStoreId());
				//通过用户id查询用户所有的优惠券
				List<CouponMember> couponMemberList = couponMemberService.getCouponListByMemberIdAndStoreId(couponSearch);
				for(CouponMember couponMember:couponMemberList){ //遍历优惠券集合
					// 优惠券开始时间
					long sLong = couponMember.getStartTime();
					// 优惠券结束时间
					long eLong = couponMember.getEndTime();
					// 当前时间
					Date nowDate = new Date();
					long nLong = nowDate.getTime();
					if(nLong >= sLong && nLong <= eLong){ //判断当前时间可以使用的优惠券
						if(couponMember.getCouponIsUser()==0){ //过滤使用掉的优惠券
							if(cartVo.getStoreId().equals(couponMember.getStoreId())){ //判断店铺下的优惠券
								if(cartVo.getGoodsTotalPrice()<couponMember.getCouponLimit().doubleValue()){ //判断是否达到优惠金额
									couponMember.setCouponSuc(1); //设置优惠券为不可使用
								}else{
									couponMember.setCouponSuc(0); //设置优惠券为可以使用
								}
								list.add(couponMember); //将优惠券放入优惠券集合
							}
						}
					}
				}
			}
		}
		return list;
	}
	
	/**
	 * 清空购物车
	 * @param memberId 用户id
	 */
	public void deleteAllCartByMemberId(String memberId){
		cartDao.deleteAllCartByMemberId(memberId);
	}
	
	/**
	 * 查询用户购物车数量
	 * @param memberId
	 * @return
	 */
	public Integer queryCountByMemberId(String memberId){
		return cartDao.queryCountByMemberId(memberId);
	}
	
	/**
	 * 计算选择运费后的运费价格
	 * @param cartVoList
	 * @param freight 运费信息(通过','隔开例如"py|11,py|10"的格式,'py'是运输类型,'10'为店铺id)
	 * @param cityId 城市id
	 */
	public void getFreightForCartVo(List<CartVo> cartVoList, String freight, String cityId){
		/**
		 * 计算订单运费信息
		 */
		//新建一个map,用来存储一个店铺下的运费信息,键为店铺id,值为运费类型
		Map<String,String> storeShipMap = new HashMap<String, String>();
		//判断运费是否包邮
		if(StringUtils.isNotBlank(freight)){ //不包邮
			//将运费信息根据','拆分为例如"py|10"的格式,'py'是运输类型,'10'为店铺id
			String[] ships = freight.split(",");
			for(String ship:ships){ //遍历运费信息,进行进一步查分
				String[] fres = ship.split("\\|");
				storeShipMap.put(fres[1], fres[0]);
			}
		}
		
		for(CartVo cartVo:cartVoList){
			//获取单个运费模板的运费
			Double shipprice = null;
			if(storeShipMap.get(cartVo.getStoreId())!=null){ //判断是否存在运费
				//新建一个店铺不包邮商品的数量
				int transGoodsNum = 0;
				for(Cart cart:cartVo.getList()){
					Goods goods = goodsService.findGoodById(cart.getGoodsId());
					if(goods.getGoodsTransfeeCharge()==0){ //商品为买家承担运费
						transGoodsNum += cart.getGoodsNum();
					}
				}
				Map<String, Double> freightMap = getFreight(cartVo.getStoreId(), cityId, transGoodsNum);
				shipprice = freightMap.get(storeShipMap.get(cartVo.getStoreId()));
				//shipprice = transportService.getFreightForStore(cartVo.getStoreId(), storeShipMap.get(cartVo.getStoreId()), cityId, transGoodsNum);
			}
			//订单运费
			if(shipprice!=null){
				cartVo.setGoodsTotalFreight(shipprice.doubleValue());
			}else{
				cartVo.setGoodsTotalFreight(0.0);
			}
		}
	}
	
	/**
	 * 通过购物车获取会员优惠券列表
	 */
	@Override
	public List<CouponMember> findCouponMemberListByCartIds(String cartIds,String memberId) {
		//购物车下单  根据用户id和cartids 查询商品 根据店铺id,和商品一级分类id分组
		List<Cart> cartList = this.queryCartByIds(cartIds,memberId);
		//新建一个购物车按店铺id和商品所在一级分类id分组的集合
		List<CartCouponVo> cartCouponVoList = new ArrayList<CartCouponVo>();
		
		//获取购物车中全部的店铺id和商品一级分类id
		String sp = "";
		String storeIds = "";
		String goodsClassIds = "";
		
		/**
		 * 根据店铺id,和商品一级分类id排序的数据特性,将购物车中数据按店铺id和商品所在一级分类id分组
		 */
		String storeId = null; //店铺id
		String gcId = null; //商品第一级分类id
		CartCouponVo cartCouponVo = null; //购物车按店铺id和商品所在一级分类id分组
		List<Cart> cartLists = null; //购物车集合
		double goodsTotalPrice = 0.0;
		if(cartList!=null){
			for (int i=0; i<cartList.size(); i++) {
				Cart cart = cartList.get(i);
				if(storeId==null){ //判断店铺id是否存在,不存在新建一个购物车按店铺id和商品所在一级分类id分组和商品所在一级分类id分组和购物车集合
					cartCouponVo = new CartCouponVo();
					cartLists = new ArrayList<Cart>();
				}else if(!storeId.equals(cart.getStoreId())){ //判断店铺id是否更换 ,若更换新建一个购物车按店铺id和商品所在一级分类id分组和商品所在一级分类id分组和购物车集合
					//若更换将购物车集合加入cartCouponVo,将分组的商品总价格加入cartCouponVo
					//将cartCouponVo加入cartCouponVoList
					//然后新建一个购物车按店铺id和商品所在一级分类id分组和购物车集合
					cartCouponVo.setGoodsTotalPrice(goodsTotalPrice);
					cartCouponVo.setCartList(cartLists);
					cartCouponVoList.add(cartCouponVo);
					goodsTotalPrice = 0.0;
					cartCouponVo = new CartCouponVo();
					cartLists = new ArrayList<Cart>();
				}else if(gcId != null && !gcId.equals(cart.getFirstGcId())){
					//判断购物车商品所在一级分类是否更换
					//若更换将购物车集合加入cartCouponVo,将分组的商品总价格加入cartCouponVo
					//将cartCouponVo加入cartCouponVoList
					//然后新建一个购物车按店铺id和商品所在一级分类id分组和购物车集合
					cartCouponVo.setGoodsTotalPrice(goodsTotalPrice);
					cartCouponVo.setCartList(cartLists);
					cartCouponVoList.add(cartCouponVo);
					goodsTotalPrice = 0.0;
					cartCouponVo = new CartCouponVo();
					cartLists = new ArrayList<Cart>();
				}else if(gcId != null && gcId.equals(cart.getFirstGcId())){
					cartCouponVo.setGoodsTotalPrice(goodsTotalPrice);
					cartCouponVo.setCartList(cartLists);
				}
				
				cartCouponVo.setFirstGcId(cart.getFirstGcId());
				cartCouponVo.setStoreId(cart.getStoreId());
				goodsTotalPrice += cart.getGoodsPrice()*cart.getGoodsNum();
				cartLists.add(cart);
				storeId = cart.getStoreId();
				gcId = cart.getFirstGcId();
				
				storeIds = storeIds + sp + storeId;
				goodsClassIds = goodsClassIds + sp + gcId;
				sp = ",";
				
				if(i==cartList.size()-1){ //判断是否为最后一次循环
					//将购物车集合加入cartCouponVo,将分组的商品总价格加入cartCouponVo
					//将cartCouponVo加入cartCouponVoList
					cartCouponVo.setGoodsTotalPrice(goodsTotalPrice);
					cartCouponVo.setCartList(cartLists);
					cartCouponVoList.add(cartCouponVo);
				}
			}
		}
		
		//通过会员id，店铺列表和商品分类列表，获取该会员所有的优惠券
		List<CouponMember> couponMemberList = couponMemberService.findCouponMemberByConditions(memberId,storeIds,goodsClassIds,System.currentTimeMillis());
		if(couponMemberList != null){

			for(int i = 0; i < couponMemberList.size(); i++){
				CouponMember couponMember = couponMemberList.get(i);
				if(couponMember.getCouponIsUser() == 1){
					couponMemberList.remove(couponMember);
					continue;
				}
			}

			for(int i = 0; i < couponMemberList.size(); i++){
				CouponMember couponMember = couponMemberList.get(i);
				for(CartCouponVo couponVo:cartCouponVoList){
					//根据店铺id和商品一级分类id匹配优惠券
					if(couponVo.getStoreId().equals(couponMember.getStoreId())&&couponVo.getFirstGcId().equals(couponMember.getCouponGoodsClassId())){
						if(couponVo.getGoodsTotalPrice()<couponMember.getCouponLimit().doubleValue()){ //判断是否达到优惠金额
							couponMember.setCouponSuc(1); //设置优惠券为不可使用
						}else{
							couponMember.setCouponSuc(0); //设置优惠券为可以使用
							break;
						}
					}else{
						couponMember.setCouponSuc(1); //设置优惠券为不可使用
					}
				}
			}
		}else{
			couponMemberList = new ArrayList<CouponMember>();
		}
		return couponMemberList;
	}

	/**
	 * 批量保存购物车
	 * @param carts
     */
	@Override
	public void saveCarts(List<Cart> carts) {
		cartDao.saveCarts(carts);
	}


	private List<GroupContiner> group(List<TransportExtend> list){
		List<GroupContiner> result = new ArrayList<GroupContiner>();
		Set<String> types=new HashSet<String>();
		for (int i = 0; i < list.size(); i++) {
			TransportExtend transportExtend = list.get(i);

			String transType = transportExtend.getType();
			
			if(!types.contains(transType)){
				types.add(transType);
				GroupContiner gc = new GroupContiner();
				gc.setTransType(transType);
				gc.setTransAreaIds(transportExtend.getAreaId());
				
				for (int j=0; j < list.size();j++) {
					TransportExtend transport = list.get(j);
					if(transport.getType().equals(transType)){
						gc.setTransAreaIds(gc.getTransAreaIds() + transport.getAreaId());
						gc.getTransList().add(transport);
					} 
				}
				result.add(gc);
			}
		}

		return result;
	}

	class GroupContiner{
		private String transType = new String();
		private String transAreaIds = new String();
		private List<TransportExtend> transList = new ArrayList<TransportExtend>();

		public String getTransType(){
			return transType;
		}

		public void setTransType(String transType){
			this.transType = transType;
		}

		public String getTransAreaIds(){
			return transAreaIds;
		}

		public void setTransAreaIds(String transAreaIds){
			this.transAreaIds = transAreaIds;
		}

		public List<TransportExtend> getTransList(){
			return transList;
		}

		public void setTransList(List<TransportExtend> transList){
			this.transList = transList;
		}
	}

	private Double totalFreight(int goodsCount, TransportExtend transportExtend){

		Integer snum = transportExtend.getSnum();    // 首件数量
		Double sprice = transportExtend.getSprice(); // 首运费
		Integer xnum = transportExtend.getXnum();    // 续件数量
		Double xprice = transportExtend.getXprice(); // 续件费
		Double tatalFreight = new Double(0);

		if(goodsCount <= snum){
			tatalFreight = sprice;
		}else{
			int cha = goodsCount - snum;
			Double rate = Math.ceil(Double.valueOf(cha + "")/Double.valueOf(xnum + ""));
			tatalFreight = tatalFreight + rate * xprice + sprice;
		}
		return tatalFreight;
	}
}

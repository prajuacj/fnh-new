package com.leimingtech.service.module.tostatic.service.impl;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leimingtech.core.common.CommonConstants;
import com.leimingtech.core.common.Constants;
import com.leimingtech.core.entity.ArticleClass;
import com.leimingtech.core.entity.ImageSet;
import com.leimingtech.core.entity.base.Article;
import com.leimingtech.core.entity.base.Goods;
import com.leimingtech.core.freemarker.TagCreator;
import com.leimingtech.core.freemarker.ToStaticPageUtils;
import com.leimingtech.core.freemarker.shiro.ShiroTags;
import com.leimingtech.core.state.goods.GoodsState;
import com.leimingtech.service.module.goods.dao.GoodsDao;
import com.leimingtech.service.module.goods.service.GoodsUrlService;
import com.leimingtech.service.module.setting.service.SettingService;
import com.leimingtech.service.module.tostatic.service.ToStaticService;
import com.leimingtech.service.module.website.service.ArticleClassService;
import com.leimingtech.service.module.website.service.ArticleService;
import com.leimingtech.service.utils.page.Pager;

import freemarker.template.TemplateException;

/**
 * 
 * @author cgl 2015年08月11日09:29:20
 */
@Service
public class ToStaticServiceImpl implements ToStaticService {

	@Autowired
	GoodsDao goodsDao;
	@Resource
	GoodsUrlService goodsService;
	@Resource
	private SettingService settingService;
	@Resource
	private ArticleService articleService;
	@Resource
	private ArticleClassService articleClassService;

	/**
	 * 将首页页面转化为静态页面 抛出异常:IOException, TemplateException, ServletException
	 */
	@Override
	public void indexToStatic() throws IOException, TemplateException, ServletException {

		Map<String, Object> data = new HashMap<String, Object>();

		/* 标签 */
		data.put("newTag", new TagCreator());

		/* 基本路径 */
		data.put("base", CommonConstants.FRONT_SERVER);
		/* 图片路径 */
		data.put("imgServer", CommonConstants.IMG_SERVER);
		/* 商户后台 */
		data.put("sellerServer", CommonConstants.SELLER_SERVER);

		/* 登陆信息 */
		data.put("shiro", new ShiroTags());

		ImageSet imageSet = new ImageSet();
		Map<String, String> consultMap = settingService.findByNameResultMap("images");
		imageSet.setBig_pic_height(Integer.valueOf(consultMap.get("big_pic_height")));
		imageSet.setBig_pic_width(Integer.valueOf(consultMap.get("big_pic_width")));
		imageSet.setSmall_pic_height(Integer.valueOf(consultMap.get("small_pic_height")));
		imageSet.setSmall_pic_width(Integer.valueOf(consultMap.get("small_pic_width")));
		imageSet.setThumbnail_pic_height(Integer.valueOf(consultMap.get("thumbnail_pic_height")));
		imageSet.setThumbnail_pic_width(Integer.valueOf(consultMap.get("thumbnail_pic_width")));
		imageSet.setTiny_pic_height(Integer.valueOf(consultMap.get("tiny_pic_height")));
		imageSet.setTiny_pic_width(Integer.valueOf(consultMap.get("tiny_pic_width")));

		data.put("imageSet", imageSet);

		String modelBasePath = CommonConstants.MODEL_BASEPATH;

		String modelPath = Constants.INDEX_MODEL;

		String staticPagePath = CommonConstants.STATIC_PAGE_BASEPATH + Constants.STATIC_INDEX + "/index.html";

		ToStaticPageUtils.createHTML(data, modelBasePath, modelPath, staticPagePath);

	}

	/**
	 * 指定某一条商品详细页转为静态页面 抛出异常:IOException, TemplateException, ServletException
	 */
	@Override
	public void saveGoodsDetailToStaticByGoodsId(String goodsId, String storeId, String seitching)
			throws IOException, TemplateException, ServletException {

		Map<String, Object> data = new HashMap<String, Object>();

		/* 标签 */
		data.put("newTag", new TagCreator());

		/* 基本路径 */
		data.put("base", CommonConstants.FRONT_SERVER);

		/* 登陆信息 */
		data.put("shiro", new ShiroTags());

		/* 商品路径 */
		data.put("goodsId", goodsId);
		/* tab切换 */
		data.put("tab", 0);

		/* 图片目录 */
		data.put("imgServer", CommonConstants.IMG_SERVER);
		/* 商户后台 */
		// data.put("sellerServer", CommonConstants.SELLER_SERVER);
		data.put("imgSrc", Constants.STORE_IMG_PATH + "/" + storeId);

		ImageSet imageSet = new ImageSet();
		Map<String, String> consultMap = settingService.findByNameResultMap("images");
		imageSet.setBig_pic_height(Integer.valueOf(consultMap.get("big_pic_height")));
		imageSet.setBig_pic_width(Integer.valueOf(consultMap.get("big_pic_width")));
		imageSet.setSmall_pic_height(Integer.valueOf(consultMap.get("small_pic_height")));
		imageSet.setSmall_pic_width(Integer.valueOf(consultMap.get("small_pic_width")));
		imageSet.setThumbnail_pic_height(Integer.valueOf(consultMap.get("thumbnail_pic_height")));
		imageSet.setThumbnail_pic_width(Integer.valueOf(consultMap.get("thumbnail_pic_width")));
		imageSet.setTiny_pic_height(Integer.valueOf(consultMap.get("tiny_pic_height")));
		imageSet.setTiny_pic_width(Integer.valueOf(consultMap.get("tiny_pic_width")));

		data.put("imageSet", imageSet);

		Goods goods = goodsService.findGoodById(goodsId);
		/**
		 * 商品分类id
		 */
		String classid = goods.getGcId();

		/**
		 * 商品发布日期
		 */
		Long startime = goods.getCreateTime();
		if (startime != null) {
			Date date = new Date(startime);
			SimpleDateFormat dateFormat = new SimpleDateFormat("YY/MM/dd");
			String staticDate = dateFormat.format(date);

			String modelBasePath = CommonConstants.MODEL_BASEPATH;

			String modelPath = Constants.GOODS_DETAIL_MODEL;

			String staticPagePath = CommonConstants.STATIC_PAGE_BASEPATH + Constants.STATIC_GOODS_DETAIL + "/" + classid
					+ "/" + staticDate + "/" + goodsId + ".html";

			String staticurl = Constants.STATIC_GOODS_DETAIL + "/" + classid + "/" + staticDate + "/" + goodsId
					+ ".html";

			String dynameicurl = "/product/detail?id=" + goodsId;

			goods.setStaticurl(staticurl);
			goods.setDynameicurl(dynameicurl);

			if (seitching.equals("1")) {
				goods.setRealurl(staticurl);
			} else {
				goods.setRealurl(dynameicurl);
			}
			goodsService.updateUrl(goods);
			ToStaticPageUtils.createHTML(data, modelBasePath, modelPath, staticPagePath);
		}
	}

	/**
	 * 批量将商品详细页转为静态页面
	 */
	@Override
	public void saveGoodsDetailToStaticBatch() throws IOException, TemplateException, ServletException {

		/**
		 * 这里利用分页,来将数据库中的所有上架商品进行静态化处理, 就一条数据对应一个静态页面,
		 * 这里利用分页是因为:如果数据库数据太多,一次取出导致内存溢出.分页取出很好的避免了这个危险
		 */
		Pager pager = new Pager();

		/** 设置一页的大小 */
		pager.setPageSize(1000);

		/** 查询条件 */
		Goods goodsCondition = new Goods();

		/** 上架 */
		goodsCondition.setGoodsShow(GoodsState.GOODS_ON_SHOW);

		/** 店铺状态开启 */
		goodsCondition.setGoodsStoreState(GoodsState.GOODS_STORE_OPEN);

		/** 商品开启 */
		goodsCondition.setGoodsState(GoodsState.GOODS_OPEN_STATE);

		/** 设置查询条件到pager */
		pager.setCondition(goodsCondition);

		/** 获得共有多少条数据 */
		int count = goodsDao.countGoods(goodsCondition);

		/** 算出共有多少页 */
		int pageCount;

		if (count % pager.getPageSize() != 0) {

			pageCount = count / pager.getPageSize() + 1;

		} else {

			pageCount = count / pager.getPageSize();

		}
		/** 查询是否生成静态页 */
		Map<String, String> map = settingService.findByNameResultMap("switching");
		String seitching = map.get("switching_isStatic");
		/** 循环的将所有页静态化 */
		for (int i = 1; i <= pageCount; i++) {

			/** 设置第几页 */
			pager.setPageNo(i);

			List<Goods> list = goodsDao.findGoodPagerList(pager);

			/** 循环将每一页的商品进行静态化 */
			for (Goods goods : list) {

				/** 调用本类中的方法,生成静态化页面 */
				saveGoodsDetailToStaticByGoodsId(goods.getGoodsId(), goods.getStoreId(), seitching);

			}

		}

	}

	/**
	 * 删除指定的商品静态页面
	 */
	@Override
	public void deleteGoodsDetailStaticPage(String goodsId) {

		String staticPagePath = CommonConstants.STATIC_PAGE_BASEPATH + Constants.STATIC_GOODS_DETAIL + "/" + goodsId
				+ ".html";

		File file = new File(staticPagePath);

		/** 判断是否存在这个网址 */
		if (file.exists()) {
			file.delete();
		}

	}

	/**
	 * 指定文章详细页转为静态页面
	 */
	@Override
	public void saveOneArticleDetailToStatic(String articleId, String acid, String seitching)
			throws IOException, TemplateException, ServletException {
		Map<String, Object> data = new HashMap<String, Object>();

		/* 标签 */
		data.put("newTag", new TagCreator());

		/* 基本路径 */
		data.put("base", CommonConstants.FRONT_SERVER);

		/* 文章ID */
		data.put("articleId", articleId);

		/* 文章分类ID */
		data.put("acId", acid);

		/* 图片目录 */
		data.put("imgServer", CommonConstants.IMG_SERVER);

		Article articles = articleService.findById(articleId);
		/**
		 * 商品分类id
		 */
		String classid = articles.getAcId();

		/**
		 * 文章发布日期
		 */
		Long startime = articles.getCreateTime();
		if (startime != null) {
			Date date = new Date(startime);
			SimpleDateFormat dateFormat = new SimpleDateFormat("YY/MM/dd");
			String staticDate = dateFormat.format(date);

			String modelBasePath = CommonConstants.MODEL_BASEPATH;

			String modelPath = Constants.STATIC_CONTENT_MODEL;

			String staticPagePath = CommonConstants.STATIC_PAGE_BASEPATH + Constants.STATIC_ARTICLE_DETAIL + "/"
					+ classid + "/" + staticDate + "/" + articleId + ".html";

			String staticurl = Constants.STATIC_ARTICLE_DETAIL + "/" + classid + "/" + staticDate + "/" + articleId
					+ ".html";

			String dynameicurl = "/help/content?acId=" + acid + "&articleId=" + articleId;

			articles.setStaticUrl(staticurl);
			articles.setDynameicUrl(dynameicurl);
			if (seitching.equals("1")) {
				articles.setRealUrl(staticurl);
			} else {
				articles.setRealUrl(dynameicurl);
			}
			articleService.update(articles);
			ToStaticPageUtils.createHTML(data, modelBasePath, modelPath, staticPagePath);
		}
	}

	/**
	 * 指定文章分类详细页转为静态页面
	 */
	@Override
	public void saveArticleDetailToStatic(String acid, String seitching)
			throws IOException, TemplateException, ServletException {
		Map<String, Object> data = new HashMap<String, Object>();

		/* 标签 */
		data.put("newTag", new TagCreator());

		/* 基本路径 */
		data.put("base", CommonConstants.FRONT_SERVER);

		/* 文章分类ID */
		data.put("acId", acid);

		data.put("pageSize", 20);

		/* 图片目录 */
		data.put("imgServer", CommonConstants.IMG_SERVER);

		ArticleClass articleClass = articleClassService.findById(acid);
		/**
		 * 商品分类id
		 */
		String classid = articleClass.getAcId();

		/**
		 * 文章发布日期
		 */
		Long startime = articleClass.getCreateTime();
		if (startime != null) {
			Date date = new Date(startime);
			SimpleDateFormat dateFormat = new SimpleDateFormat("YY/MM/dd");
			String staticDate = dateFormat.format(date);

			String modelBasePath = CommonConstants.MODEL_BASEPATH;

			String modelPath = Constants.STATIC_ARTICLE_MODEL;

			String staticPagePath = CommonConstants.STATIC_PAGE_BASEPATH + Constants.STATIC_CONTENT_DETAIL + "/"
					+ classid + "/" + staticDate + "/" + acid + ".html";

			String staticurl = Constants.STATIC_CONTENT_DETAIL + "/" + classid + "/" + staticDate + "/" + acid
					+ ".html";

			String dynameicurl = "/help/index?acId=" + acid;

			articleClass.setStaticUrl(staticurl);
			articleClass.setDynameicUrl(dynameicurl);
			if (seitching.equals("1")) {
				articleClass.setRealUrl(staticurl);
			} else {
				articleClass.setRealUrl(dynameicurl);
			}
			articleClassService.update(articleClass);
			ToStaticPageUtils.createHTML(data, modelBasePath, modelPath, staticPagePath);
		}
	}

	/**
	 * 批量发布文章静态页面
	 */
	@Override
	public void saveArticleToStaticBatch() throws IOException, TemplateException, ServletException {

		/**
		 * 这里利用分页,来将数据库中的所有发布状态的文章进行静态化处理, 就一条数据对应一个静态页面,
		 * 这里利用分页是因为:如果数据库数据太多,一次取出导致内存溢出.分页取出很好的避免了这个危险
		 */
		Pager pager = new Pager();

		/** 设置一页的大小 */
		pager.setPageSize(1000);
		Article article = new Article();
		/** 获得共有多少条数据 */
		int count = articleService.findCount(article);

		/** 算出共有多少页 */
		int pageCount;

		if (count % pager.getPageSize() != 0) {

			pageCount = count / pager.getPageSize() + 1;

		} else {

			pageCount = count / pager.getPageSize();

		}

		/** 查询是否生成静态页 */
		Map<String, String> map = settingService.findByNameResultMap("switching");
		String seitching = map.get("switching_isStatic");

		/** 循环的将所有页静态化 */
		for (int i = 1; i <= pageCount; i++) {

			/** 设置第几页 */
			pager.setPageNo(i);

			Pager rp = articleService.findArticlePageList(pager);
			List<Article> list = (List<Article>) rp.getResult();

			/** 循环将每一页的商品进行静态化 */
			for (Article articles : list) {
				/** 调用本类中的方法,生成静态化页面 */
				saveOneArticleDetailToStatic(articles.getArticleId(), articles.getAcId(), seitching);
			}

		}

	}

	/**
	 * 批量发布文章分类静态页面
	 */
	@Override
	public void saveArticleDetailToStaticBatch() throws IOException, TemplateException, ServletException {

		/**
		 * 这里利用分页,来将数据库中的所有发布状态的文章进行静态化处理, 就一条数据对应一个静态页面,
		 * 这里利用分页是因为:如果数据库数据太多,一次取出导致内存溢出.分页取出很好的避免了这个危险
		 */
		Pager pager = new Pager();

		/** 设置一页的大小 */
		pager.setPageSize(1000);

		/** 获得共有多少条数据 */
		int count = articleClassService.findCount();

		/** 算出共有多少页 */
		int pageCount;

		if (count % pager.getPageSize() != 0) {

			pageCount = count / pager.getPageSize() + 1;

		} else {

			pageCount = count / pager.getPageSize();

		}

		/** 查询是否生成静态页 */
		Map<String, String> map = settingService.findByNameResultMap("switching");
		String seitching = map.get("switching_isStatic");

		/** 循环的将所有页静态化 */
		for (int i = 1; i <= pageCount; i++) {

			/** 设置第几页 */
			pager.setPageNo(i);

			Pager rp = articleClassService.findArticlePageList(pager);
			List<ArticleClass> list = (List<ArticleClass>) rp.getResult();

			/** 循环将每一页的商品进行静态化 */
			for (ArticleClass articles : list) {
				/** 调用本类中的方法,生成静态化页面 */
				saveArticleDetailToStatic(articles.getAcId(), seitching);
			}

		}

	}
}

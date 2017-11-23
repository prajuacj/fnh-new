package com.leimingtech.service.module.lucence.service.impl;


import com.leimingtech.core.common.CommonConstants;
import com.leimingtech.core.common.Constants;
import com.leimingtech.core.entity.GoodsSpec;
import com.leimingtech.core.entity.base.Goods;
import com.leimingtech.service.module.goods.service.GoodsSpecService;
import com.leimingtech.service.module.lucence.service.LucenceService;
import com.leimingtech.service.module.search.service.SearchService;
import com.leimingtech.service.module.setting.service.SettingService;
import com.leimingtech.service.utils.lucene.LucenePager;
import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.index.*;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wltea.analyzer.cfg.DefaultConfig;
import wltea.analyzer.dic.Dictionary;
import wltea.analyzer.lucene.IKAnalyzer;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 生成LUCENCE索引实现
 * @author KVIUFF
 * @version 2015-11-25 09:58:00
 */
@Service
public class LucenceServiceImpl implements LucenceService {
	private static final Object obj=new Object(); //锁标记
	/**
	 * 分词器
	 */
	private Analyzer ANALYZER = new IKAnalyzer();
	
	/**
	 * LUCENE内核版本
	 */
	private Version LUCENEVERSION = Version.LUCENE_48;
	/**
	 * 商品索引路径
	 */
	public static final String GOODS_INDEX_PATH = CommonConstants.LUCENE_BASEPATH + Constants.GOODS_SEARCH_INDEX_PATH;
	/**
	 * 店铺索引路径
	 */
	public static final String STORE_INDEX_PATH = CommonConstants.LUCENE_BASEPATH + Constants.STORE_SEARCH_INDEX_PATH;
	
	@Resource
	private SearchService searchService;
	
	@Resource
	private SettingService settingService;
	@Autowired
	private GoodsSpecService goodsSpecService;

	/**
	 * 初始化索引配置
	 * @return
	 */
	private  IndexWriter getIndexWrite(String IndexPath){
		Directory directory = null;
		IndexWriterConfig indexWriterConfig = null;
		IndexWriter indexWriter = null;
		try {
			File file = new File(IndexPath);
			if(!file.exists()){ // 文件不存在则创建
				file.mkdirs();
			}
			directory = FSDirectory.open(file); // 打开文件
			// 初始化索引配置  版本 ,分词器
			indexWriterConfig = getIndexWriteConfig();
			indexWriter = new IndexWriter(directory, indexWriterConfig);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return indexWriter;
	}
	
	
	/**
	 * 初始化索引配置
	 * @return
	 */
	private IndexWriterConfig getIndexWriteConfig(){
		IndexWriterConfig indexWriterConfig = null;
		indexWriterConfig = new IndexWriterConfig(LUCENEVERSION, ANALYZER);
		return indexWriterConfig;
	}
	
	/**
	 * 初始化document,将goods中的属性放入到document中
	 * @param goods
	 * @param document
	 * @return
	 */
	private Document initGoodsDocument(Goods goods, Document document){
		Field goodsId = new StringField("goodsId", goods.getGoodsId(), Store.YES);
		document.add(goodsId);
		Field goodsName = new TextField("goodsName", goods.getGoodsName(), Store.YES);
		document.add(goodsName);
		Field goodsSubtitle = new TextField("goodsSubtitle", goods.getGoodsSubtitle()==null?"":goods.getGoodsSubtitle(), Store.YES);
		document.add(goodsSubtitle);
		Field gcId = new StringField("gcId", goods.getGcId() + "", Store.YES);
		document.add(gcId);
		Field gcName = new TextField("gcName", goods.getGcName(), Store.YES);
		document.add(gcName);
		Field brandId = new StringField("brandId", goods.getBrandId(), Store.YES);
		document.add(brandId);
		Field brandName = new TextField("brandName", goods.getBrandName()==null?"":goods.getBrandName(), Store.YES);
		document.add(brandName);
		Field typeId = new StringField("typeId", goods.getTypeId(), Store.YES);
		document.add(typeId);
		Field storeId = new StringField("storeId", goods.getStoreId(), Store.YES);
		document.add(storeId);
		Field storeName = new TextField("storeName", goods.getStoreName(), Store.YES);
		document.add(storeName);
		if(goods.getSpecId()!=null){
			Field specId = new StringField("specId", goods.getSpecId(), Store.YES);//设个默认值不然报错20150906
			document.add(specId);
		}
		Field specName = new TextField("specName", goods.getSpecName()==null?"":goods.getSpecName(), Store.YES);
		document.add(specName);
		Field goodsImage = new TextField("goodsImage", goods.getGoodsImage()==null?"":goods.getGoodsImage(), Store.YES);
		document.add(goodsImage);
		Field goodsImageMore = new TextField("goodsImageMore", goods.getGoodsImageMore()==null?"":goods.getGoodsImageMore(), Store.YES);
		document.add(goodsImageMore);
		Field goodsStorePrice = new DoubleField("goodsStorePrice", goods.getGoodsStorePrice().doubleValue(), Store.YES);
		document.add(goodsStorePrice);
		Field goodsStorePriceInterval = new TextField("goodsStorePriceInterval", goods.getGoodsStorePriceInterval()==null?"":goods.getGoodsStorePriceInterval(), Store.YES);
		document.add(goodsStorePriceInterval);
		Field goodsSerial = new TextField("goodsSerial", goods.getGoodsSerial()==null?"":goods.getGoodsSerial(), Store.YES);
		document.add(goodsSerial);
		Field goodsClick = new IntField("goodsClick", goods.getGoodsClick(), Store.YES);
		document.add(goodsClick);
		Field goodsCommend = new StringField("goodsCommend", goods.getGoodsCommend() + "", Store.YES);
		document.add(goodsCommend);
		Field createTime = new LongField("createTime", goods.getCreateTime()==null? 0 :goods.getCreateTime(), Store.YES);
		document.add(createTime);
//		Field goodsKeywords = new TextField("goodsKeywords", goods.getGoodsKeywords()==null?"":goods.getGoodsKeywords(), Store.YES);
//		document.add(goodsKeywords);
		Field goodsDescription = new TextField("goodsDescription", goods.getGoodsDescription()==null?"":goods.getGoodsDescription(), Store.YES);
		document.add(goodsDescription);
		Field goodsBody = new TextField("goodsBody", goods.getGoodsBody()==null?"":goods.getGoodsBody(), Store.YES);
		document.add(goodsBody);
		Field goodsAttr = new TextField("storeName", goods.getGoodsAttr()==null?"":goods.getGoodsAttr(), Store.YES);
		document.add(goodsAttr);
		Field goodsSpec = new TextField("goodsSpec", goods.getGoodsSpec()==null?"":goods.getGoodsSpec(), Store.YES);
		document.add(goodsSpec);
		Field goodsColImg = new TextField("goodsColImg", goods.getGoodsColImg()==null?"":goods.getGoodsColImg(), Store.YES);
		document.add(goodsColImg);
		Field goodsForm = new IntField("goodsForm", goods.getGoodsForm(), Store.YES);
		document.add(goodsForm);
		Field transportId = new StringField("transportId", StringUtils.isEmpty(goods.getTransportId())?"-1":goods.getTransportId(), Store.YES);
		document.add(transportId);
		Field pyPrice = new DoubleField("pyPrice", goods.getPyPrice()==null?Double.parseDouble(0+""):goods.getPyPrice().doubleValue(), Store.YES);
		document.add(pyPrice);
		Field kdPrice = new DoubleField("kdPrice", goods.getKdPrice()==null?Double.parseDouble(0+""):goods.getKdPrice().doubleValue(), Store.YES);
		document.add(kdPrice);
		Field esPrice = new DoubleField("esPrice", goods.getEsPrice()==null?Double.parseDouble(0+""):goods.getEsPrice().doubleValue(), Store.YES);
		document.add(esPrice);
		Field cityId = new StringField("cityId", StringUtils.isEmpty(goods.getCityId())?"-1":goods.getCityId(), Store.YES);
		document.add(cityId);
		Field cityName = new TextField("cityName", goods.getCityName()==null?"":goods.getCityName(), Store.YES);
		document.add(cityName);
		Field provinceId = new StringField("provinceId", StringUtils.isEmpty(goods.getProvinceId())?"-1":goods.getProvinceId(), Store.YES);
		document.add(provinceId);
		Field provinceName = new TextField("provinceName", goods.getProvinceName()==null?"":goods.getProvinceName(), Store.YES);
		document.add(provinceName);
		Field goodsCloseReason = new TextField("goodsCloseReason", goods.getGoodsCloseReason()==null?"":goods.getGoodsCloseReason(), Store.YES);
		document.add(goodsCloseReason);
		Field commentnum = new IntField("commentnum", goods.getCommentnum(), Store.YES);
		document.add(commentnum);
		Field salenum = new IntField("salenum", goods.getSalenum(), Store.YES);
		document.add(salenum);
		Field goodsCollect = new IntField("goodsCollect", goods.getGoodsCollect(), Store.YES);
		document.add(goodsCollect);
		Field goodsTransfeeCharge = new IntField("goodsTransfeeCharge", goods.getGoodsTransfeeCharge(), Store.YES);
		document.add(goodsTransfeeCharge);
		Field storeClassId = new StringField("storeClassId", StringUtils.isEmpty(goods.getStoreClassId())?"-1":goods.getStoreClassId(), Store.YES);
		document.add(storeClassId);
		Field classPath = new StringField("classPath", ","+goods.getClassPath(), Store.YES);
		document.add(classPath);
		Field staticurl = new StringField("staticurl", goods.getStaticurl() ==null?"/product/detail?goodsId="+goodsId:goods.getStaticurl(), Store.YES);
		document.add(staticurl);
		Field dynameicurl = new StringField("dynameicurl", goods.getDynameicurl()==null?"/product/detail?goodsId="+goodsId:goods.getDynameicurl() , Store.YES);
		document.add(dynameicurl);
		Field realurl = new StringField("realurl", goods.getRealurl()==null?"/product/detail?goodsId="+goodsId:goods.getRealurl() , Store.YES);
		document.add(realurl);
		
		/**通过商品id获得商品下所有对应的规格*/
		List<GoodsSpec> goodsSpecs = goodsSpecService.findListByGoodsId(goods.getGoodsId());
		
		StringBuffer sb=new StringBuffer();
		if(goodsSpecs!=null && goodsSpecs.size()>0){
			for (int i = 0; i < goodsSpecs.size(); i++) {
				sb.append(goodsSpecs.get(i).getSpecGoodsSpec());
			}
		}		
		
		Field specIds = new StringField("specIds", sb==null?"":sb.toString(), Store.YES);//规格
		document.add(specIds);
		
		
		return document;
	}
	
	
	/**
	 * 生成LUCENE索引 单个
	 * @param goods
	 */
	public  void creatOneIndex(Goods goods) {
		deleteOneIndex(goods);
		synchronized(obj){
			IndexWriter indexWriter=null;
			try {
				indexWriter = getIndexWrite(GOODS_INDEX_PATH);
				//初始化document
				Document document = new Document();
				document = initGoodsDocument(goods, document);
				// 添加文本到索引中
				indexWriter.addDocument(document);
				indexWriter.forceMerge(1);
				// 将改动持久化到本次索引中
				indexWriter.commit();
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				closeIndexWriter(indexWriter);
			}
		}
	}
	
	/**
	 * 生成LUCENE索引 多个
	 * @param list
	 */
	public void createMoreIndex(List<Goods> list) {
		//初始化document
		for (Goods goods : list) {
			creatOneIndex(goods);
		}
	}

	/**
	 * 删除索引 多个
	 * @param list
	 */
	public void deleteMoreIndex(List<Goods> list) {
		for (Goods goods : list) {
			deleteOneIndex(goods);
		}
	}
	
	/**
	 * 删除所有商品索引
	 * 
	 */
	public void deleteAllGoodsIndex() {
		IndexWriter indexWriter=null;
		try {
			indexWriter = getIndexWrite(GOODS_INDEX_PATH);
			indexWriter.deleteAll();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeIndexWriter(indexWriter);
		}
	}

	/**
	 * 删除索引 单个
	 * @param goods
	 */
	public void deleteOneIndex(Goods goods) {
		try {
			String goodsId = goods.getGoodsId();
			deleteOneIndex("goodsId", goodsId);
			//强制删除
			//forceDelete();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 删除指定商品索引
	 * @param field 商品的LUCENE某一个字段名称
	 * @param id 该字段名称对应的id为多少的商品将会被删除
	 */
	public void deleteOneIndex(String field, String id) {
		synchronized(obj){
			IndexWriter indexWriter = null;
			try {
				indexWriter = getIndexWrite(GOODS_INDEX_PATH);
//				BooleanQuery booleanQuery = new BooleanQuery();
//				NumericRangeQuery<Integer> rangeQuery2 = NumericRangeQuery.newIntRange(field, id, id, true, true);
//				booleanQuery.add(rangeQuery2, BooleanClause.Occur.MUST);
//				indexWriter.deleteDocuments(rangeQuery2);
				Term term = new Term(field, id);
				indexWriter.deleteDocuments(term);
				indexWriter.forceMerge(1);
				// 将改动持久化到本次索引中
				indexWriter.commit();
			} catch (Exception e) {
				e.printStackTrace();
			} finally{
				if(indexWriter != null){
					try {
						indexWriter.close();
						indexWriter = null;
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	//强制删除方法    
    public void forceDelete() {
        IndexWriter indexWriter = null;
        try {
           indexWriter = getIndexWrite(GOODS_INDEX_PATH);
           indexWriter.forceMergeDeletes();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
			try {
				if(indexWriter!=null)indexWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
    }

	/**
	 * 更新索引 多个
	 */
	public void updateMoreIndex(List<Goods> list) {
		for (Goods goods : list) {
			updateOneIndex(goods);
		}
	}

	/**
	 * 更新索引  单个
	 * @param goods
	 */
	public  void updateOneIndex(Goods goods) {
		synchronized(obj){
			IndexWriter indexWriter = null;
			try {
				indexWriter = getIndexWrite(GOODS_INDEX_PATH);
				//初始化document
				Document document = new Document();
				document = initGoodsDocument(goods, document);
				Term term = new Term("goodsId", goods.getGoodsId());
				indexWriter.updateDocument(term, document);
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				closeIndexWriter(indexWriter);
			}
		}
	}
	
	/**
	 * 初始化搜索的商品数据
	 * @param goods
	 * @param document
	 * @return
	 */
	private Goods initSearchGoods(Goods goods, Document document){
		// 初始化goods
		goods.setGoodsId(document.get("goodsId"));
		goods.setGoodsName(document.get("goodsName"));
		goods.setGoodsSubtitle(document.get("goodsSubtitle"));
		goods.setGcId(document.get("gcId"));
		goods.setGcName(document.get("gcName"));
		goods.setBrandId(document.get("brandId"));
		goods.setBrandName(document.get("brandName"));
		goods.setTypeId(document.get("typeId"));
		goods.setStoreId(document.get("storeId"));
		goods.setStoreName(document.get("storeName"));
		if(StringUtils.isNotEmpty(document.get("specId"))){
			goods.setSpecId(document.get("specId"));
		}
		//goods.setSpecId(document.get("specId"));
		goods.setSpecName(document.get("specName"));
		goods.setGoodsImage(document.get("goodsImage"));
		goods.setGoodsImageMore(document.get("goodsImageMore"));
		goods.setGoodsStorePrice(BigDecimal.valueOf(Double.parseDouble(document.get("goodsStorePrice"))));
		goods.setGoodsStorePriceInterval(document.get("goodsStorePriceInterval"));
		goods.setGoodsSerial(document.get("goodsSerial"));
		goods.setGoodsClick(Integer.parseInt(document.get("goodsClick")));
		goods.setGoodsCommend(Integer.parseInt(document.get("goodsCommend")));
		goods.setCreateTime(Long.parseLong(document.get("createTime")));
//		goods.setGoodsKeywords(document.get("goodsKeywords"));
		goods.setGoodsDescription(document.get("goodsDescription"));
		goods.setGoodsBody(document.get("goodsBody"));
		goods.setGoodsAttr(document.get("goodsAttr"));
		goods.setGoodsSpec(document.get("goodsSpec"));
		goods.setGoodsColImg(document.get("goodsColImg"));
		goods.setGoodsForm(Integer.parseInt(document.get("goodsForm")));
		goods.setTransportId(document.get("transportId"));
		goods.setPyPrice(BigDecimal.valueOf(Double.parseDouble(document.get("pyPrice"))));
		goods.setKdPrice(BigDecimal.valueOf(Double.parseDouble(document.get("kdPrice"))));
		goods.setEsPrice(BigDecimal.valueOf(Double.parseDouble(document.get("esPrice"))));
		goods.setCityId(document.get("cityId"));
		goods.setCityName(document.get("cityName"));
		goods.setProvinceId(document.get("provinceId"));
		goods.setProvinceName(document.get("provinceName"));
		goods.setGoodsCloseReason(document.get("goodsCloseReason"));
		goods.setCommentnum(Integer.parseInt(document.get("commentnum")));
		goods.setSalenum(Integer.parseInt(document.get("salenum")));
		goods.setGoodsCollect(Integer.parseInt(document.get("goodsCollect")));
		goods.setGoodsTransfeeCharge(Integer.parseInt(document.get("goodsTransfeeCharge")));
		goods.setStoreClassId(document.get("storeClassId"));
        Map<String,String> map = settingService.findByNameResultMap("switching");
		
		String seitching=map.get("switching_isStatic");
		
        if(seitching.equals("1")){
			goods.setRealurl(document.get("staticurl"));
		}else{
			goods.setRealurl(document.get("dynameicurl"));
		}
        
		return goods;
	}

	/**
	 * LUCENCE查询
	 * @param lucenePager
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public LucenePager searchGoodsIndex(LucenePager lucenePager) {
		Directory directory = null;
		IndexSearcher indexSearcher = null;
		IndexReader indexReader = null;
		File indexDir = new File(GOODS_INDEX_PATH);
		try {
			directory = FSDirectory.open(indexDir);
			//获得INDEXREADER
			indexReader = DirectoryReader.open(directory);
			//获得IndexSearch
			indexSearcher = new IndexSearcher(indexReader);
			//从lucenePage得到query
			BooleanQuery query = (BooleanQuery) searchService.getQuery(lucenePager);
			if(query == null){
				return null;
			}
			//判断筛选条件是否为空,如果为空则说明,没有数据要筛选
			if(lucenePager.getFilterConditionsStr() != null){
				query = searchService.doFilter(query, lucenePager);
			}
			
			//得到总记录数
			int count = indexSearcher.search(query, 100000000).totalHits;
			if(count > 0){
				//获得res
				TopDocsCollector res = searchService.getCollector(lucenePager);
				
				if(lucenePager != null) {
					if(lucenePager.getSpValueIdsFilter() != null){
						String[] spValueIds = lucenePager.getSpValueIdsFilter();
						for(int i=0;i<spValueIds.length;i++){
							query.add(new WildcardQuery(new Term("specIds", "*\""+spValueIds[i]+"\"*")),Occur.MUST);  
						}
					}
				}
				/*SortField sortField = new SortField(lucenePager.getSortField(), SortField.Type.STRING, true);
				
				TopDocs docs = indexSearcher.search(query, 100000000, new Sort(sortField));*/
				indexSearcher.search(query, res);
				/*
				 * 得到topDocs,并且指定索引,取出该页的数据
				 * 参数:从下表为几开始开始,多少一页 从0开始下标
				 */
				TopDocs docs = null;
				Integer start = lucenePager.getStart();
				Integer size = lucenePager.getPageSize();
				docs = res.topDocs(start, size);
				// 得到doc结果集数组
				ScoreDoc[] scoreDocs = docs.scoreDocs;
				// 准备list为null
				List<Goods> list = new ArrayList<Goods>();
				for(int i = 0; i < scoreDocs.length; i++){
					// 拿到doc对象,相当于拿到数据库中的一条记录
					Document document = indexSearcher.doc(scoreDocs[i].doc);
					// 准备goodstypeVo,请注意这里引的包是search下面的
					Goods goods = new Goods();
					goods = initSearchGoods(goods, document);
					//将赋值完成的类加入list
					list.add(goods);
				}
				lucenePager.setResult(list);
				lucenePager.setTotalRows(res.getTotalHits());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(indexReader!=null){
					indexReader.close();
					indexReader=null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if(directory!=null){
					directory.close();
					directory=null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return lucenePager;
	}
	
	/**
	 * 初始化store document
	 * @param store
	 * @return
	 */
	private Document initStoreDocument(com.leimingtech.core.entity.base.Store store, Document document){
		//准备field
		Field storeId = new StringField("storeId", store.getStoreId(), Store.YES);
		document.add(storeId);
		Field storeName = new StringField("storeName", store.getStoreName(), Store.YES);
		document.add(storeName);
		Field storeLogo = new StringField("storeLogo", store.getStoreLogo()==null?"":store.getStoreLogo(), Store.YES);
		document.add(storeLogo);
		Field memberName = new TextField("memberName", store.getMemberName(), Store.YES);
		document.add(memberName);
		Field scId = new StringField("scId", store.getScId() + "", Store.YES);
		document.add(scId);
		Field provinceId = new StringField("provinceId", store.getProvinceId() + "", Store.YES);
		document.add(provinceId);
		Field storeAddress = new TextField("storeAddress", store.getStoreAddress(), Store.YES);
		document.add(storeAddress);
		Field storeSales = new IntField("storeSales", store.getStoreSales(), Store.YES);
		document.add(storeSales);
		Field storeCollect = new IntField("storeCollect", store.getStoreCollect(), Store.YES);
		document.add(storeCollect);
		Field storeClick = new IntField("storeClick", store.getStoreClick(), Store.YES);
		document.add(storeClick);
		Field storeCredit = new IntField("storeCredit", store.getStoreCredit(), Store.YES);
		document.add(storeCredit);
		Field praiseRate = new DoubleField("praiseRate", store.getPraiseRate(), Store.YES);
		document.add(praiseRate);
		Field storeGoodsCount = new IntField("storeGoodsCount", store.getStoreGoodsCount() == null ? 0 : store.getStoreGoodsCount(), Store.YES);
		document.add(storeGoodsCount);
		String storezy = store.getStoreZy();
		if(StringUtils.isNotEmpty(storezy)){
			Field storeZy = new TextField("storeZy", storezy, Store.YES);
			document.add(storeZy);
		}

		return document;
	}

	/**
	 * 店铺生成LUCENE索引 单个
	 * @param store
	 */
	public  void creatStoreOneIndex(com.leimingtech.core.entity.base.Store store) {
		deleteStoreOneIndex(store);
		synchronized(obj){

			IndexWriter indexWriter = null;
			try {
				indexWriter = getIndexWrite(STORE_INDEX_PATH);
				//初始化document
				Document document = new Document();
				document = initStoreDocument(store, document);
				// 添加文本到索引中
				indexWriter.addDocument(document);
				indexWriter.forceMerge(1);
				// 将改动持久化到本次索引中
				indexWriter.commit();
				System.out.println("店铺索引生成成功：店铺名称－" + store.getStoreName());
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				closeIndexWriter(indexWriter);
			}
		}
	}

	/**
	 * 店铺生成LUCENE索引 多个
	 * @param list
	 */
	public void createStoreMoreIndex(
			List<com.leimingtech.core.entity.base.Store> list) {
		for (com.leimingtech.core.entity.base.Store store : list) {
			creatStoreOneIndex(store);
		}
	}

	/**
	 * 店铺删除索引 单个
	 * @param store
	 */
	public void deleteStoreOneIndex(com.leimingtech.core.entity.base.Store store) {
		try {
			String storeId = store.getStoreId();
			deleteStoreOneIndex("storeId", storeId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 店铺删除指定商品索引
	 * @param field 店铺的LUCENE某一个字段名称
	 * @param id 该字段名称对应的id为多少的店铺将会被删除
	 */
	public  void deleteStoreOneIndex(String field, String id) {
		synchronized(obj){
			IndexWriter indexWriter = null;
			try {
				indexWriter = getIndexWrite(STORE_INDEX_PATH);
//				BooleanQuery booleanQuery = new BooleanQuery();
//				NumericRangeQuery<Integer> rangeQuery2 = NumericRangeQuery.newIntRange(field, id, id, true, true);
//				booleanQuery.add(rangeQuery2, BooleanClause.Occur.MUST);
//				indexWriter.deleteDocuments(rangeQuery2);
				Term term = new Term("storeId", id);
				indexWriter.deleteDocuments(term);
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				closeIndexWriter(indexWriter);
			}
		}
	}

	/**
	 * 店铺删除索引 多个
	 * @param list
	 */
	public void deleteStoreMoreIndex(
			List<com.leimingtech.core.entity.base.Store> list) {
		for (com.leimingtech.core.entity.base.Store store : list) {
			deleteStoreOneIndex(store);
		}
	}

	/**
	 * 店铺更新索引 单个
	 * @param store
	 */
	public  void updateStoreOneIndex(com.leimingtech.core.entity.base.Store store) {
		synchronized(obj){
			IndexWriter indexWriter =null;
			try {
				 indexWriter = getIndexWrite(STORE_INDEX_PATH);
				//初始化document
				Document document = new Document();
				document = initStoreDocument(store, document);
				Term term = new Term("storeId", store.getStoreId() + "");
				indexWriter.updateDocument(term, document);
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				closeIndexWriter(indexWriter);
			}
		}
	}
	
	public void closeIndexWriter(IndexWriter indexWriter){
		if(indexWriter!=null){
			try {
				indexWriter.close();
				indexWriter=null;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 店铺更新索引 多个
	 * @param list
	 */
	public void updateStoreMoreIndex(
			List<com.leimingtech.core.entity.base.Store> list) {
		for (com.leimingtech.core.entity.base.Store store : list) {
			updateStoreOneIndex(store);
		}
	}
	
	/**
	 * 删除所有的店铺的索引
	 */
	public void deleteAllStoreIndex() {
		IndexWriter indexWriter=null;
		try {
			 indexWriter = getIndexWrite(STORE_INDEX_PATH);
			indexWriter.deleteAll();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeIndexWriter(indexWriter);
		}
	}
	
	/**
	 * 店铺LUCENCE查询
	 * @param lucenePager
	 * @return
	 */
	public LucenePager searchStoreIndex(LucenePager lucenePager) {
		
		Directory directory = null;
		IndexSearcher indexSearcher = null;
		IndexReader indexReader = null;
		
		try{
			File indexDir = new File(STORE_INDEX_PATH);
			//目录
			directory = FSDirectory.open(indexDir);
			//获得indexreader
			indexReader = DirectoryReader.open(directory);
			//获得IndexSearch
			indexSearcher = new IndexSearcher(indexReader);
			//从lucenePage得到query
			BooleanQuery query = (BooleanQuery) searchService.getQuery(lucenePager);
			if(query == null){
				return null;
			}
			//得到总记录数
			int count = indexSearcher.search(query, 100000000).totalHits;
			if(count != 0){
				//获得res
				TopDocsCollector res = searchService.getCollector(lucenePager);
				//使用indexsearcher查找,并且将res条件放入
				indexSearcher.search(query, res);
				/*
				 * 得到topDocs,并且指定索引,取出该页的数据
				 * 参数:从下表为几开始开始,多少一页 从0开始下标
				 */
				TopDocs docs = null;
				Integer start = lucenePager.getStart();
				Integer size = lucenePager.getPageSize();
				docs = res.topDocs(start, size);
				
				//得到doc结果集数组
				ScoreDoc[] scoreDocs = docs.scoreDocs;
				//准备list为null
				List<com.leimingtech.core.entity.base.Store> list = null;
				//判断是否取出数据
				//如果有数据则new 一个list
				list = new ArrayList<com.leimingtech.core.entity.base.Store>();
				for(int i = 0; i < scoreDocs.length; i++){
					//准备goodstypeVo,请注意这里引的包是search下面的
					com.leimingtech.core.entity.base.Store store = new com.leimingtech.core.entity.base.Store();
					//拿到doc对象,相当于拿到数据库中的一条记录
					Document document = indexSearcher.doc(scoreDocs[i].doc);
					/*
					 * 下面开始一对一的赋值给store
					 */
					store.setStoreId(document.get("storeId"));
					store.setStoreName(document.get("storeName"));
					store.setMemberName(document.get("memberName"));
					store.setScId(document.get("scId"));
					store.setProvinceId(document.get("provinceId"));
					store.setStoreAddress(document.get("storeAddress"));
					store.setStoreSales(Integer.parseInt(document.get("storeSales")));
					store.setStoreCollect(Integer.parseInt(document.get("storeCollect")));
					store.setStoreClick(Integer.parseInt(document.get("storeClick")));
					store.setStoreCredit(Integer.parseInt(document.get("storeCredit")));
					store.setPraiseRate(Float.parseFloat(document.get("praiseRate")));
					store.setStoreGoodsCount(Integer.parseInt(document.get("storeGoodsCount")));
					store.setStoreZy(document.get("storeZy"));
					store.setStoreLogo(document.get("storeLogo"));
					//将赋值完成的类加入list
					list.add(store);
				}
				lucenePager.setResult(list);
				lucenePager.setTotalRows(res.getTotalHits());
				return lucenePager;
			}
		}catch(Exception e){
			return null;
		}finally{
			if(indexReader != null){
				try {
					indexReader.close();
					indexReader = null;
				} catch (IOException e) {
					
				}
			}
		}
		return lucenePager;
	}


	public void addWord(){
		DefaultConfig defaultConfig = (DefaultConfig) DefaultConfig.getInstance();
		defaultConfig.setUseSmart(true);
		Dictionary.initial(defaultConfig);
		Dictionary dictionary = Dictionary.getSingleton();
	}

}

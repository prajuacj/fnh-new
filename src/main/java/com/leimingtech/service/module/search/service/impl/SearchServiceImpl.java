package com.leimingtech.service.module.search.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.leimingtech.core.common.StringUtils;
import com.leimingtech.core.lucene.analyzer.SimpleAnalyzer;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Filter;
import org.apache.lucene.search.MatchAllDocsQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.SortField.Type;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocsCollector;
import org.apache.lucene.search.TopFieldCollector;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.search.WildcardQuery;
import org.apache.lucene.util.Version;
import org.springframework.stereotype.Service;

import wltea.analyzer.lucene.IKAnalyzer;

import com.leimingtech.core.jackson.JsonUtils;
import com.leimingtech.core.lucene.analyzer.SimpleAnalyzer;
import com.leimingtech.service.module.search.service.SearchService;
import com.leimingtech.service.utils.classInfo.ClassInfo;
import com.leimingtech.service.utils.lucene.FilterCondition;
import com.leimingtech.service.utils.lucene.LucenePager;
import com.leimingtech.service.utils.lucene.SpecFilter;

/**
 * @author cgl
 * 2015年07月06日14:51:40
 */
@Service
public class SearchServiceImpl implements SearchService{
	
	private Analyzer analyzer = new IKAnalyzer();

	/**
	 * 建立query
	 */
	@Override
	public BooleanQuery getQuery(LucenePager lucenePager){
		/*
		 * 判断model,
		 * 商品goods
		 * 店铺 store
		 */
		String model = lucenePager.getModel();
		//关键词
		String keyword = lucenePager.getKeyword();
		//搜索字段
		String searchType = lucenePager.getSearchType();
		//准备booleanQuery
		BooleanQuery query = null;
		if(model.equals("store")){
			//准备booleanQuery
			if(StringUtils.isNotEmpty(keyword)){
				query = new BooleanQuery();
//				QueryParser parser = new QueryParser(Version.LUCENE_48, "storeName", analyzer);
//				Query query1 = null;
//				try {
//					query1 = parser.parse(keyword);
//				} catch (ParseException e) {
//					e.printStackTrace();
//				}
//				//加入query
//				query.add(query1,Occur.SHOULD);
				query.add(new WildcardQuery(new Term("storeName","*" + keyword + "*")), BooleanClause.Occur.SHOULD);
			}else{
				query = new BooleanQuery();
				MatchAllDocsQuery query1 = new MatchAllDocsQuery();
				query.add(query1,BooleanClause.Occur.SHOULD);
			}
			return query;
		} else if(model.equals("goods")) {
			//准备booleanQuery
			query = new BooleanQuery();
			//判断是否是通过关键词搜索,如果不是则不需要使用getKeywords
			if(StringUtils.isNotEmpty(keyword)){
				if(searchType.equals("keywordSearch")){
					//方法一
					QueryParser parser = new QueryParser(Version.LUCENE_48, "goodsName", analyzer);
					Query query1 = null;
					try {
						parser.setDefaultOperator(QueryParser.AND_OPERATOR);
						query1 = parser.parse(keyword);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					//加入query
					query.add(query1,Occur.MUST);
					//方法二
//					query.add(new WildcardQuery(new Term("goodsName","*" + keyword + "*")), BooleanClause.Occur.MUST);
				}else if(searchType.equals("allSearch")){
					//查询所有
					MatchAllDocsQuery query1 = new MatchAllDocsQuery();
					query.add(query1, BooleanClause.Occur.SHOULD);
				}else if(searchType.equals("gcIdSearch")){//通过分类id查找
					//加入query
					query.add(new WildcardQuery(new Term("classPath","*," + keyword + ",*")), BooleanClause.Occur.MUST);
				}else if(searchType.equals("typeIdSearch")){//通过类型id查找
					//加入query
					Term term = new Term(keyword);
					query.add(new WildcardQuery(term),BooleanClause.Occur.MUST);
				}else if(searchType.equals("BrandIdSearch")){
					//加入query
					Term term = new Term("brandId", keyword);
					query.add(new WildcardQuery(term),BooleanClause.Occur.MUST);
				}
			} else {
				MatchAllDocsQuery query1 = new MatchAllDocsQuery();
				query.add(query1,BooleanClause.Occur.SHOULD);
			}
			return query;
		}
		return null;
	}
	
	/**
	 * 数据筛选
	 * @param booleanQuery
	 * @param lucenePager
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public BooleanQuery doFilter(BooleanQuery booleanQuery, LucenePager lucenePager){
			//检验传入的query是否为空
			if(booleanQuery == null){
				booleanQuery = new BooleanQuery();
			}
			List<?> list = JsonUtils.readJsonList(lucenePager.getFilterConditionsStr(), FilterCondition.class);
			/*
			 * 有数据要筛选,开始建立语句
			 */
			//获得筛选条件的list
			List<FilterCondition> filter = (List<FilterCondition>) list;
			/*
			 * 得到所有的键,这里的键代表的是,哪一个字段需要筛选
			 * 并且将得到的set交给迭代器,itertor
			 */
			Iterator<FilterCondition> keys = filter.iterator();
			//使用while迭代数据
			while(keys.hasNext()){
				//得到当前这个条件
				FilterCondition filterCondition = keys.next();
				//得到筛选的名称
				String filterName = filterCondition.getFilterName();
				//得到筛选条件的数据
				String conditions = filterCondition.getConditionData();
				booleanQuery.add(new BooleanClause(new TermQuery(new Term(filterName, conditions)), BooleanClause.Occur.MUST));
				//筛选
//				Integer condition = Integer.parseInt(conditions);
				//建立query条件查询
//				NumericRangeQuery<Integer> rangeQuery = NumericRangeQuery.newIntRange(filterName, condition, condition, true, true);
//				booleanQuery.add(rangeQuery, BooleanClause.Occur.MUST);
			}
		return booleanQuery;
	}
	
	/**
	 * 判断用户是否是否选择了排序,以及排序的规则,分页等
	 */
	@SuppressWarnings("rawtypes")
	public TopDocsCollector<?> getCollector(LucenePager lucenePager) throws NoSuchFieldException, SecurityException, IOException{
		
		TopDocsCollector res = null;
		//判断用户是否选择类排序
		if(lucenePager.getSortField() != null && !lucenePager.getSortField().trim().equals("") && lucenePager.getSortOrder() != null && !lucenePager.getSortOrder().trim().equals("")){
			/*
			 * 符合条件,需要排序
			 */
			//获取排序的方式(降序,升序)
			boolean order = false;//默认为升序
			if(lucenePager.getSortOrder().equals("desc")){
				order = true;
			}
			SortField sortField = null;
			//获得type
			Type type = getType(lucenePager);
			//建立sortField
			sortField = new SortField(lucenePager.getSortField(), type, order);
			//建立sort排序
			Sort sort = new Sort(sortField);
			//建立res
			res = TopFieldCollector.create(sort, 1000000, false, false, false, false);
		}else{
			/*
			 * 不需要排序,直接按照搜索的分页
			 */
			res = TopScoreDocCollector.create(lucenePager.getStart()+lucenePager.getPageSize(), false);
		}
		return res;
	}
	
	 /**
	 * 规格删选器
	 */
	@Override
	public Filter getSpecFilter(LucenePager lucenePager) {
		if(lucenePager != null) {
			if(lucenePager.getSpValueIdsFilter() != null){
				String[] spValueIds = lucenePager.getSpValueIdsFilter();
				String searchType = lucenePager.getSearchType();
				String field = "";
				if(searchType.equals("keywordSearch")){
					field = "goodsName";
				}else if(searchType.equals("gcIdSearch")){
					field = "gcId";
				}else if(searchType.equals("typeIdSearch")){
					field = "typeId";
				}
				SpecFilter filter = new SpecFilter(field, lucenePager.getKeyword(), spValueIds);
				return filter;
			}
		}
		return null;
	}
	
	/** 切分关键字
	 * @param keyword
	 * @return	关键字数组
	 * @throws IOException
	 */
	private String[] getKeywords(String keyword) {
		try{
			List<String> list = new ArrayList<String>();
			TokenStream tokenStream = analyzer.tokenStream("", keyword);
	        tokenStream.addAttribute(CharTermAttribute.class);
	        while (tokenStream.incrementToken()) {
	            CharTermAttribute charTermAttribute = tokenStream.getAttribute(CharTermAttribute.class);
	            list.add(charTermAttribute.toString());
	        }
	        String[] result = new String[list.size()];
	        return list.toArray(result);
		}catch(IOException e){
			return new String[]{keyword};
		}
	}

	//获得type
	@SuppressWarnings("deprecation")
	private Type getType(LucenePager lucenePager) throws NoSuchFieldException, SecurityException{
		//获得这个属性的类型
		Class<?> clazz = ClassInfo.getFieldType(lucenePager.getSearchObjClass(), lucenePager.getSortField());
		//判断clazz这个类型属于哪一个类型
		if(clazz.equals(Integer.class)){
			return SortField.Type.INT;
		}else if(clazz.equals(int.class)){
			return SortField.Type.INT;
		}else if(clazz.equals(String.class)){
			return SortField.Type.STRING;
		}else if(clazz.equals(BigDecimal.class)){
			return SortField.Type.DOUBLE;
		}else if(clazz.equals(Long.class)){
			return SortField.Type.LONG;
		}else if(clazz.equals(Double.class)){
			return SortField.Type.DOUBLE;
		}else if(clazz.equals(Float.class)){
			return SortField.Type.FLOAT;
		}else if(clazz.equals(Short.class)){
			return SortField.Type.SHORT;
		}else if(clazz.equals(Byte.class)){
			return SortField.Type.BYTE;
		}else if(clazz.equals(Timestamp.class)){
			return SortField.Type.LONG;
		}
		return null;
	}
}

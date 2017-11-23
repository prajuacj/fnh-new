package com.leimingtech.service.module.goods.service.impl;

import java.util.List;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import com.leimingtech.core.cache.jedis.JedisConfig;
import com.leimingtech.core.cache.jedis.JedisUtils;
import com.leimingtech.core.common.StringUtils;
import com.leimingtech.core.entity.Districts;
import com.leimingtech.core.entity.GoodsClassh5;
import com.leimingtech.core.entity.base.Member;
import com.leimingtech.service.module.goods.dao.DistrictsDao;
import com.leimingtech.service.module.goods.service.DistrictsService;
import com.leimingtech.service.module.member.dao.MemberDao;

/**
 * 商品分类接口实现
 * @author lkang
 *
 */
@Slf4j
@Service
public class DistrictsServiceImpl implements DistrictsService {
	
	@Resource
    private DistrictsDao districtsDao;

	@Resource
	private MemberDao memberDao;
	
	/**
     * 保存分类
     * @param goodsClass
     */
	public void save(Districts goodsClass) {
		districtsDao.save(goodsClass);
	}

	/**
     * 修改分类
     * @param goodsClass
     */
	public void update(Districts goodsClass) {
		districtsDao.update(goodsClass);
        if(goodsClass.getIsRelate()!=null && goodsClass.getIsRelate()==1){//是否关联子分类 0否, 1是
        	//修改子分类佣金比例类型
        	Districts gclass=new Districts();
        	gclass.setGcParentId(goodsClass.getGcId());
        	gclass.setExpenScale(goodsClass.getExpenScale());//佣金比例
        	gclass.setGcIdpath(goodsClass.getGcIdpath());//idpath
        	districtsDao.updatebyparentid(gclass);
        }
      //更新redis缓存
      updateDicCache();
	}

	/**
     * 删除
     * @param id
     */
	public void delete(String id) {
		districtsDao.delete(id);
		//更新redis缓存
		delDicCache();
	}
	
	/**
     * 通过id查询分类
     * @param gcId
     * @return
     */
	public Districts findById(String gcId) {
		return districtsDao.findById(gcId);
	}

	/**
     * 根据父id查询分类列表
     * @param parentid 为0查询一级分类
     * @return
     */
	public List<Districts> findList(String parentid) {
		return districtsDao.findList(parentid);
	}
	
	/**
     * 查询所有的分类
     * @return
     */
	public List<Districts> findAll() {
		return districtsDao.findAll();
	}

	/**
     * 根据不同条件查询条数，页面验证用
     * @param goodsClass
     * @return
     */
	public int findCount(Districts goodsClass) {
		return districtsDao.findCount(goodsClass);
	}
   
	/**
     * 根据父goodsClass查询分类列表
     * @param goodsClass
     * @return
     */
	@Override
	public List<Districts> findListbyishow(Districts goodsClass) {
		return districtsDao.findListbyishow(goodsClass);
	}
    
	/**
     * 查询所有的分类
     * @return
     */
	@Override
	public List<Districts> findAllbyisshow(Districts goodsClass) {
		List<Districts> gclzs = null;
		if(JedisConfig.JEDIS_STATUS){
		////需要安装redis
			Object obj = JedisUtils.getObject(JedisConfig.GCLZ_PREFIX + "goodsClass");
			if(obj == null){
				gclzs = districtsDao.findAllbyisshow(goodsClass);
				//10分钟
				JedisUtils.setObject(JedisConfig.GCLZ_PREFIX + "goodsClass", gclzs, JedisConfig.JEDIS_EXPIRE);
				log.debug(JedisConfig.GCLZ_PREFIX + "存入redis");
			}else{
				gclzs = (List<Districts>)obj;
				log.debug(JedisConfig.GCLZ_PREFIX + "转化成功");
			}
		}else{
			gclzs = districtsDao.findAllbyisshow(goodsClass);
		}
		return gclzs;
	}
	
	/**
     * 商品分类api中设置一级分类下的gcLastChild
     * @param classList
     * @return
     */
    public List<Districts> setApiGcLastChild(List<Districts> classList){
    	for(Districts goodsClass:classList){ //遍历第一级分类
    		List<Districts> list = districtsDao.findChild(goodsClass.getGcId());
			//新建一个字符串,存储每个分类第三级分类下的第一个分类的名称
			String classStr = "";
			//判断一级分类下有没有二级分类
			if(list!=null){ 
				for(Districts secondClass:list){
					//判断二级分类下有没有三级分类
					if(secondClass.getClassList()!=null){ 
						for(int i=0;i<secondClass.getClassList().size();i++){
							if(i==0){
								classStr += secondClass.getClassList().get(i).getGcName() + "";
							}
						}
					}
				}
			}
			goodsClass.setGcLastChild(classStr);
		}
    	return classList;
    }

    /**
     * 通过父id查询子分类
     * @param gcParentId
     * @return
     */
    public List<Districts> findChild(String gcParentId){
    	return districtsDao.findChild(gcParentId);
    }
    
    /**
     * 修改分类
     * @param goodsClass
     */
	@Override
	public void updatebyparentid(Districts goodsClass) {
		districtsDao.updatebyparentid(goodsClass);
	}

	/**
	 * 查询所有分类
	 * @return
	 */
	@Override
	public List<GoodsClassh5> queryDistricts() {
		//List<GoodsClassh5> list = districtsDao.queryDistricts();
		return districtsDao.queryDistricts();
	}

	@Override
	public List<Districts> queryBottomChildList(Districts districts) {
		List<Districts> districtsList = districtsDao.queryBottomChildList(districts);
		
		if (!districtsList.isEmpty()) {
			int listlen = districtsList.size();
			Districts districtItem = null;
			Member member = new Member();
			
			for (int i = 0; i < listlen; i++) {
				districtItem = districtsList.get(i);
				
				//获取层级关系
				String gc_idpath = districtItem.getGcIdpath();
				String[] gc_idpathArr = gc_idpath.split(",");
				StringBuffer gcName = new StringBuffer();
				String gcNames = "";
				
				for (int k = 0; k < gc_idpathArr.length; k++) {
					gc_idpath = gc_idpathArr[k];
					
					if (StringUtils.isNotBlank(gc_idpath)) {
						Districts resultDistrict = districtsDao.findById(gc_idpath);
						
						if (resultDistrict != null) {
							gcName.append(resultDistrict.getGcName());
							gcName.append("/");
						}
					}
				}
				
				if (gcName.toString().endsWith("/")) {
					gcNames = gcName.substring(0, gcName.length() - 1);
				}
				else {
					gcNames = gcName.toString();
				}
				
				districtItem.setGcName(gcNames);
				
				member.setDistrictId(districtItem.getGcId());
				int memCount = memberDao.findCountByDistrict(member);
				
				districtItem.setMemberCount(memCount);
			}
		}
		
		return districtsList;
	}
	
	/**
	 * 更新缓存数据
	 * 需要
	 * 安装redis
	 */
	public void updateDicCache(){
		if(JedisConfig.JEDIS_STATUS){
			Districts goodsclass = new Districts();
			goodsclass.setGcshow(1);//是否显示1为显示0为不显示
			List<Districts> goodsList = districtsDao.findAllbyisshow(goodsclass);
			//10分钟过期时间
			JedisUtils.setObject("gclz:goodsClass", goodsList, 600);
			log.debug("gclz:更新redis");
		}
	}
	
	/**
	 * 删除缓存
	 * 需要安装redis
	 */
	public void delDicCache(){
		updateDicCache();
	}
}

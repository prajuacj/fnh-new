package com.leimingtech.service.module.website.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.leimingtech.core.common.CommonConstants;
import com.leimingtech.core.common.Constants;
import com.leimingtech.core.entity.base.Brand;
import com.leimingtech.core.entity.base.WebCode;
import com.leimingtech.core.entity.vo.FloorVo;
import com.leimingtech.core.jackson.JsonUtils;
import com.leimingtech.service.module.goods.dao.BrandDao;
import com.leimingtech.service.module.website.dao.WebCodeDao;
import com.leimingtech.service.module.website.service.WebCodeService;
import com.leimingtech.service.module.website.vo.BannerVo;
import com.leimingtech.service.module.website.vo.FaceVo;

/**
 * @author llf
 * @Package com.leimingtech.service.module.website.service.impl
 * @Description:
 * @date 2014/12/16 14:26
 */
@Service
public class WebCodeServiceImpl implements WebCodeService{

    @Resource
    private WebCodeDao webCodeDao;
    @Resource
    private BrandDao brandDao;

    /**
     * 保存
     * @param floorVo
     */
    @Override
    public void save(FloorVo floorVo) {
        WebCode webCode = new WebCode();
        webCode.setShowName(floorVo.getFloorName());
        //设置针对楼层的变量名称
        webCode.setVarName(floorVo.getType());
        webCode.setCodeInfo(JsonUtils.toJsonStr(floorVo));
        webCode.setIsShow(floorVo.getIsShow());
        webCode.setSort(floorVo.getSort());
        webCode.setCreateTime(System.currentTimeMillis());
        webCodeDao.save(webCode);
    }

    /**
     * 查询所有
     *
     * @return
     */
    @Override
    public List<WebCode> queryAll() {
        return webCodeDao.queryAll();
    }

    /**
     * 查询单条
     *
     * @param id
     * @return
     */
    @Override
    public FloorVo queryById(String id) {
        return JsonUtils.fromJson(webCodeDao.queryById(id).getCodeInfo(),FloorVo.class);
    }

    /**
     * 修改
     *
     * @param floorVo
     */
    @Override
    public void update(FloorVo floorVo,String id) {
        WebCode webCode = new WebCode();
        webCode.setCodeId(id);
        webCode.setShowName(floorVo.getFloorName());
        //设置针对楼层的变量名称
        webCode.setVarName("floor_list");
        webCode.setCodeInfo(JsonUtils.toJsonStr(floorVo));
        webCode.setIsShow(floorVo.getIsShow());
        webCode.setSort(floorVo.getSort());
        webCode.setUpdateTime(System.currentTimeMillis());
        webCodeDao.update(webCode);
    }

    /**
     * 删除
     *
     * @param id
     */
    @Override
    public void delete(String id) {
        webCodeDao.delete(id);
    }

    @Override
    public void saveBanner(List<BannerVo> bannerList,List<BannerVo> recommendList) {
        Map<String,List<BannerVo>> map = Maps.newHashMap();
        map.put("banner",bannerList);
        map.put("recommend",recommendList);
        WebCode webCode = new WebCode();
        webCode.setShowName("banner");
        //设置针对楼层的变量名称
        webCode.setVarName("banner");
        webCode.setCodeInfo(JsonUtils.toJsonStr(map));
        webCode.setIsShow(1);
        webCode.setSort(1);
        webCodeDao.save(webCode);
    }

    @Override
    public void updateBanner(FaceVo vo,String id) {
        String imgPic ;
        String originalFilename ;
        List<BannerVo> bannerList = Lists.newArrayList();
        List<BannerVo> recommendList = Lists.newArrayList();
        int bannerIndex = 0;
        int recommendIndex = 0;
        for(MultipartFile myFile : vo.getBannerFiles()){
            if(!myFile.isEmpty()){
                originalFilename = String.valueOf(new DateTime().getMillis())+
                        myFile.getOriginalFilename().substring( myFile.getOriginalFilename().indexOf("."));
                try {
                    org.apache.commons.io.FileUtils.copyInputStreamToFile(myFile.getInputStream(),
                            new File(CommonConstants.FILE_BASEPATH + Constants.LOGO_UPLOAD_URL, originalFilename));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                imgPic = Constants.LOGO_UPLOAD_URL + "/" + originalFilename;
                BannerVo bannerVo = new BannerVo();
                bannerVo.setImageUrl(imgPic);
                bannerVo.setLinkUrl(vo.getBannerUrl()[bannerIndex]);
                bannerList.add(bannerVo);
            }
            bannerIndex ++;
        }
        for(MultipartFile myFile : vo.getRecommendFiles()){
            if(!myFile.isEmpty()){
                originalFilename = String.valueOf(new DateTime().getMillis())+
                        myFile.getOriginalFilename().substring( myFile.getOriginalFilename().indexOf("."));
                try {
                    org.apache.commons.io.FileUtils.copyInputStreamToFile(myFile.getInputStream(),
                            new File(CommonConstants.FILE_BASEPATH + Constants.LOGO_UPLOAD_URL, originalFilename));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                imgPic = Constants.LOGO_UPLOAD_URL + "/" + originalFilename;
                BannerVo bannerVo = new BannerVo();
                bannerVo.setImageUrl(imgPic);
                bannerVo.setLinkUrl(vo.getRecommendUrl()[recommendIndex]);
                recommendList.add(bannerVo);
            }
            recommendIndex ++;
        }
    }

    @Override
    public Map<String, List<BannerVo>> queryBannerById(String id) {
        return JsonUtils.fromJson(webCodeDao.queryById(id).getCodeInfo(),Map.class);
    }

    @Override
    public void saveBrand(String[] brandIds) {

        List<Brand> list = Lists.newArrayList();
        for (String brandId : brandIds) {
            Brand brand = brandDao.findById(brandId);
            list.add(brand);
        }
        WebCode webCode = new WebCode();
        webCode.setShowName("brand");
        //设置针对楼层的变量名称
        webCode.setVarName("brand");
        webCode.setCodeInfo(JsonUtils.toJsonStr(list));
        webCode.setIsShow(1);
        webCode.setSort(1);
        webCodeDao.save(webCode);
    }

	@Override
	public List<WebCode> queryAllByType(String type) {
		return webCodeDao.queryAllByType(type);
	}

	@Override
	public WebCode queryNewByType(String type) {
		return webCodeDao.queryNewByType(type);
	}

	@Override
	public List<WebCode> queryByVarname(String type) {
		// TODO Auto-generated method stub
		return webCodeDao.queryByVarname(type);
	}
    
    
}

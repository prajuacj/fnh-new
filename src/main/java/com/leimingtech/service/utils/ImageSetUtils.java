
package com.leimingtech.service.utils;

import java.io.IOException;
import java.util.Map;

import magick.MagickException;

import com.leimingtech.core.common.CommonConstants;
import com.leimingtech.core.common.ImageUtil;
import com.leimingtech.core.common.SpringContextUtil;
import com.leimingtech.core.entity.ImageSet;
import com.leimingtech.service.module.setting.service.SettingService;


/**
 * admin系统设置---图片设置工具类
 * @author zhaorh
 * @version 2015-10-27
 */
public class ImageSetUtils {
	private static SettingService settingService = SpringContextUtil.getBean(SettingService.class);
	
	/**
	 * 图片设置尺寸
	 */
	public static void setImages(String imageMore){
			String[] imageArr = imageMore.split(",");
			new ImageSetThread(imageArr).start();
			
	}
	/**
	 * 获取数据库map集合的value放到ImageSet实体中
	 */
	private static ImageSet getImageSetByMap() {
		Map<String,String> map = settingService.findByNameResultMap("images");
		ImageSet imageSet = new ImageSet();
		imageSet.setBig_pic_height(Integer.valueOf(map.get("big_pic_height")));
		imageSet.setBig_pic_width(Integer.valueOf(map.get("big_pic_width")));
		imageSet.setSmall_pic_height(Integer.valueOf(map.get("small_pic_height")));
		imageSet.setSmall_pic_width(Integer.valueOf(map.get("small_pic_width")));
		imageSet.setThumbnail_pic_height(Integer.valueOf(map.get("thumbnail_pic_height")));
		imageSet.setThumbnail_pic_width(Integer.valueOf(map.get("thumbnail_pic_width")));
		imageSet.setTiny_pic_height(Integer.valueOf(map.get("tiny_pic_height")));
		imageSet.setTiny_pic_width(Integer.valueOf(map.get("tiny_pic_width")));
		return imageSet;
	}

	/**
	 * 生成四种规格图片的线程
	 */
	public static class ImageSetThread extends Thread{
		private String[] imageArr;
		
		public ImageSetThread(String[] imageArr) {
			this.imageArr = imageArr;
		}
		
		@Override
		public void run() {

			try {
				ImageSet imageSet = getImageSetByMap();
				for(int i=0;i<imageArr.length;i++){
					//获取图片扩展名
					String ext = "."+imageArr[i].substring(imageArr[i].lastIndexOf(".") + 1);
					//商品详细页小图尺寸
					int tiny_pic_width = imageSet.getTiny_pic_width();
					int tiny_pic_height = imageSet.getTiny_pic_height();
					//CommonConstants.FILE_BASEPATH
					ImageUtil.scaleRateImageFile(CommonConstants.FILE_BASEPATH+imageArr[i], CommonConstants.FILE_BASEPATH+imageArr[i]+"_"+tiny_pic_width+"x"+tiny_pic_height+ext,tiny_pic_width,tiny_pic_height); 
					//缩略图尺寸
					int thumbnail_pic_width = imageSet.getThumbnail_pic_width();
					int thumbnail_pic_height = imageSet.getThumbnail_pic_height();
					ImageUtil.scaleRateImageFile(CommonConstants.FILE_BASEPATH+imageArr[i], CommonConstants.FILE_BASEPATH+imageArr[i]+"_"+thumbnail_pic_width+"x"+thumbnail_pic_height+ext,thumbnail_pic_width,thumbnail_pic_height); 
					//商品详细页图片尺寸
					int small_pic_width = imageSet.getSmall_pic_width();
					int small_pic_height = imageSet.getSmall_pic_height();
					ImageUtil.scaleRateImageFile(CommonConstants.FILE_BASEPATH+imageArr[i], CommonConstants.FILE_BASEPATH+imageArr[i]+"_"+small_pic_width+"x"+small_pic_height+ext,small_pic_width,small_pic_height); 
					//商品相册图片尺寸
					int big_pic_width = imageSet.getBig_pic_width();
					int big_pic_height = imageSet.getBig_pic_height();
					ImageUtil.scaleRateImageFile(CommonConstants.FILE_BASEPATH+imageArr[i], CommonConstants.FILE_BASEPATH+imageArr[i]+"_"+big_pic_width+"x"+big_pic_height+ext,big_pic_width,big_pic_height);
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (MagickException e) {
				e.printStackTrace();
			}
		}
	}
	
}

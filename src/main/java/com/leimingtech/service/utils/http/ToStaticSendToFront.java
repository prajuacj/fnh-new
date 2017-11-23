package com.leimingtech.service.utils.http;

import com.leimingtech.core.common.CommonConstants;
import com.leimingtech.core.common.ImageUtil;
import com.leimingtech.core.common.SpringContextUtil;
import com.leimingtech.core.entity.ImageSet;
import com.leimingtech.core.entity.base.Goods;
import com.leimingtech.core.entity.base.ShopServer;
import com.leimingtech.service.module.shopappserver.service.ShopServerService;
import magick.MagickException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.util.List;

/**
 * 发布静态页,这个工具类帮助封装了跳转至front 发布静态页面
 *
 * @author chen
 */
public class ToStaticSendToFront {

    //private static ShopServerService shopServerService = SpringContextUtil.getBean(ShopServerService.class);


    public static void setGoods(Goods goods){
        new GoodsStaticThread(goods).start();
    }


    /**
     * 生成商品详细页静态文件的线程
     */
    public static class GoodsStaticThread extends Thread{
        private Goods goods;

        public GoodsStaticThread(Goods goods) {
            this.goods = goods;
        }

        @Override
        public void run() {

            try {
                String goodsId = goods.getGoodsId();
                String storeId = goods.getStoreId();
                ToStaticSendToFront.onegoodsDetailStatic(goodsId, storeId);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



    /**
     * 发布首页静态页面
     */
    public static void indexStatic() throws IOException {

        HttpClient httpClient = new DefaultHttpClient();

        HttpGet httpGet = new HttpGet(CommonConstants.FRONT_SERVER + "/toStatic/indexStatic");
        httpClient.execute(httpGet);

//       List<ShopServer> shopserverlist=shopServerService.findShopServerByType("front");
//       for(ShopServer  shopserver:shopserverlist){
//       HttpGet httpGet = new HttpGet(shopserver.getServerName()+ "/toStatic/indexStatic");
//	   httpClient.execute(httpGet);
//       }
    }

    /**
     * 发布指定商品页面
     */
    public static void onegoodsDetailStatic(String goodsId, String storeId) throws IOException {

        HttpClient httpClient = new DefaultHttpClient();

        HttpGet httpGet = new HttpGet(CommonConstants.FRONT_SERVER + "/toStatic/oneGoodsDetailStatic?goodsId=" + goodsId + "&storeId=" + storeId);
        httpClient.execute(httpGet);
//        List<ShopServer> shopserverlist = shopServerService.findShopServerByType("front");
//        for (ShopServer shopserver : shopserverlist) {
//            HttpGet httpGet = new HttpGet(shopserver.getServerName() + "/toStatic/oneGoodsDetailStatic?goodsId=" + goodsId + "&storeId=" + storeId);
//            httpClient.execute(httpGet);
//        }
    }

    /**
     * 批量发布所有的商品页面
     */
    public static void goodsDetailBatchStatic() throws IOException {

        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(CommonConstants.FRONT_SERVER + "/toStatic/goodsDetailStatic");
        httpClient.execute(httpGet);
//        List<ShopServer> shopserverlist = shopServerService.findShopServerByType("front");
//        for (ShopServer shopserver : shopserverlist) {
//            HttpGet httpGet = new HttpGet(shopserver.getServerName() + "/toStatic/goodsDetailStatic");
//            httpClient.execute(httpGet);
//        }
    }


    /**
     * 发布指定文章
     */
    public static void oneArticlesDetailStatic(String articleId, String acid) throws IOException {

        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(CommonConstants.FRONT_SERVER + "/toStatic/saveOneArticleDetailToStatic?acId=" + acid + "&articleId=" + articleId);
        httpClient.execute(httpGet);

//        List<ShopServer> shopserverlist = shopServerService.findShopServerByType("front");
//        for (ShopServer shopserver : shopserverlist) {
//            HttpGet httpGet = new HttpGet(shopserver.getServerName() + "/toStatic/saveOneArticleDetailToStatic?acId=" + acid + "&articleId=" + articleId);
//            httpClient.execute(httpGet);
//        }
    }

    /**
     * 发布指定文章分类
     */
    public static void saveArticleDetailToStatic(String acid) throws IOException {

        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(CommonConstants.FRONT_SERVER + "/toStatic/saveArticleDetailToStatic?acId=" + acid);
        httpClient.execute(httpGet);

//        List<ShopServer> shopserverlist = shopServerService.findShopServerByType("front");
//        for (ShopServer shopserver : shopserverlist) {
//            HttpGet httpGet = new HttpGet(shopserver.getServerName() + "/toStatic/saveArticleDetailToStatic?acId=" + acid);
//            httpClient.execute(httpGet);
//        }
    }


    /**
     * 发布所有文章分类
     */
    public static void saveArticleDetailToStaticBatch() throws IOException {

        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(CommonConstants.FRONT_SERVER + "/toStatic/saveArticleDetailToStaticBatch");
        httpClient.execute(httpGet);

//        List<ShopServer> shopserverlist = shopServerService.findShopServerByType("front");
//        for (ShopServer shopserver : shopserverlist) {
//            HttpGet httpGet = new HttpGet(shopserver.getServerName() + "/toStatic/saveArticleDetailToStaticBatch");
//            httpClient.execute(httpGet);
//        }
    }

    /**
     * 发布所有文章分类
     */
    public static void saveArticleToStaticBatch() throws IOException {

        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(CommonConstants.FRONT_SERVER + "/toStatic/saveArticleToStaticBatch");
        httpClient.execute(httpGet);

//        List<ShopServer> shopserverlist = shopServerService.findShopServerByType("front");
//        for (ShopServer shopserver : shopserverlist) {
//            HttpGet httpGet = new HttpGet(shopserver.getServerName() + "/toStatic/saveArticleToStaticBatch");
//            httpClient.execute(httpGet);
//        }
    }
}

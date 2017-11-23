package com.leimingtech.service.utils.sessionkey.seller;

import com.leimingtech.core.common.Constants;
import com.leimingtech.core.common.SpringContextUtil;
import com.leimingtech.core.entity.base.Member;
import com.leimingtech.core.entity.base.ShopSeller;
import com.leimingtech.core.entity.base.Store;
import com.leimingtech.service.module.member.service.MemberService;
import com.leimingtech.service.module.seller.service.ShopSellerService;
import com.leimingtech.service.module.store.service.StoreService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

/**
 * @author llf
 * @Package com.leimingtech.seller.utils.sessionKey
 * @Description:
 * @date 2015/3/2 14:38
 */
public class CacheUtils {

    /**
     * 获取SessionUser
     *
     * @return
     */
    public static CacheUser getCacheUser() {
        HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
        if (null != session) {
            CacheUser cacheUser;
            Subject currentUser = SecurityUtils.getSubject();
            cacheUser = (CacheUser) session.getAttribute(Constants.USER_SESSION_KEY);
            if (cacheUser == null) {
                cacheUser = CacheUtils.initCacheUser(currentUser.getPrincipal().toString());
            }
            return cacheUser;
        } else {
            return null;
        }
    }

    /**
     * 根据卖家用户名初始化数据
     *
     * @param sellerName
     * @return
     */
    public static CacheUser initCacheUser(String sellerName) {
        MemberService memberService = SpringContextUtil.getBean(MemberService.class);
        StoreService storeService = SpringContextUtil.getBean(StoreService.class);
        ShopSellerService shopSellerService = SpringContextUtil.getBean(ShopSellerService.class);
        //通过会员id获取店铺账户信息
        ShopSeller shopSeller = shopSellerService.findShopSellerBySellerName(sellerName);
        Store store = null;
        Member member = null;
        //如果店铺账户信息为空，通过会员id直接获取店铺信息（可能是申请店铺的会员）
        if (shopSeller == null) {
            member = memberService.findMemberByName(sellerName);
            store = storeService.findByMemberId(member.getMemberId());
            shopSeller = shopSellerService.findShopSellerByMemberId(member.getMemberId());
        } else {
            member = memberService.findMemberById(shopSeller.getMemberId());
            store = storeService.findById(shopSeller.getStoreId());
        }
        CacheUser cacheUser = new CacheUser();
        cacheUser.setMember(member);
        cacheUser.setSeller(shopSeller);
        cacheUser.setStore(store);
        HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
        if (null != session) {
            session.setAttribute(Constants.USER_SESSION_KEY, cacheUser);
        } else {
            throw new RuntimeException("CacheUser初始化失败");
        }
        return cacheUser;
    }

    /**
     * 清除用户数据
     */
    public static void cleanCacheUser() {
        HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
        session.removeAttribute(Constants.USER_SESSION_KEY);
    }
}

package com.leimingtech.service.module.trade.dao;

import java.util.List;

import com.leimingtech.core.entity.base.RefundReturn;
import com.leimingtech.core.entity.vo.RefundReturnBillVo;
import com.leimingtech.core.entity.vo.RefundReturnDetailVo;
import com.leimingtech.core.entity.vo.ReturnDetailVo;
import com.leimingtech.service.utils.page.Pager;

/**
 * 退款退货DAO接口
 *
 * @author liukai
 * @version 2015-11-02
 */
public interface RefundReturnDao {

	/**
	 * 查询分页退款退货数据
	 * 
	 * @param pager 分页对象
	 * @return
	 */
	public List<RefundReturn> findRefundReturnPagerList(Pager pager);
	
	/**
	 * 查询分页退款退货数据
	 * 
	 * @param pager 分页对象
	 * @return
	 */
	public List<RefundReturnBillVo> findBillPagerList(Pager pager);

	/**
	 * 通过id获取单条退款退货数据
	 * 
	 * @param id
	 * @return
	 */
	public RefundReturn findRefundReturnById(String id);
	
	/**
	 * 通过退款批次号获取单条退款退货数据(仅限支付宝)
	 * @param batchNo
	 * @return
	 */
	public List<RefundReturn> findRefundReturnByBatchNo(String batchNo);
	
	/**
	 * 条件查询退款退货总条数
	 * @param refundReturn
	 * @return
	 */
	public int findRefundReturnCount(RefundReturn refundReturn);

	/**
	 * 通过id删除退款退货数据
	 * 
	 * @param id
	 */
	public void deleteRefundReturnById(String id);

	/**
	 * 修改退款退货数据
	 * 
	 * @param refundReturn
	 */
	public void updateRefundReturn(RefundReturn refundReturn);

	/**
	 * 保存退款退货数据
	 * 
	 * @param refundReturn
	 */
	public void saveRefundReturn(RefundReturn refundReturn);

	/**
	 * 获取所有退款退货数据
	 * 
	 * @return
	 */
	public List<RefundReturn> findRefundReturnAllList();
	
	/**
	 * 查询退款退货详情,必传退货id,可传用户id和店铺id,不需要传null
	 * @param refundReturn
	 * @return
	 */
	public ReturnDetailVo findRefundReturnDetail(RefundReturn refundReturn);
	
	/**
	 * 根据退款批次号修改管理员审核状态(只限支付宝)
	 * @param refundReturn
	 */
	public void updateRefundReturnByBatchNo(RefundReturn refundReturn);
	
	/**
	 * 根据退款id查询退款详情,必传退款id,可传用户id和店铺id,不需要传null
	 * @param refundReturn
	 * @return
	 */
	public RefundReturnDetailVo findRefundReturnDetailVo(RefundReturn refundReturn);
}

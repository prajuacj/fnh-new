package com.leimingtech.service.module.goods.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.leimingtech.core.common.Collections3;
import com.leimingtech.core.entity.GoodsAttribute;
import com.leimingtech.core.entity.GoodsType;
import com.leimingtech.core.entity.base.GoodsAttributeValue;
import com.leimingtech.core.entity.base.TypeBrand;
import com.leimingtech.core.entity.base.TypeSpec;
import com.leimingtech.core.entity.vo.GoodsTypeVO;
import com.leimingtech.service.module.goods.dao.AttributeValueDao;
import com.leimingtech.service.module.goods.dao.GoodsAttributeDao;
import com.leimingtech.service.module.goods.dao.GoodsTypeDao;
import com.leimingtech.service.module.goods.dao.TypeBrandDao;
import com.leimingtech.service.module.goods.dao.TypeSpecDao;
import com.leimingtech.service.module.goods.service.GoodsTypeService;
import com.leimingtech.service.module.goods.vo.GoodsTypeVo;
import com.leimingtech.service.utils.page.Pager;

@Service
public class GoodsTypeServiceImpl implements GoodsTypeService {

	@Resource
	private GoodsTypeDao goodsTypeDao;

	@Resource
	private GoodsAttributeDao attributeDao;

	@Resource
	private TypeBrandDao tbDao;
	@Resource
	private TypeSpecDao tsDao;

	@Resource
	private AttributeValueDao attrValueDao;

	@Override
	public void save(GoodsType goodsType) {
		goodsTypeDao.save(goodsType);
	}

	@Override
	public void update(GoodsType goodsType) {
		goodsTypeDao.update(goodsType);
	}

	@Override
	public void delete(String typeId) {
		goodsTypeDao.delete(typeId);
		// 删除属性
		attributeDao.deleteByType(typeId);
		// 删除属性值
		attributeDao.deleteByType(typeId);
	}

	@Override
	public GoodsType findById(String typeId) {
		return goodsTypeDao.findById(typeId);
	}

	@Override
	public List<GoodsType> findList() {
		return goodsTypeDao.findList();
	}

	@Override
	public GoodsTypeVO selectTypeFetchOther(String typeId) {
		return goodsTypeDao.selectTypeFetchOther(typeId);
	}

	@Override
	public int findCount(Pager pager) {
		return goodsTypeDao.findCount(pager);
	}

	@Override
	public Pager findPageList(Pager pager) {
		List<GoodsType> list = goodsTypeDao.findPageList(pager);
		pager.setResult(list);
		return pager;
	}

	@Override
	public void saveGoodsType(GoodsTypeVo vo) {
		GoodsType type = vo.getGoodsType();
		goodsTypeDao.save(type);
		String typeId = type.getTypeId();
		this.saveOther(vo, typeId);

	}

	@Override
	public void updateGoodsType(GoodsTypeVo vo) {
		// 先删除
		deleteOther(vo.getGoodsType().getTypeId());
		// 修改good_type
		goodsTypeDao.update(vo.getGoodsType());
		// 重新批量插入
		this.saveOther(vo, vo.getGoodsType().getTypeId());
	}

	@Override
	public void updateType(GoodsType type) {
		goodsTypeDao.updateType(type);
	}

	private void deleteOther(String typeId) {

		attrValueDao.deleteBatch(typeId);
		attributeDao.deleteBatch(typeId);
		tbDao.delete(typeId);
		tsDao.delete(typeId);
	}

	/**
	 * 插入其余的表
	 *
	 * @param vo
	 * @param typeId
	 */
	private void saveOther(GoodsTypeVo vo, String typeId) {
		// 重新构造插入good_attribute,shop_attribute_value
		if (Collections3.isNotEmpty(vo.getAttrList())) {
			for (GoodsAttribute attr : vo.getAttrList()) {
				if (!StringUtils.isBlank(attr.getAttrName()) && !StringUtils.isBlank(attr.getAttrValue())
						&& attr.getAttrId() == null) {
					attr.setTypeId(typeId);
					attributeDao.save(attr);
					// 这里应该用批量插入，可惜解决不了返回主键
					for (String value : attr.getAttrValue().split(",")) {
						GoodsAttributeValue attrValue = new GoodsAttributeValue();
						attrValue.setAttrId(attr.getAttrId());
						attrValue.setTypeId(typeId);
						attrValue.setAttrValueName(value);
						attrValueDao.save(attrValue);
					}
				}
			}
		}
		// 重新构造插入good_type_brand
		if (Collections3.isNotEmpty(vo.getBrandList())) {
			List<TypeBrand> tbList = Lists.newArrayList();
			for (TypeBrand tb : vo.getBrandList()) {
				if (tb.getBrandId() != null) {
					tb.setTypeId(typeId);
					tbList.add(tb);
				}
			}
			tbDao.batchSave(tbList);
		}
		// 重新构造插入good_type_spec
		if (Collections3.isNotEmpty(vo.getSpecList())) {
			List<TypeSpec> tsList = Lists.newArrayList();
			for (TypeSpec ts : vo.getSpecList()) {
				if (ts.getSpId() != null) {
					ts.setTypeId(typeId);
					tsList.add(ts);
				}
			}
			tsDao.batchSave(tsList);
		}
	}

	/**
	 * 根据父id查询分类列表
	 *
	 * @param parentid
	 *            为0查询一级分类
	 * @return
	 */
	@Override
	public List<GoodsType> findList2(String parentid) {
		return goodsTypeDao.findList2(parentid);
	}

	/**
	 * 通过父id查询子分类
	 *
	 * @param gtParentId
	 * @return
	 */
	@Override
	public List<GoodsType> findChild(String gtParentId) {
		return goodsTypeDao.findChild(gtParentId);
	}

	@Override
	public int findCountByName(String typeName) {
		return goodsTypeDao.findCountByName(typeName);
	}

}

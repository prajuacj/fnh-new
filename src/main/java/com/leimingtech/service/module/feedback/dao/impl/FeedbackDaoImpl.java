/**
 *
 */
package com.leimingtech.service.module.feedback.dao.impl;

import com.leimingtech.core.common.IdGen;
import com.leimingtech.core.entity.base.Feedback;
import com.leimingtech.service.module.feedback.dao.FeedbackDao;
import com.leimingtech.service.module.feedback.dao.mapper.FeedbackMapper;
import com.leimingtech.service.utils.page.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>Title: feedbackDaoImpl.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2014-2018</p>
 * <p>Company: leimingtech.com</p>
 *
 * @author linjm
 * @version 1.0
 * @date 2015年8月25日
 */

@Repository
public class FeedbackDaoImpl implements FeedbackDao {

    @Autowired
    private FeedbackMapper feedbackMapper;

    @Override
    public void save(Feedback feedback) {
        feedback.setId(IdGen.uuid());
        feedbackMapper.save(feedback);
    }

    @Override
    public void delete(String id) {
        feedbackMapper.delete(id);
    }

    @Override
    public void update(Feedback feedback) {
        feedbackMapper.update(feedback);
    }

    @Override
    public Feedback findbyId(String id) {
        return feedbackMapper.findbyId(id);
    }

    @Override
    public List<Feedback> findBylist() {
        return feedbackMapper.findBylist();
    }

    @Override
    public List<Feedback> findBylist(Pager pager) {
        return feedbackMapper.findPageList(pager);
    }

    @Override
    public int findCount(Feedback feedback) {
        return feedbackMapper.findCount(feedback);
    }

}

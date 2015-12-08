package com.taoren.lb.label.dao.impl;

import com.taoren.lb.base.dao.impl.BaseDaoImpl;
import com.taoren.lb.label.dao.LabelMediaDao;
import com.taoren.model.lb.LabelMedia;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wangshuisheng on 2015/5/27.
 */
@Repository
public class LabelMediaDaoImpl extends BaseDaoImpl implements LabelMediaDao {


    @Override
    protected String getNameSpace() {
        return "LabelMediaMapper.";
    }

    @Autowired
    SqlSession sqlSession;


    public int insertLabelMedia(LabelMedia labelMedia) {
        return sqlSession.insert(getNameSpace() + "insertLabelMedia", labelMedia);
    }

    public int insertLabelMediaList(List<LabelMedia> labelMediaList) {
        return sqlSession.insert(getNameSpace() + "insertLabelMediaList", labelMediaList);
    }

    public LabelMedia selectLabelMediaById(Long id) {
        return sqlSession.selectOne(getNameSpace() + "selectLabelMediaById", id);
    }

    public List<LabelMedia> selectLabelMedia(LabelMedia labelMedia) {
        return sqlSession.selectList(getNameSpace() + "selectLabelMedia", labelMedia);
    }


    public List<LabelMedia> selectLabelMediaList(LabelMedia labelMedia) {
        return sqlSession.selectList(getNameSpace() + "selectLabelMediaList", labelMedia);
    }

    public int updateLabelMedia(LabelMedia labelMedia) {
        return sqlSession.update(getNameSpace() + "updateLabelMedia", labelMedia);
    }

    public int updateLabelMediaList(List<LabelMedia> labelMediaList) {
        return sqlSession.update(getNameSpace() + "updateLabelMediaList", labelMediaList);
    }
    public int deleteLabelMediaById(Long id) {
        return sqlSession.delete(getNameSpace() + "deleteLabelMedia", id);
    }
}

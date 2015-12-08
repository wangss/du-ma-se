package com.taoren.lb.label.dao.impl;

import com.taoren.lb.base.dao.impl.BaseDaoImpl;
import com.taoren.lb.label.dao.LabelDao;
import com.taoren.model.lb.Label;

import com.taoren.model.lb.LabelZan;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


/**
 * Created by wangshuisheng on 2015/5/27.
 */
@Repository
public class LabelDaoImpl extends BaseDaoImpl implements LabelDao {


    @Override
    protected String getNameSpace() {
        return "LabelMapper.";
    }

    @Autowired
    SqlSession sqlSession;


    public int insertLabel(Label label) {
        return sqlSession.insert(getNameSpace() + "insertLabel", label);
    }

    public Label selectLabelById(Long id) {
        return sqlSession.selectOne(getNameSpace() + "selectLabelById", id);
    }

    public List<Label> selectLabel(Label label) {
        return sqlSession.selectList(getNameSpace() + "selectLabel", label);
    }

    public int updateLabel(Label label) {
        return sqlSession.update(getNameSpace() + "updateLabel", label);
    }

    public int deleteLabelById(Long id) {
        return sqlSession.delete(getNameSpace() + "deleteLabelById", id);
    }

    public int deleteLabelByUid(Long uid) {
        return sqlSession.delete(getNameSpace() + "deleteLabelByUid", uid);
    }

    public int updateLabelZan(LabelZan zan) {
        return sqlSession.update(getNameSpace() + "updateLabelZan", zan);
    }

    public Integer labelCount(Map map) {
        return sqlSession.selectOne(getNameSpace() + "labelCount", map);
    }

    public List<Label> labelList(Map map) {
        return sqlSession.selectList(getNameSpace() + "labelList", map);
    }

}

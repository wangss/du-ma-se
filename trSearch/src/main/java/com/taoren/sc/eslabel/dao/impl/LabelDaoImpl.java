package com.taoren.sc.eslabel.dao.impl;
import com.taoren.model.lb.Label;
import com.taoren.sc.base.dao.impl.BaseDaoImpl;

import com.taoren.sc.eslabel.dao.LabelDao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wangshuisheng on 2015/6/11.
 */
@Repository
public class LabelDaoImpl extends BaseDaoImpl implements LabelDao {

    @Autowired
    SqlSession sqlSession;

    @Override
    protected String getNameSpace() {
        return "EsLabelMapper.";
    }

    public Label selectLabelById(Long id) {
        return sqlSession.selectOne(getNameSpace() + "selectLabelById", id);
    }

    public List<Label> selectLabel(Label label) {
        return sqlSession.selectList(getNameSpace() + "selectLabel", label);
    }
}

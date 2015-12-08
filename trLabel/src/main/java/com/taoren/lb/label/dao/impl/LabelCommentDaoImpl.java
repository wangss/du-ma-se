package com.taoren.lb.label.dao.impl;

import com.taoren.lb.base.dao.impl.BaseDaoImpl;
import com.taoren.lb.label.dao.LabelCommentDao;
import com.taoren.model.lb.Label;
import com.taoren.model.lb.LabelComment;
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
public class LabelCommentDaoImpl extends BaseDaoImpl implements LabelCommentDao {


    @Override
    protected String getNameSpace() {
        return "LabelCommentMapper.";
    }

    @Autowired
    SqlSession sqlSession;


    public int insertLabelComment(LabelComment labelComment) {
        return sqlSession.insert(getNameSpace() + "insertLabelComment", labelComment);
    }


    public List<LabelComment> selectLabelComment(LabelComment labelComment) {
        return sqlSession.selectList(getNameSpace() + "selectLabelComment", labelComment);
    }

    public int updateLabelComment(LabelComment labelComment) {
        return sqlSession.update(getNameSpace() + "updateLabelComment", labelComment);
    }

    public int deleteLabelCommentById(Long id) {
        return sqlSession.delete(getNameSpace() + "deleteLabelCommentById", id);
    }

    public int deleteLabelComment(LabelComment labelComment) {
        return sqlSession.delete(getNameSpace() + "deleteLabelComment", labelComment);
    }


    public Integer labelCommentCount(Map map) {
        return sqlSession.selectOne(getNameSpace() + "labelCommentCount", map);
    }


    public List<LabelComment> labelCommentList(Map map) {
        return sqlSession.selectList(getNameSpace() + "labelCommentList", map);
    }

}

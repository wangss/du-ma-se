package com.taoren.lb.label.dao.impl;

import com.taoren.lb.base.dao.impl.BaseDaoImpl;
import com.taoren.lb.label.dao.LabelZanDao;
import com.taoren.model.lb.LabelZan;
import com.taoren.model.lb.LabelZanUser;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wangshuisheng on 2015/7/2.
 */
@Repository
public class LabelZanDaoImpl extends BaseDaoImpl implements LabelZanDao {
    @Override
    protected String getNameSpace() {
        return "LabelZanMapper.";
    }

    @Autowired
    SqlSession sqlSession;


    public int insertZan(LabelZan zan) {
        return sqlSession.insert(getNameSpace() + "insertZan", zan);
    }

    public int deleteZan(LabelZan zan) {
        return sqlSession.delete(getNameSpace() + "deleteZan", zan);
    }


    public List<LabelZanUser> zanUserList(long labelId) {
        return sqlSession.selectList("zanUserList", labelId);
    }
}

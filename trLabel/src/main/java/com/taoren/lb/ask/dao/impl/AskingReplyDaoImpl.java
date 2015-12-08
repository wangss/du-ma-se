package com.taoren.lb.ask.dao.impl;

import com.taoren.lb.ask.dao.AskingReplyDao;
import com.taoren.lb.base.dao.impl.BaseDaoImpl;
import com.taoren.model.ask.AskingReply;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


/**
 * Created by wangshuisheng on 2015/5/27.
 */
@Repository
public class AskingReplyDaoImpl extends BaseDaoImpl implements AskingReplyDao {


    @Override
    protected String getNameSpace() {
        return "AskingReplyMapper.";
    }

    @Autowired
    SqlSession sqlSession;


    public int insertAskingReply(AskingReply askingReply) {
        return sqlSession.insert(getNameSpace() + "insertAskingReply", askingReply);
    }


    public List<AskingReply> selectAskingReply(AskingReply askingReply) {
        return sqlSession.selectList(getNameSpace() + "selectAskingReply", askingReply);
    }

    public int updateAskingReply(AskingReply askingReply) {
        return sqlSession.update(getNameSpace() + "updateAskingReply", askingReply);
    }

    public int deleteAskingReplyById(Long id) {
        return sqlSession.delete(getNameSpace() + "deleteAskingReplyById", id);
    }

    public int deleteAskingReply(AskingReply askingReply) {
        return sqlSession.delete(getNameSpace() + "deleteAskingReply", askingReply);
    }


    public Integer askingReplyCount(Map map) {
        return sqlSession.selectOne(getNameSpace() + "askingReplyCount", map);
    }


    public List<AskingReply> askingReplyList(Map map) {
        return sqlSession.selectList(getNameSpace() + "askingReplyList", map);
    }




}

package com.taoren.lb.ask.dao.impl;

import com.taoren.lb.ask.dao.AskingDao;
import com.taoren.lb.base.dao.impl.BaseDaoImpl;
import com.taoren.model.ask.Asking;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


/**
 * Created by wangshuisheng on 2015/5/27.
 */
@Repository
public class AskingDaoImpl extends BaseDaoImpl implements AskingDao {


    @Override
    protected String getNameSpace() {
        return "AskingMapper.";
    }

    @Autowired
    SqlSession sqlSession;


    public int insertAsking(Asking asking) {
        return sqlSession.insert(getNameSpace() + "insertAsking", asking);
    }

    public Asking selectAskingById(Long id) {
        return sqlSession.selectOne(getNameSpace() + "selectAskingById", id);
    }

    public List<Asking> selectAsking(Asking asking) {
        return sqlSession.selectList(getNameSpace() + "selectAsking", asking);
    }

    public int updateAsking(Asking asking) {
        return sqlSession.update(getNameSpace() + "updateAsking", asking);
    }

    public int deleteAskingById(Long id) {
        return sqlSession.delete(getNameSpace() + "deleteAskingById", id);
    }


    public int deleteAsking(Asking asking) {
        return sqlSession.delete(getNameSpace() + "deleteAsking", asking);
    }

    public Integer askingCount(Map map) {
        return sqlSession.selectOne(getNameSpace() + "askingCount", map);
    }

    public List<Asking> askingList(Map map) {
        return sqlSession.selectList(getNameSpace() + "askingList", map);
    }

}

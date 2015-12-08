package com.taoren.lb.ask.dao.impl;

import com.taoren.lb.ask.dao.AskingMediaDao;
import com.taoren.lb.base.dao.impl.BaseDaoImpl;
import com.taoren.model.ask.AskingMedia;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wangshuisheng on 2015/5/27.
 */
@Repository
public class AskingMediaDaoImpl extends BaseDaoImpl implements AskingMediaDao {


    @Override
    protected String getNameSpace() {
        return "AskingMediaMapper.";
    }

    @Autowired
    SqlSession sqlSession;


    public int insertAskingMedia(AskingMedia askingMedia) {
        return sqlSession.insert(getNameSpace() + "insertAskingMedia", askingMedia);
    }

    public int insertAskingMediaList(List<AskingMedia> askingMediaList) {
        return sqlSession.insert(getNameSpace() + "insertAskingMediaList", askingMediaList);
    }

    public AskingMedia selectAskingMediaById(Long id) {
        return sqlSession.selectOne(getNameSpace() + "selectAskingMediaById", id);
    }

    public List<AskingMedia> selectAskingMedia(AskingMedia askingMedia) {
        return sqlSession.selectList(getNameSpace() + "selectAskingMedia", askingMedia);
    }


    public List<AskingMedia> selectAskingMediaList(AskingMedia askingMedia) {
        return sqlSession.selectList(getNameSpace() + "selectAskingMediaList", askingMedia);
    }

    public int updateAskingMedia(AskingMedia askingMedia) {
        return sqlSession.update(getNameSpace() + "updateAskingMedia", askingMedia);
    }

    public int updateAskingMediaList(List<AskingMedia> askingMediaList) {
        return sqlSession.update(getNameSpace() + "updateAskingMediaList", askingMediaList);
    }
    public int deleteAskingMediaById(Long id) {
        return sqlSession.delete(getNameSpace() + "deleteAskingMedia", id);
    }
}

package com.taoren.sc.esuser.dao;

import com.taoren.sc.base.dao.BaseDao;
import com.taoren.model.user.UserPosition;

import java.util.List;

/**
 * Created by wangshuisheng on 2015/6/11.
 */
public interface UserPositionDao extends BaseDao {


    public UserPosition selectUserPositionByUid(Long id);

    public List<UserPosition> selectUserPosition(UserPosition userPosition);

    public int insertUserPosition(UserPosition userPosition);

    public int updateUserPosition(UserPosition userPosition);

    public int saveUserPosition(UserPosition userPosition);
}

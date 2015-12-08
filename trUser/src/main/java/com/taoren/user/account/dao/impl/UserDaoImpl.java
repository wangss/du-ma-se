package com.taoren.user.account.dao.impl;

import com.taoren.common.constant.RedisConstants;
import com.taoren.user.account.dao.UserDao;
import com.taoren.user.base.dao.impl.BaseDaoImpl;
import com.taoren.model.user.User;
import com.taoren.model.user.UserInfo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by wangshuisheng on 2015/5/27.
 */
@Repository
public class UserDaoImpl extends BaseDaoImpl implements UserDao {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate; // inject the template as ListOperations


    @Resource(name="redisTemplate")
    private ValueOperations<String, Object> valueUserOps;


    @Override
    protected String getNameSpace() {
        return "UserMapper.";
    }

    @Autowired
    SqlSession sqlSession;

    public long getUserId() {
        return sqlSession.selectOne(getNameSpace() + "getUserId");
    }

    public long selectUserHeadId() {
        return sqlSession.selectOne(getNameSpace() + "selectUserHeadId");
    }

    public long selectLabelMediaId() {
        return sqlSession.selectOne(getNameSpace() + "selectLabelMediaId");
    }

    public long selectAskingMediaId() {
        return sqlSession.selectOne(getNameSpace() + "selectAskingMediaId");
    }

    public int insertUser(User user) {
        return sqlSession.insert(getNameSpace() + "insertUser", user);
    }

    public int insertUserInfo(UserInfo userInfo) {
        return sqlSession.insert(getNameSpace() + "insertUserInfo", userInfo);
    }

    public User selectUserById(Long id) {
        return sqlSession.selectOne(getNameSpace() + "selectUser", id);
    }

    public List<User> selectUser(User user) {
        return sqlSession.selectList(getNameSpace() + "selectUser", user);
    }

    public List<User> selectUserId(User user) {
        return sqlSession.selectList(getNameSpace() + "selectUserId", user);
    }

    public UserInfo selectUserInfoById(Long id) {
        return sqlSession.selectOne(getNameSpace() + "selectUserInfoById", id);
    }

    public List<UserInfo> selectUserInfo(User user) {
        return sqlSession.selectList(getNameSpace() + "selectUserInfo", user);
    }


    public Integer userInfoCount(Map map) {
        return sqlSession.selectOne(getNameSpace() + "userInfoCount", map);
    }

    public List<UserInfo> userInfoList(Map map) {
        return sqlSession.selectList(getNameSpace() + "userInfoList", map);
    }

    public int updateUser(User user) {
        return sqlSession.update(getNameSpace() + "updateUser", user);
    }

    public int updateUserTrId(User user) {
        return sqlSession.update(getNameSpace() + "updateUserTrId", user);
    }
    public int updateUserInfo(UserInfo userInfo) {
        return sqlSession.update(getNameSpace() + "updateUserInfo", userInfo);
    }

    public int updatePasswordByPhone(User user) {
        return sqlSession.update(getNameSpace() + "updatePasswordByPhone", user);
    }


    public void setPhoneVerifyCode(String phone, String varifyCode) {

        valueUserOps.set(RedisConstants.PHONE_PRE + phone, varifyCode, 60, TimeUnit.SECONDS);
    }

    public String getPhoneVerifyCode(String phone) {
        return (String)valueUserOps.get(RedisConstants.PHONE_PRE + phone);
    }

    public void setTokenUser(String token, User user) {
        User u = new User();
        u.setId(user.getId());
        u.setTrId(user.getTrId());
        u.setPhone(user.getPhone());
        u.setAreaCode(user.getAreaCode());
        u.setDeviceToken(user.getDeviceToken());
        valueUserOps.set(RedisConstants.TOKEN_PRE + token, u);
    }

    public User getTokenUser(String token) {
        if(token == null){
            return null;
        }
        Object obj = valueUserOps.get(RedisConstants.TOKEN_PRE + token);
        if(obj == null){
            return null;
        }
        return (User)obj;
    }

    public void delTokenUser(String token) {
        redisTemplate.delete(RedisConstants.TOKEN_PRE + token);
    }

    public void setUidToken(long uid, String token) {
        valueUserOps.set(RedisConstants.UID_TOKEN_PRE + uid, token);
    }

    public String getUidToken(long uid) {
        Object obj = valueUserOps.get(RedisConstants.UID_TOKEN_PRE + uid);
        return obj == null ? null : (String)obj;
}

    public void delUidToken(long uid) {
        redisTemplate.delete(RedisConstants.UID_TOKEN_PRE + uid);
    }


    public void setVerifyCodePhone(String verifyCode, String phone) {
        valueUserOps.set(RedisConstants.VERIFYCODE_PHONE_PRE + verifyCode, phone, 60, TimeUnit.SECONDS);
    }

    public String getVerifyCodePhone(String verifyCode) {
        Object obj = valueUserOps.get(RedisConstants.VERIFYCODE_PHONE_PRE + verifyCode);
        return obj == null ? null : (String)obj;
    }

    public void setVerifyUser(String verifyCode, User user) {
        valueUserOps.set(RedisConstants.VERIFYCODE_USER_PRE + verifyCode, user, 90, TimeUnit.SECONDS);
    }

    public User getVerifyUser(String verifyCode) {
        if(verifyCode == null){
            return null;
        }
        Object obj = valueUserOps.get(RedisConstants.VERIFYCODE_USER_PRE + verifyCode);
        if(obj == null){
            return null;
        }
        return (User)obj;
    }
}

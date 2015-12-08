package com.taoren.user.account.dao;

import com.taoren.user.base.dao.BaseDao;
import com.taoren.model.user.User;
import com.taoren.model.user.UserInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by wangshuisheng on 2015/5/27.
 */
public interface UserDao extends BaseDao{


    public long getUserId();

    public long selectUserHeadId();

    public long selectLabelMediaId();

    public long selectAskingMediaId();

    public int insertUser(User user);
    public int insertUserInfo(UserInfo userInfo);

    public User selectUserById(Long id);
    public List<User> selectUser(User user);

    public List<User>  selectUserId(User user);

    public UserInfo selectUserInfoById(Long id);
    public List<UserInfo> selectUserInfo(User user);


    public Integer userInfoCount(Map map);
    public List<UserInfo> userInfoList(Map map);



    public int updateUser(User user);
    public int updateUserTrId(User user);
    public int updateUserInfo(UserInfo userInfo);

    public int updatePasswordByPhone(User user);



    public void setPhoneVerifyCode(String phone, String verifyCode);
    public String getPhoneVerifyCode(String phone);

    public void setTokenUser(String token, User user);
    public User getTokenUser(String token);
    public void delTokenUser(String token);

    public void setUidToken(long uid, String token);
    public String getUidToken(long uid);
    public void delUidToken(long uid);

    //用于用户忘记密码时
    public void setVerifyCodePhone(String verifyCode, String phone);
    public String getVerifyCodePhone(String verifyCode);

    public void setVerifyUser(String verifyCode, User user);
    public User getVerifyUser(String verifyCode);
}

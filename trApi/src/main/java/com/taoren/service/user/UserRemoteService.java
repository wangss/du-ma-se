package com.taoren.service.user;

import com.taoren.model.user.User;
import com.taoren.service.user.model.UserRespDto;

/**
 * Created by wangshuisheng on 2015/5/21.
 */
public interface UserRemoteService {

    /**
     * 通过token获取相应的用户
     * @param token
     * @return
     */
    public User getUserbyToken(String token);
    public String getUserbyToken4Json(String token);

    public String getTokenByUid(long uid);

    /**
     * 修改账号信息
     */
    public UserRespDto editUser(User user);
    public String editUser4Json(String userJson);



    /**
     * 用户修改密码
     */
    public UserRespDto editUserPassword(User user);
    public String editUserPassword4Json(String userJson);

    /**
     * 用户修改手机号
     */
    public UserRespDto editUserPhone(User user);
    public String editUserPhone4Json(String userJson);




}

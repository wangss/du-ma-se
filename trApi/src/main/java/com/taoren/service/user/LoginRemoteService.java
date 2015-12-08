package com.taoren.service.user;

import com.taoren.model.user.User;
import com.taoren.service.base.model.BaseRespDto;
import com.taoren.service.user.model.LoginRespDto;
import com.taoren.service.user.model.LogoutRespDto;

/**
 * Created by wangshuisheng on 2015/5/21.
 */
public interface LoginRemoteService {

    /**
     * 登录
     * @param user
     * @return
     */
    public LoginRespDto login(User user);
    public String login4Json(String userJson);

    /**
     * 管理员登录
     * @param user
     * @return
     */
    public LoginRespDto loginAdmin(User user);
    public String loginAdmin4Json(String userJson);

    /**
     * 退出
     * @param user
     * @return
     */
    public LogoutRespDto logout(User user);
    public String logout4Json(String userJson);



    /**
     * 忘记密码时修改用户密码
     * @param user
     * @return
     */
    public BaseRespDto passwordForget(User user);
    public String passwordForget4Json(String userJson);
}

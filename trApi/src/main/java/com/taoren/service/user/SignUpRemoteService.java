package com.taoren.service.user;

import com.taoren.model.user.UserInfo;
import com.taoren.service.user.model.NewPhoneRespDto;
import com.taoren.service.user.model.NewUserRespDto;

/**
 * Created by Administrator on 2015/5/19.
 */
public interface SignUpRemoteService {

    /**
     * 给手机发送验证码
     * @param phone
     * @return
     */
    public NewPhoneRespDto verifyNewPhone(int areaCode, String phone);

    /**
     * 给手机发送验证码
     * @param phone
     * @return
     */
    public String verifyNewPhone4Json(int areaCode, String phone);

    /**
     * 新生成用户
     * @param userInfo
     * @return
     */
    public NewUserRespDto createNewAccount(UserInfo userInfo);

    /**
     * 新生成用户
     * @param userInfo
     * @return
     */
    public String createNewAccount4Json(String userInfo);

}

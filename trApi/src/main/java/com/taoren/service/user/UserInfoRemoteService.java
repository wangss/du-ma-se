package com.taoren.service.user;

import com.taoren.model.user.UserInfo;
import com.taoren.service.user.model.UserInfoListRespDto;
import com.taoren.service.user.model.UserInfoRespDto;

import java.util.Map;

/**
 * Created by wangshuisheng on 2015/5/21.
 */
public interface UserInfoRemoteService {


    /**
     * 用户查看自己信息
     */
    public UserInfoRespDto getUserInfo(long uid);
    public String getUserInfo4Json(long uid);

//    /**
//     * 显示他人信息
//     */
//    public UserInfo getUserShow(long uid);
//    public String getUserShow4Json(long uid);
//
//    /**
//     * 查看用户详细信息
//     */
//    public UserInfo getUserDetail(long uid);
//    public String getUserDetail4Json(long uid);

    /**
     * 用户修改资料
     */
    public UserInfoRespDto editUserInfo(UserInfo userInfo);
    public String editUserInfo4Json(String userJson);

    /**
     * 用户资料分页
     * @param map
     * @return
     */
    public UserInfoListRespDto userInfoList(Map map);
    public String userInfoList4Json(String paramJson);

}

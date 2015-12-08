package com.taoren.user.account.service.impl;

import com.taoren.common.constant.CodeMsgConstants;
import com.taoren.common.util.StringUtils;
import com.taoren.common.util.TaorenUtils;
import com.taoren.model.user.User;
import com.taoren.model.user.UserInfo;
import com.taoren.service.user.UserInfoRemoteService;
import com.taoren.service.user.UserRemoteService;
import com.taoren.service.user.model.UserInfoListRespDto;
import com.taoren.service.user.model.UserInfoRespDto;
import com.taoren.user.account.dao.UserDao;
import com.taoren.user.easemob.apiUtil.utils.EasemobUtil;
import com.taoren.user.easemob.dao.EasemobDao;
import com.taoren.user.mob.utils.MobUtil;
import com.taoren.user.relation.dao.UserReportDao;
import com.taoren.user.util.http.SecretUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangshuisheng on 2015/5/26.
 */
@Service
public class UserInfoServiceImpl implements UserInfoRemoteService {

    Logger logger = LoggerFactory.getLogger(UserInfoServiceImpl.class);

    @Autowired
    private UserDao userDao;


//    /**
//     * 查看用户信息
//     * @param uid
//     * @return
//     */
//    public UserInfo getUserShow(long uid) {
//        UserInfo userInfo = userDao.selectUserInfoById(uid);
//        userInfo.removeSomePrivacy();
//        return userInfo;
//    }
//
//    /**
//     * 查看用户信息
//     * @param uid
//     * @return
//     */
//    public String getUserShow4Json(long uid) {
//        return TaorenUtils.o2j(getUserShow(uid));
//    }

//    /**
//     * 用户所有信息
//     * @param uid
//     * @return
//     */
//    public UserInfo getUserDetail(long uid) {
//        return userDao.selectUserInfoById(uid);
//    }
//
//    /**
//     * 用户所有信息
//     * @param uid
//     * @return
//     */
//    public String getUserDetail4Json(long uid) {
//        return TaorenUtils.o2j(getUserDetail(uid));
//    }


    /**
     * 获取用户个人信息
     * @param uid
     * @return
     */
    public UserInfoRespDto getUserInfo(long uid) {

        UserInfoRespDto respDto = new UserInfoRespDto();

        UserInfo userInfo = userDao.selectUserInfoById(uid);

//        userInfo.removeSomeUnneeded();
        respDto.setUserInfo(userInfo);
        respDto.setResultCode(CodeMsgConstants.USERINFO_R_C_SUCCESS);
        respDto.setTime(new Date());
        respDto.setMsg(CodeMsgConstants.USERINFO_MSG_GETSUCCESS);

        return respDto;
    }

    /**
     * 获取用户个人信息
     * @param uid
     * @return
     */
    public String getUserInfo4Json(long uid) {
        return TaorenUtils.o2j(getUserInfo(uid));
    }


    /**
     * 用户修改个人信息
     * @param userInfo
     * @return
     */
    public UserInfoRespDto editUserInfo(UserInfo userInfo) {

        UserInfoRespDto respDto = new UserInfoRespDto();

        int i = 0;
        if(!StringUtils.isEmpty(userInfo.getTrId())){
            i = userDao.updateUserTrId(userInfo);
        }else{

            i = userDao.updateUserInfo(userInfo);
            if(i < 1){
                if(userInfo.getUid() != null){

                    if(userInfo.getPrivacyFind() == null){
                        userInfo.setPrivacyFind(1);//默认为1
                    }

                    if(userInfo.getPrivacyStrangerTalk() == null){
                        userInfo.setPrivacyStrangerTalk(1);//默认为1
                    }

                    i = userDao.insertUserInfo(userInfo);
                }
            }
        }

        if(i > 0){
            respDto.setResultCode(CodeMsgConstants.USERINFO_R_C_SUCCESS);
            respDto.setTime(new Date());
            respDto.setMsg(CodeMsgConstants.USERINFO_MSG_EDITSUCCESS);
        }else{
            respDto.setResultCode(CodeMsgConstants.USERINFO_R_C_FAIL);
            respDto.setTime(new Date());
            respDto.setMsg(CodeMsgConstants.USERINFO_E_MSG_EDITFAIL);
        }

        return respDto;
    }

    /**
     * 用户修改个人信息
     * @param userInfoJson
     * @return
     */
    public String editUserInfo4Json(String userInfoJson) {
        return TaorenUtils.o2j(TaorenUtils.j2o(userInfoJson, UserInfo.class));
    }


    public UserInfoListRespDto userInfoList(Map map) {
        UserInfoListRespDto respDto = new UserInfoListRespDto();
        try{
            if(map.get("page_size") == null){
                map.put("page_size", 10);
            }
            if(map.get("curr_page") == null){
                map.put("curr_page", 1);
            }
            int curr_page  = (Integer)map.get("curr_page");
            int page_size = (Integer)map.get("page_size");
            map.put("start_row", (curr_page - 1) * page_size);

            respDto.setTotalRow(userDao.userInfoCount(map));
            respDto.setList(userDao.userInfoList(map));
            respDto.setResultCode(1);
        }catch (Exception e){
            e.printStackTrace();
            respDto.setResultCode(2);
        }

        return respDto;
    }

    public String userInfoList4Json(String paramJson) {
        return TaorenUtils.o2j(TaorenUtils.j2o(paramJson, HashMap.class));
    }
}

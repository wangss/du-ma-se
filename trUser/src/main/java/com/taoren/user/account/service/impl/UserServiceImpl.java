package com.taoren.user.account.service.impl;

import com.taoren.common.constant.CodeMsgConstants;
import com.taoren.common.util.StringUtils;
import com.taoren.common.util.TaorenUtils;
import com.taoren.service.user.UserRemoteService;
import com.taoren.service.user.model.UserInfoListRespDto;
import com.taoren.service.user.model.UserInfoRespDto;
import com.taoren.service.user.model.UserRespDto;
import com.taoren.user.account.dao.UserDao;
import com.taoren.user.easemob.apiUtil.utils.EasemobUtil;
import com.taoren.user.easemob.dao.EasemobDao;
import com.taoren.user.mob.utils.MobUtil;
import com.taoren.model.user.User;
import com.taoren.model.user.UserInfo;
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
public class UserServiceImpl implements UserRemoteService {

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;


    @Autowired
    private EasemobDao easemobDao;

    public User getUserbyToken(String token) {
        return userDao.getTokenUser(token);
    }

    public String getUserbyToken4Json(String token) {
        return TaorenUtils.o2j(getUserbyToken(token));
    }

    public String getTokenByUid(long uid) {
        return userDao.getUidToken(uid);
    }

    public UserRespDto editUser(User user) {

        UserRespDto respDto = new UserRespDto();

        int i = userDao.updateUser(user);

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

    public String editUser4Json(String userJson) {
        return TaorenUtils.o2j(TaorenUtils.j2o(userJson, User.class));

    }



    /**
     * 修改密码
     * @param user
     * @return
     */
    public UserRespDto editUserPassword(User user) {
        UserRespDto respDto = new UserRespDto();

        //检查参数是否齐全
        if(StringUtils.isEmpty(user.getPhone())
                || StringUtils.isEmpty(user.getPassword())
                || StringUtils.isEmpty(user.getPasswordNew())){
            respDto.setResultCode(CodeMsgConstants.PASSWORD_R_C_FAIL);
            respDto.setErrorCode(CodeMsgConstants.PASSWORD_E_C_PARAMATERERROR);
            respDto.setMsg(CodeMsgConstants.PASSWORD_E_MSG_PARAMATERERROR);
            return respDto;

        }

        user.setPassword(SecretUtils.taoRenPassword(user.getPassword()));

        //如果密码正确
        boolean editFail = false;
        List<User> userList = userDao.selectUser(user);
        if (userList != null && userList.size() > 0) {
            User u = new User();
            u.setId(user.getId());
            u.setPassword(SecretUtils.taoRenPassword(user.getPasswordNew()));
            //修改成功
            if (userDao.updateUser(u) > 0) {
                respDto.setResultCode(CodeMsgConstants.PASSWORD_R_C_SUCCESS);
                respDto.setTime(new Date());
                respDto.setMsg(CodeMsgConstants.PASSWORD_MSG_RESETSUCCESS);

                //修改环信密码
                String huanxinToken = easemobDao.getAppToken();
                if(huanxinToken == null){
                    huanxinToken = easemobDao.getAppToken();
                }
                EasemobUtil.modifyIMUserPassword("tr_" + user.getId(), SecretUtils.easemobPassword(user.getPasswordNew()), huanxinToken);

            } else {
                respDto.setResultCode(CodeMsgConstants.PASSWORD_R_C_FAIL);
                respDto.setErrorCode(CodeMsgConstants.PASSWORD_E_C_RESETFAIL);
                respDto.setMsg(CodeMsgConstants.PASSWORD_E_MSG_RESETFAIL);
            }
        } else {
            respDto.setResultCode(CodeMsgConstants.PASSWORD_R_C_FAIL);
            respDto.setErrorCode(CodeMsgConstants.PASSWORD_E_C_PASSWORDERROR);
            respDto.setMsg(CodeMsgConstants.PASSWORD_E_MSG_PASSWORDERROR);
        }

        return respDto;
    }

    /**
     * 修改密码
     * @param userJson
     * @return
     */
    public String editUserPassword4Json(String userJson) {
        return TaorenUtils.o2j(editUserPassword(TaorenUtils.j2o(userJson, User.class)));
    }

    public UserRespDto editUserPhone(User user) {
        UserRespDto respDto = new UserRespDto();

        //检查参数是否齐全(对于服务组件来说，此3个参数才是必须的)
        if(StringUtils.isEmpty(user.getPhone())
                || StringUtils.isEmpty(user.getVerifyCode())){

            respDto.setResultCode(CodeMsgConstants.NEWACCOUNT_R_C_FAIL);
            respDto.setErrorCode(CodeMsgConstants.NEWACCOUNT_E_C_PARAMATERERROR);//1参数缺失
            respDto.setMsg(CodeMsgConstants.NEWACCOUNT_E_MSG_PARAMATERERROR);

            return respDto;
        }

        if(!MobUtil.verifyCode(user.getPhone(), user.getAreaCode(), user.getVerifyCode())){
            respDto.setResultCode(CodeMsgConstants.NEWACCOUNT_R_C_FAIL);
            respDto.setErrorCode(CodeMsgConstants.NEWACCOUNT_E_C_VARIFYCODEERROR);
            respDto.setMsg(CodeMsgConstants.NEWACCOUNT_E_MSG_VARIFYCODEERROR);

        }else{

            user.setTrId(null);
            int i = userDao.updateUser(user);
            if(i > 0){
                respDto.setResultCode(CodeMsgConstants.USERINFO_R_C_SUCCESS);
                respDto.setTime(new Date());
                respDto.setMsg(CodeMsgConstants.USERINFO_MSG_EDITSUCCESS);
            }else{
                respDto.setResultCode(CodeMsgConstants.USERINFO_R_C_FAIL);
                respDto.setTime(new Date());
                respDto.setMsg(CodeMsgConstants.USERINFO_E_MSG_EDITFAIL);
            }

        }
        return respDto;
    }

    public String editUserPhone4Json(String userJson) {
        return TaorenUtils.o2j(editUserPhone(TaorenUtils.j2o(userJson, User.class)));
    }


}

package com.taoren.user.account.service.impl;

import com.taoren.common.constant.CodeMsgConstants;
import com.taoren.common.util.StringUtils;
import com.taoren.common.util.TaorenUtils;
import com.taoren.service.base.model.BaseRespDto;
import com.taoren.service.user.LoginRemoteService;
import com.taoren.service.user.model.LoginRespDto;
import com.taoren.service.user.model.LogoutRespDto;
import com.taoren.user.account.dao.UserDao;
import com.taoren.user.easemob.apiUtil.utils.EasemobUtil;
import com.taoren.user.easemob.dao.EasemobDao;
import com.taoren.user.mob.utils.MobUtil;
import com.taoren.model.user.User;
import com.taoren.model.user.UserInfo;
import com.taoren.user.util.http.SecretUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


/**
 * Created by Administrator on 2015/5/25.
 */
@Service
public class LoginServiceImpl implements LoginRemoteService {

    Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Autowired
    private EasemobDao easemobDao;



    /**
     * 登录
     * @param user
     * @return
     */
    public LoginRespDto login(User user) {
        LoginRespDto respDto = new LoginRespDto();

        //验证用户密码
        String phone = user.getPhone();
        String trId = user.getTrId();
        String originalpassword = user.getPassword();

        //phone 与 trId必须二选一， 密码不能为空
        if((StringUtils.isEmpty(phone) && StringUtils.isEmpty(trId))
                || StringUtils.isEmpty(originalpassword)){
            respDto.setResultCode(CodeMsgConstants.LOGIN_R_C_FAIL);
            respDto.setErrorCode(CodeMsgConstants.LOGIN_E_C_ACCOUNTERROR);//1用户名密码不能为空;3其它；
            respDto.setMsg(CodeMsgConstants.LOGIN_E_MSG_ACCOUNTERROR);

            return respDto;
        }


        user.setPassword(null);

        List<UserInfo> userInfoList = userDao.selectUserInfo(user);

        if(userInfoList == null || userInfoList.size() <=0
                || userInfoList.get(0) == null
                || !userInfoList.get(0).getPassword().equals(SecretUtils.taoRenPassword(originalpassword))
                || userInfoList.get(0).getStatus() != 1){

            respDto.setResultCode(CodeMsgConstants.LOGIN_R_C_FAIL);
            respDto.setErrorCode(CodeMsgConstants.LOGIN_E_C_ACCOUNTERROR);
            respDto.setMsg(CodeMsgConstants.LOGIN_E_MSG_ACCOUNTERROR);

        }else{

            UserInfo userInfo = userInfoList.get(0);

            if(userInfo.getEasemobStatus() == 0){
                try{
                    String huanxinToken = easemobDao.getAppToken();

                    int easemobStatus = 1;//创建ok

                    //环信用户创建ok
                    if(EasemobUtil.createNewIMUser("tr_" + userInfo.getUid(), SecretUtils.easemobPassword(originalpassword), huanxinToken)) {
                        User u = new User();
                        u.setId(userInfo.getUid());
                        u.setEasemobStatus(1);
                        userDao.updateUser(user);
                    }
                }catch (Exception e){
                    //do nothing
                }

            }

            //生成用户校验token放入redis缓存中
            String token = TaorenUtils.getUUIDnoDash();

            user.setId(userInfo.getUid());
            user.setTrId(userInfo.getTrId());
            user.setPhone(userInfo.getPhone());
            user.setAreaCode(userInfo.getAreaCode());
            user.setDeviceToken(user.getDeviceToken());
            userDao.setTokenUser(token, user);

            String oldToken = userDao.getUidToken(userInfo.getUid());
            if(oldToken != null){
                userDao.delTokenUser(oldToken);
                userDao.delUidToken(userInfo.getUid());
            }
            userDao.setUidToken(userInfo.getUid(), token);

            userInfo.removeSomeUnneeded();
            userInfo.setToken(token);

            respDto.setUserInfo(userInfo);
            respDto.setResultCode(CodeMsgConstants.LOGIN_R_C_SUCCESS);
            respDto.setTime(new Date());
            respDto.setMsg(CodeMsgConstants.LOGIN_MSG_lOGINSUCCESS);

        }
        
        return respDto;
    }


    /**
     * 登录
     * @param userJson
     * @return
     */
    public String login4Json(String userJson) {

        User user = TaorenUtils.j2o(userJson, User.class);

        return TaorenUtils.o2j(login(user));
    }

    /**
     * 退出
     * @param user
     * @return
     */
    public LogoutRespDto logout(User user) {

        LogoutRespDto respDto = new LogoutRespDto();

        userDao.delTokenUser(user.getToken());
        userDao.delUidToken(user.getId());

        respDto.setResultCode(CodeMsgConstants.LOGOUT_R_C_SUCCESS);
        respDto.setMsg(CodeMsgConstants.LOGOUT_MSG_lOGOUTSUCCESS);
        respDto.setTime(new Date());

        return respDto;
    }

    /**
     * 退出
     * @param userJson
     * @return
     */
    public String logout4Json(String userJson) {
        User user = TaorenUtils.j2o(userJson, User.class);
        return TaorenUtils.o2j(login(user));
    }

    /**
     * 忘记密码时修改密码
     * @param user
     * @return
     */
    public BaseRespDto passwordForget(User user) {

        BaseRespDto respDto = new BaseRespDto();

        //检查参数是否齐全(对于服务组件来说，此3个参数才是必须的)
        if(StringUtils.isEmpty(user.getPassword())
                || StringUtils.isEmpty(user.getPhone())
                || StringUtils.isEmpty(user.getVerifyCode())){
            respDto.setResultCode(CodeMsgConstants.PASSWORD_R_C_FAIL);
            respDto.setErrorCode(CodeMsgConstants.PASSWORD_E_C_PARAMATERERROR);//1参数缺失
            respDto.setMsg(CodeMsgConstants.PASSWORD_E_MSG_PARAMATERERROR);

            return respDto;
        }

        if(!(MobUtil.verifyCode(user.getPhone(), user.getAreaCode(), user.getVerifyCode()))){
            respDto.setResultCode(CodeMsgConstants.PASSWORD_R_C_FAIL);
            respDto.setErrorCode(CodeMsgConstants.PASSWORD_E_C_VARIFYCODEERROR);//1参数缺失; 2验证码错误；3其它；
            respDto.setMsg(CodeMsgConstants.PASSWORD_E_MSG_VARIFYCODEERROR);

        }else{
            String password = user.getPassword();

            user.setPassword(SecretUtils.taoRenPassword(user.getPassword()));
            boolean fail = false;
            if(userDao.updatePasswordByPhone(user) == 1){
                List<User> users = userDao.selectUserId(user);
                if(users != null && users.size()>0){
                     User u = users.get(0);
                    String huanxinToken = easemobDao.getAppToken();
                    if(huanxinToken == null){
                        huanxinToken = easemobDao.getAppToken();
                    }
                    EasemobUtil.modifyIMUserPassword("tr_" + u.getId(), SecretUtils.easemobPassword(password), huanxinToken);

                    respDto.setResultCode(CodeMsgConstants.PASSWORD_R_C_SUCCESS);
                    respDto.setMsg(CodeMsgConstants.PASSWORD_MSG_RESETSUCCESS);
                    respDto.setTime(new Date());
                }

            }else{
                fail = true;
            }

            if(fail){
                respDto.setResultCode(CodeMsgConstants.PASSWORD_R_C_FAIL);
                respDto.setMsg(CodeMsgConstants.USERINFO_E_MSG_EDITFAIL);
            }
        }

        return respDto;
    }


    /**
     * 忘记密码时修改密码
     * @param userJson
     * @return
     */
    public String passwordForget4Json(String userJson) {
        User user = TaorenUtils.j2o(userJson, User.class);
        return TaorenUtils.o2j(passwordForget(user));
    }


    /**
     * 管理员登录, 只能通过trId登录
     * @param user
     * @return
     */
    public LoginRespDto loginAdmin(User user) {
        LoginRespDto respDto = new LoginRespDto();

        User u = new User();
        u.setTrId(user.getTrId());
        List<UserInfo> userInfoList = userDao.selectUserInfo(u);

        if(userInfoList == null || userInfoList.size() <=0
                || userInfoList.get(0) == null
                || !userInfoList.get(0).getPassword().equals(SecretUtils.taoRenPassword(user.getPassword()))
                || userInfoList.get(0).getStatus() != 1
                || userInfoList.get(0).getUserType() == 1){

            respDto.setResultCode(CodeMsgConstants.LOGIN_R_C_FAIL);
            respDto.setErrorCode(CodeMsgConstants.LOGIN_E_C_ACCOUNTERROR);
            respDto.setMsg(CodeMsgConstants.LOGIN_E_MSG_ACCOUNTERROR);

        }else{

            UserInfo userInfo = userInfoList.get(0);

            respDto.setUserInfo(userInfo);
            respDto.setResultCode(CodeMsgConstants.LOGIN_R_C_SUCCESS);
            respDto.setTime(new Date());
            respDto.setMsg(CodeMsgConstants.LOGIN_MSG_lOGINSUCCESS);

        }

        return respDto;
    }


    /**
     * 管理员登录, 只能通过trId登录 4Json
     * @param userJson
     * @return
     */
    public String loginAdmin4Json(String userJson) {
        User user = TaorenUtils.j2o(userJson, User.class);
        return TaorenUtils.o2j(loginAdmin(user));
    }


}

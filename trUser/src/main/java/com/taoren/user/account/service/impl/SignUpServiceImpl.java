package com.taoren.user.account.service.impl;

import com.taoren.common.constant.CodeMsgConstants;
import com.taoren.common.util.StringUtils;
import com.taoren.common.util.TaorenUtils;
import com.taoren.service.user.SignUpRemoteService;
import com.taoren.service.user.model.NewPhoneRespDto;
import com.taoren.service.user.model.NewUserRespDto;
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
 * Created by Administrator on 2015/5/19.
 */
@Service
public class SignUpServiceImpl implements SignUpRemoteService {

    Logger logger = LoggerFactory.getLogger(SignUpServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Autowired
    private EasemobDao easemobDao;


    /**
     * 注册新号手机验证
     * @param phone
     * @return
     */
    public NewPhoneRespDto verifyNewPhone(int areaCode, String phone) {
        NewPhoneRespDto respDto = new NewPhoneRespDto();

        //先检查该手机号是否已经注册
        User user = new User();
        user.setAreaCode(areaCode);
        user.setPhone(phone);
        List<User> userList = userDao.selectUserId(user);

        if(userList != null && userList.size() > 0){
            respDto.setCanRegister(false);
            respDto.setMsg(CodeMsgConstants.NEWPHONE_E_MSG_ALREADYREGISTERED);

        }else{
            respDto.setCanRegister(true);
        }
        return respDto;
    }

    /**
     * 注册新号手机验证4Json
     * @param phone
     * @return
     */
    public String verifyNewPhone4Json(int areaCode, String phone) {
        return TaorenUtils.o2j(verifyNewPhone(areaCode, phone));
    }

    /**
     * 创建新帐户
     * @param userInfo
     * @return
     */
    public NewUserRespDto createNewAccount(UserInfo userInfo) {

        NewUserRespDto respDto = new NewUserRespDto();

        //检查参数是否齐全(对于服务组件来说，此3个参数才是必须的)
        if(StringUtils.isEmpty(userInfo.getPhone())
                || StringUtils.isEmpty(userInfo.getPassword())
                || StringUtils.isEmpty(userInfo.getVerifyCode())){
            respDto.setResultCode(CodeMsgConstants.NEWACCOUNT_R_C_FAIL);
            respDto.setErrorCode(CodeMsgConstants.NEWACCOUNT_E_C_PARAMATERERROR);//1参数缺失
            respDto.setMsg(CodeMsgConstants.NEWACCOUNT_E_MSG_PARAMATERERROR);

            return respDto;
        }

        User u = new User();
        u.setAreaCode(userInfo.getAreaCode());
        u.setPhone(userInfo.getPhone());
        List<User> userList = userDao.selectUserId(u);

        if(userList != null && userList.size() > 0) {
            respDto.setResultCode(CodeMsgConstants.NEWACCOUNT_R_C_FAIL);
            respDto.setMsg(CodeMsgConstants.NEWACCOUNT_E_MSG_REGISTEREFAIL);
            return respDto;
        }

        //验证码
        if(!MobUtil.verifyCode(userInfo.getPhone(), userInfo.getAreaCode(), userInfo.getVerifyCode())){
            respDto.setResultCode(CodeMsgConstants.NEWACCOUNT_R_C_FAIL);
            respDto.setErrorCode(CodeMsgConstants.NEWACCOUNT_E_C_VARIFYCODEERROR);
            respDto.setMsg(CodeMsgConstants.NEWACCOUNT_E_MSG_VARIFYCODEERROR);

        }else{

            long userId = userDao.getUserId();//此处可以优化
            //顺序创建环信用户
            int easemobStatus = 1;//创建ok
            try{
                String huanxinToken = easemobDao.getAppToken();
                //环信用户创建ok
                if(!EasemobUtil.createNewIMUser("tr_" + userId, SecretUtils.easemobPassword(userInfo.getPassword()), huanxinToken)) {
                    easemobStatus = 0;
                }
            }catch (Exception e){
                e.printStackTrace();
                easemobStatus = 0;
            }


            User user = new User();
            user.setId(userId);
            user.setPhone(userInfo.getPhone());
            user.setRegisterPhone(userInfo.getPhone());
            user.setAreaCode(userInfo.getAreaCode());
            user.setDeviceType(userInfo.getDeviceType());
            user.setLongitude(userInfo.getLongitude());
            user.setLatitude(userInfo.getLatitude());
            user.setArea(userInfo.getArea());

            user.setPassword(SecretUtils.taoRenPassword(userInfo.getPassword()));
            user.setStatus(1);//默认为1，即正常用户，不能缺少
            user.setUserType(1);
            user.setEasemobStatus(easemobStatus);
            userDao.insertUser(user);

            //用户详情
            try{
                userInfo.setId(userId);
                userInfo.setUid(userId);

                userInfo.setPrivacyFind(1);//默认为1
                userInfo.setPrivacyStrangerTalk(1);//默认为1

                userDao.insertUserInfo(userInfo);
            }catch (Exception e){
                e.printStackTrace();
            }


            String token = TaorenUtils.getUUIDnoDash();
            user.setDeviceToken(userInfo.getDeviceToken());
            userDao.setTokenUser(token, user);
            userDao.setUidToken(userId, token);

            //生成token放入缓存
            respDto.setToken(token);
            respDto.setResultCode(CodeMsgConstants.NEWACCOUNT_R_C_SUCCESS);
            respDto.setUid(user.getId());
            respDto.setMsg(CodeMsgConstants.NEWACCOUNT_MSG_REGISTESUCCESS);
            respDto.setTime(new Date());

        }

        return respDto;
    }

    /**
     * 创建新帐户4Json
     * @param userJson
     * @return
     */
    public String createNewAccount4Json(String userJson) {

        UserInfo userInfo = TaorenUtils.j2o(userJson, UserInfo.class);

        return TaorenUtils.o2j(createNewAccount(userInfo));
    }
}

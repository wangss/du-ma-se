package com.taoren.common.constant;

/**
 * Created by wangshuisheng on 2015/6/8.
 */
public class CodeMsgConstants {

    /**
     * 说明：为简单起见
     *       R表示 RESULT
     *       E表示 ERROR
     *       C表示 Code
     */

    public static final int SYSTEM_R_C_ERROR = 3;//
    public static final String SYSTEM_E_MSG = "System Error... ";//系统出错，请重试


    public static final int SYSTEM_R_C_ILLEGALREQUEST = 4;//
    public static final String SYSTEM_MSG_ILLEGALREQUEST = "illegle request";//鉴权失败，或已退出，请重新登录

    //注册时手机号验证
    public static final int NEWPHONE_R_C_SUCCESS = 1;
    public static final String NEWPHONE_MSG_SENDCODESUCCESS = "send varifyCdoe success";//发送验证码成功

    public static final int NEWPHONE_R_C_FAIL = 2;

    public static final int NEWPHONE_E_C_PHONEERROR = 1;
    public static final String NEWPHONE_E_MSG_PHONEERROR = "phone error";//手机号错误

    public static final int NEWPHONE_E_C_ALREADYREGISTERED = 2;
    public static final String NEWPHONE_E_MSG_ALREADYREGISTERED = "already registed";//手机号已经注册

    public static final int NEWPHONE_E_C_SENDCODEERROR = 3;
    public static final String NEWPHONE_E_MSG_SENDCODEERROR = "send verifyCode error";//发送验证码出错



    //注册新帐号相关
    public static final int NEWACCOUNT_R_C_SUCCESS = 1;
    public static final String NEWACCOUNT_MSG_REGISTESUCCESS = "Registed success";//注册成功

    public static final int NEWACCOUNT_R_C_FAIL = 2;

    public static final int NEWACCOUNT_E_C_PARAMATERERROR = 1;
    public static final String NEWACCOUNT_E_MSG_PARAMATERERROR = "parameter error";//参数缺失

    public static final int NEWACCOUNT_E_C_VARIFYCODEERROR = 2;
    public static final String NEWACCOUNT_E_MSG_VARIFYCODEERROR = "verifyCode error";//验证码错误

    public static final int NEWACCOUNT_E_C_REGISTEREFAIL = 3;
    public static final String NEWACCOUNT_E_MSG_REGISTEREFAIL = "registed fail";//注册失败


    //用户登录
    public static final int LOGIN_R_C_SUCCESS = 1;
    public static final String LOGIN_MSG_lOGINSUCCESS = "login success";//登录成功

    public static final int LOGIN_R_C_FAIL = 2;

    public static final int LOGIN_E_C_ACCOUNTERROR = 1;
    public static final String LOGIN_E_MSG_ACCOUNTERROR = "account error";//用户名密码有误


    //用户退出
    public static final int LOGOUT_R_C_SUCCESS = 1;
    public static final String LOGOUT_MSG_lOGOUTSUCCESS = "logout success";//退出成功

    public static final int LOGOUT_R_C_FAIL = 2;

    public static final int LOGOUT_E_C_LOGOUTFAIL = 1;
    public static final String LOGOUT_E_MSG_ILLEGALREQUEST = "";//退出失败


    //忘记密码时注册码相关
    public static final int PASSWORD_VERIFY_R_C_SUCCESS = 1;
    public static final String PASSWORD_VERIFY_MSG_SENDCODESUCCESS = "send code success";//发送验证码成功

    public static final int PASSWORD_VERIFY_R_C_FAIL = 2;

    public static final int PASSWORD_VERIFY_E_C_PHONEERROR = 1;
    public static final String PASSWORD_VERIFY_E_MSG_PHONEERROR = "phone error";//手机号错误

    public static final int PASSWORD_VERIFY_E_C_USERNOTEXISTED = 2;
    public static final String PASSWORD_VERIFY_E_MSG_USERNOTEXISTED = "user not existed";//该手机号未注册

    public static final int PASSWORD_VERIFY_E_C_SENDCODEERROR = 3;
    public static final String PASSWORD_VERIFY_E_MSG_SENDCODEERROR = "send code error";//发送验证码出错


    //忘记密码时更改密码
    public static final int PASSWORD_R_C_SUCCESS = 1;
    public static final String PASSWORD_MSG_RESETSUCCESS = "Reset success";//修改成功

    public static final int PASSWORD_R_C_FAIL = 2;

    public static final int PASSWORD_E_C_PARAMATERERROR = 1;
    public static final String PASSWORD_E_MSG_PARAMATERERROR = "parameter error";//参数缺失

    public static final int PASSWORD_E_C_VARIFYCODEERROR = 2;
    public static final String PASSWORD_E_MSG_VARIFYCODEERROR = "varifyCode error";//验证码错误

    public static final int PASSWORD_E_C_PASSWORDERROR = 3;
    public static final String PASSWORD_E_MSG_PASSWORDERROR = "password error";//密码错误

    public static final int PASSWORD_E_C_RESETFAIL = 4;
    public static final String PASSWORD_E_MSG_RESETFAIL = "reset fail";//修改失败



    //用户信息

    public static final int USERINFO_R_C_SUCCESS = 1;
    public static final String USERINFO_MSG_GETSUCCESS = "Get success";//获取成功
    public static final String USERINFO_MSG_EDITSUCCESS = "Edit success";//修改成功


    public static final int USERINFO_R_C_FAIL = 2;

    public static final int USERINFO_E_C_FAIL = 2;
    public static final String USERINFO_E_MSG_GETFAIL = "Get fail";//获取失败
    public static final String USERINFO_E_MSG_EDITFAIL = "Edit fail";//修改失败

    public static final int USERINFO_E_C_TRIDAlREADYHAVE = 3;
    public static final String USERINFO_E_MSG_TRIDAlREADYHAVE = "Can't modify TrId";//不能修改淘人号

    public static final int USERINFO_E_C_TRIDTAKEN = 4;
    public static final String USERINFO_E_MSG_TRIDTAKEN = "TrId already be taken";//淘人号已经被占用


    //添加标签
    public static final int LABEL_R_C_SUCCESS = 1;
    public static final String LABEL_MSG_ADDSUCCESS = "Add success";//添加成功
    public static final String LABEL_MSG_EDITSUCCESS = "Edit success";//修改成功
    public static final String LABEL_MSG_DELSUCCESS = "Del success";//删除成功

    public static final int LABEL_R_C_FAIL = 2;

    public static final int LABEL_E_C_FAIL = 1;
    public static final String LABEL_E_MSG_ADDFAIL = "Add Fail";//添加失败
    public static final String LABEL_E_MSG_EDITFAIL = "Edit fail";//修改失败
    public static final String LABEL_E_MSG_DELFAIL = "Delete fail";//删除失败


    //好友相关
    public static final int FRIEND_R_C_SUCCESS = 1;
    public static final String FRIEND_MSG_ADDSUCCESS = "Add success";//添加成功
    public static final String FRIEND_MSG_DELSUCCESS = "Del success";//删除成功
    public static final String FRIEND_MSG_GETSUCCESS = "Get success";//修改成功

    public static final int FRIEND_R_C_FAIL = 2;

    public static final int FRIEND_E_C_FAIL = 1;
    public static final String FRIEND_E_MSG_ADDFAIL = "Add Fail";//添加失败
    public static final String FRIEND_E_MSG_GETFAIL = "GET fail";//修改失败
    public static final String FRIEND_E_MSG_DELFAIL = "Delete fail";//删除失败

    //拒绝相关
    public static final int REFUSE_R_C_SUCCESS = 1;
    public static final String REFUSE_MSG_ADDSUCCESS = "Refuse success";//添加成功
    public static final String REFUSE_MSG_DELSUCCESS = "Unrefuse success";//删除成功

    public static final int REFUSE_R_C_FAIL = 2;

    public static final int REFUSE_E_C_FAIL = 1;
    public static final String REFUSE_E_MSG_ADDFAIL = "Refuse Fail";//添加失败
    public static final String REFUSE_E_MSG_DELFAIL = "Unrefuse fail";//删除失败

    //举报相关
    public static final int REPORT_R_C_SUCCESS = 1;
    public static final String REPORT_MSG_ADDSUCCESS = "report success";//添加成功
    public static final String REPORT_MSG_EDITSUCCESS = "Edit success";//修改成功

    public static final int REPORT_R_C_FAIL = 2;

    public static final int REPORT_E_C_FAIL = 1;
    public static final String REPORT_E_MSG_ADDFAIL = "Report Fail";//添加失败
    public static final String REPORT_E_MSG_EDITFAIL = "Edit fail";//删除失败

}

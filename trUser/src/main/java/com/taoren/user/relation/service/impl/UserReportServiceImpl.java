package com.taoren.user.relation.service.impl;

import com.taoren.common.constant.CodeMsgConstants;
import com.taoren.common.util.TaorenUtils;
import com.taoren.model.user.UserReport;

import com.taoren.service.user.UserReportRemoteService;
import com.taoren.service.user.model.UserReportListRespDto;
import com.taoren.service.user.model.UserReportRespDto;
import com.taoren.user.relation.dao.UserFriendDao;
import com.taoren.user.relation.dao.UserRefuseDao;
import com.taoren.user.relation.dao.UserReportDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by wangshuisheng on 2015/5/26.
 */
@Service
public class UserReportServiceImpl implements UserReportRemoteService {

    Logger logger = LoggerFactory.getLogger(UserReportServiceImpl.class);

    @Autowired
    UserReportDao userReportDao;


    public UserReportRespDto addUserReport(long uid, long defendant, int reason, String reasonMsg) {

        UserReport userReport = new UserReport();
//        userReport.setId(uid + "_" + defendant);
        userReport.setUid(uid);
        userReport.setDefendant(defendant);
        userReport.setReason(reason);
        userReport.setReasonMsg(reasonMsg);
        userReport.setStatus(1);
        userReport.setHaveNoticed(0);

        UserReportRespDto respDto = new UserReportRespDto();
        if(userReportDao.insertUserReport(userReport) == 1){
            respDto.setResultCode(CodeMsgConstants.REPORT_R_C_SUCCESS);
            respDto.setMsg(CodeMsgConstants.REPORT_MSG_ADDSUCCESS);
        }else{
            respDto.setResultCode(CodeMsgConstants.REPORT_R_C_FAIL);
            respDto.setMsg(CodeMsgConstants.REPORT_E_MSG_ADDFAIL);
        }

        return respDto;
    }

    public String addUserReport4Json(long uid, long defendant, int reason, String reasonMsg) {
        return TaorenUtils.o2j(addUserReport(uid, defendant, reason, reasonMsg));
    }

    public UserReportRespDto editUserReport(UserReport userReport) {
        UserReportRespDto respDto = new UserReportRespDto();

        if(userReportDao.updateUserReport(userReport) == 1){
            respDto.setResultCode(CodeMsgConstants.REPORT_R_C_SUCCESS);
            respDto.setMsg(CodeMsgConstants.REPORT_MSG_EDITSUCCESS);
        }else{
            respDto.setResultCode(CodeMsgConstants.REPORT_R_C_FAIL);
            respDto.setMsg(CodeMsgConstants.REPORT_E_MSG_EDITFAIL);
        }

        return respDto;
    }

    public String editUserReport4Json(String reportJosn) {
        return TaorenUtils.o2j(editUserReport(TaorenUtils.j2o(reportJosn, UserReport.class)));
    }


    public UserReportListRespDto userReportList(Map map) {
        UserReportListRespDto respDto = new UserReportListRespDto();
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

            respDto.setTotalRow(userReportDao.userReportCount(map));
            respDto.setList(userReportDao.userReportList(map));
            respDto.setResultCode(1);
        }catch (Exception e){
            e.printStackTrace();
            respDto.setResultCode(2);
        }

        return respDto;
    }

    public String userReportList4Json(String paramJson) {
        return TaorenUtils.o2j(userReportList(TaorenUtils.j2o(paramJson, HashMap.class)));
    }

    public UserReportListRespDto getUserReport(UserReport userReport) {
        UserReportListRespDto respDto = new UserReportListRespDto();
        List<UserReport> userReportList = userReportDao.selectUserReport(userReport);

        respDto.setList(userReportList);
        respDto.setResultCode(1);
        respDto.setTime(new Date());
        return respDto;
    }

    public String getUserReport4Json(String paramJson) {
        return TaorenUtils.o2j(getUserReport(TaorenUtils.j2o(paramJson, UserReport.class)));
    }
}

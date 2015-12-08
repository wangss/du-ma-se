package com.taoren.user.relation.service.impl;

import com.taoren.common.constant.CodeMsgConstants;
import com.taoren.common.util.TaorenUtils;
import com.taoren.model.user.UserFriend;
import com.taoren.model.user.UserRefuse;
import com.taoren.service.user.RelationRemoteService;
import com.taoren.service.user.model.RelationDto;

import com.taoren.service.user.model.UserRefuseRespDto;
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
public class RelationServiceImpl implements RelationRemoteService {

    Logger logger = LoggerFactory.getLogger(RelationServiceImpl.class);

    @Autowired
    UserFriendDao userFriendDao;

    @Autowired
    UserRefuseDao userRefuseDao;

    @Autowired
    UserReportDao userReportDao;


    public UserRefuseRespDto addUserRefuse(long uid, long refuseUid) {
        UserRefuse userRefuse = new UserRefuse();
        userRefuse.setUid(uid);
        userRefuse.setRefuseUid(refuseUid);
        userRefuse.setIsRefused(1);

        UserRefuseRespDto respDto = new UserRefuseRespDto();
        if(userRefuseDao.insertUserRefuse(userRefuse) == 1){
            respDto.setResultCode(CodeMsgConstants.REFUSE_R_C_SUCCESS);
            respDto.setMsg(CodeMsgConstants.REFUSE_MSG_ADDSUCCESS);
        }else{
            respDto.setResultCode(CodeMsgConstants.REFUSE_R_C_FAIL);
            respDto.setMsg(CodeMsgConstants.REFUSE_E_MSG_ADDFAIL);
        }

        return respDto;
    }

    public String addUserRefuse4Json(long uid, long refuseUid) {
        return TaorenUtils.o2j(addUserRefuse(uid, refuseUid));
    }


    public UserRefuseRespDto delUserRefuse(long uid, long refuseUid) {
        UserRefuse userRefuse = new UserRefuse();
        userRefuse.setUid(uid);
        userRefuse.setRefuseUid(refuseUid);
        userRefuse.setIsRefused(1);

        UserRefuseRespDto respDto = new UserRefuseRespDto();
        if(userRefuseDao.deleteUserRefuse(userRefuse) == 1){
            respDto.setResultCode(CodeMsgConstants.REFUSE_R_C_SUCCESS);
            respDto.setMsg(CodeMsgConstants.REFUSE_MSG_DELSUCCESS);
        }else{
            respDto.setResultCode(CodeMsgConstants.REFUSE_R_C_FAIL);
            respDto.setMsg(CodeMsgConstants.REFUSE_E_MSG_DELFAIL);
        }

        return respDto;
    }

    public String delUserRefuse4Json(long uid, long refuseUid) {
        return TaorenUtils.o2j(delUserRefuse(uid, refuseUid));
    }

    public RelationDto isRefused(long uid, long targetUid) {
        RelationDto dto = new RelationDto();

        UserRefuse userRefuse = new UserRefuse();
        userRefuse.setUid(targetUid);
        userRefuse.setRefuseUid(uid);
        List<UserRefuse> userRefuseList = userRefuseDao.selectUserRefuse(userRefuse);

        if(userRefuseList != null && userRefuseList.size() > 0){
            dto.setRefused(true);
        }else{
            dto.setRefused(false);
        }
        return dto;
    }

    public String isRefused4Json(long uid, long targetUid) {
        return TaorenUtils.o2j(isRefused(uid, targetUid));
    }


    public RelationDto getRelation(long uid, long targetUid) {

        RelationDto dto = new RelationDto();

        UserFriend userFriend = new UserFriend();
        userFriend.setUid(uid);
        userFriend.setFriend(targetUid);
        List<UserFriend> userFriendList = userFriendDao.selectUserFriendIds(userFriend);

        if(userFriendList != null && userFriendList.size() > 0){
            dto.setFriend(true);

        }else{
            dto.setFriend(false);
        }

        UserRefuse userRefuse = new UserRefuse();
        userRefuse.setUid(targetUid);
        userRefuse.setRefuseUid(uid);
        List<UserRefuse> userRefuseList = userRefuseDao.selectUserRefuse(userRefuse);

        if(userRefuseList != null && userRefuseList.size() > 0){
            dto.setRefused(true);
        }else{
            dto.setRefused(false);
        }

        userRefuse.setUid(uid);
        userRefuse.setRefuseUid(targetUid);
        userRefuseList = userRefuseDao.selectUserRefuse(userRefuse);

        if(userRefuseList != null && userRefuseList.size() > 0){
            dto.setRefuse(true);
        }else{
            dto.setRefuse(false);
        }

        return dto;
    }

    public String getRelation4Json(long uid, long targetUid){
        return TaorenUtils.o2j(getRelation(uid,targetUid));
    }

}

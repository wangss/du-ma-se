package com.taoren.user.relation.service.impl;

import com.taoren.common.constant.CodeMsgConstants;
import com.taoren.common.util.TaorenUtils;
import com.taoren.model.user.UserFriend;
import com.taoren.service.base.model.BaseRespDto;
import com.taoren.service.user.UserFriendRemoteService;
import com.taoren.service.user.model.RelationDto;
import com.taoren.user.relation.dao.UserFriendDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by wangshuisheng on 2015/5/26.
 */
@Service
public class UserFriendServiceImpl implements UserFriendRemoteService {

    Logger logger = LoggerFactory.getLogger(UserFriendServiceImpl.class);

    @Autowired
    UserFriendDao userFriendDao;

    public RelationDto addUserFriend(long uid, long friend) {
        UserFriend userFriend = new UserFriend();
        userFriend.setUid(uid);
        userFriend.setFriend(friend);
        userFriend.setIsFriend(1);

        RelationDto respDto = new RelationDto();
        if(userFriendDao.insertUserFriend(userFriend) == 1){
            respDto.setResultCode(CodeMsgConstants.FRIEND_R_C_SUCCESS);
            respDto.setMsg(CodeMsgConstants.FRIEND_MSG_ADDSUCCESS);
        }else{
            respDto.setResultCode(CodeMsgConstants.FRIEND_R_C_FAIL);
            respDto.setMsg(CodeMsgConstants.FRIEND_E_MSG_ADDFAIL);
        }

        return respDto;
    }

    public String addUserFriend4Json(long uid, long friend) {
        return TaorenUtils.o2j(addUserFriend(uid, friend));
    }

    public RelationDto delUserFriend(long uid, long friend) {
        UserFriend userFriend = new UserFriend();
        userFriend.setUid(uid);
        userFriend.setFriend(friend);

        RelationDto respDto = new RelationDto();
        if(userFriendDao.deleteUserFriend(userFriend) == 1){
            respDto.setResultCode(CodeMsgConstants.FRIEND_R_C_SUCCESS);
            respDto.setMsg(CodeMsgConstants.FRIEND_MSG_DELSUCCESS);
        }else{
            respDto.setResultCode(CodeMsgConstants.FRIEND_R_C_FAIL);
            respDto.setMsg(CodeMsgConstants.FRIEND_E_MSG_DELFAIL);
        }

        return respDto;
    }

    public String delUserFriend4Json(long uid, long friend) {
        return TaorenUtils.o2j(delUserFriend(uid, friend));
    }

    public List<Long> getUserFriendIds(long uid) {
        UserFriend userFriend = new UserFriend();
        userFriend.setUid(uid);
        List<UserFriend> userFriendList = userFriendDao.selectUserFriendIds(userFriend);
        List<Long> ids = new ArrayList<Long>();
        for(UserFriend friend: userFriendList){
            ids.add(friend.getFriend());
        }
        return ids;
    }

    public String getUserFriendIds4Json(long uid) {
        return TaorenUtils.o2j(getUserFriendIds(uid));
    }


    public RelationDto isFriend(long uid, long targetUid) {
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
        return dto;
    }

    public String isFriend4Json(long uid, long targetUid) {
        return TaorenUtils.o2j(isFriend(uid, targetUid));
    }

}

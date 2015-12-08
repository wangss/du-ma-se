package com.taoren.service.sc;

import com.taoren.model.user.UserInfo;
import com.taoren.service.user.model.EsPositionDto;
import com.taoren.service.user.model.EsUserDto;

import java.util.List;

/**
 * Created by wangshuisheng on 2015/5/21.
 */
public interface UserSearchRemoteService {

    public void indexUser(UserInfo userInfo);
    public void indexUser4Json(String userJson);

    public void updateUser(UserInfo userInfo);
    public void updateUser4Json(String userJson);

    public void updateUserPosition(UserInfo userInfo);
    public void updateUserPosition4Json(String userJson);

//    public UserInfo findUser(UserInfo userInfo);
//    public String findUser4Json(String userJson);

    public List<EsUserDto> findUsersByIds(List<String> userIds);
    public String findUsersByIds4Json(String userIds);

    public EsUserDto findUserByUid(String uid);
    public String findUserByUid4Json(String uid);

    public EsUserDto findUserByTrId(String trId);
    public String findUserByTrId4Json(String trId);

    public EsUserDto findUserByPhone(String phone);
    public String findUserByPhone4Json(String phone);


    public EsPositionDto getUserPosition(long uid, Double longitude, Double latitude);
    public String getUserPosition4Json(long uid, Double longitude, Double latitude);

    public String deleteUser(long uid);

    public String reIndexUser(long uid);

}

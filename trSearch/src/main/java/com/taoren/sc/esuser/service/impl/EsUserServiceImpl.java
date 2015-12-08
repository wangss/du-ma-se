package com.taoren.sc.esuser.service.impl;

import com.taoren.common.util.StringUtils;
import com.taoren.common.util.TaorenUtils;

import com.taoren.model.lb.Label;
import com.taoren.sc.constants.IndexTypes;
import com.taoren.sc.eslabel.dao.LabelDao;
import com.taoren.service.lb.model.EsLabelDto;
import com.taoren.sc.esuser.dao.EsPositionDao;
import com.taoren.sc.esuser.dao.EsUserDao;
import com.taoren.sc.esuser.dao.UserInfoDao;
import com.taoren.sc.esuser.dao.UserPositionDao;
import com.taoren.sc.esuser.entities.EsPosition;
import com.taoren.sc.esuser.entities.EsUser;
import com.taoren.service.user.model.EsPositionDto;
import com.taoren.service.user.model.EsUserDto;
import com.taoren.service.sc.UserSearchRemoteService;
import com.taoren.service.user.model.GeoPointDto;
import com.taoren.model.user.UserInfo;
import com.taoren.model.user.UserPosition;
import org.elasticsearch.common.geo.GeoDistance;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wangshuisheng on 2015/6/15.
 */
@Service
public class EsUserServiceImpl implements UserSearchRemoteService {

    @Autowired
    EsUserDao esUserDao;

    @Autowired
    EsPositionDao esPositionDao;

//    @Autowired
//    private EsLabelDao esLabelDao;

    @Autowired
    UserPositionDao userPositionDao;

    @Autowired
    UserInfoDao userInfoDao;

    @Autowired
    LabelDao labelDao;


    /**
     * 用户建立索引信息
     * @param userInfo
     */
    public void indexUser(UserInfo userInfo) {

        if(userInfo == null){
            return;
        }
        if(userInfo.getUid() == null){
            userInfo.setUid(userInfo.getId());
        }
        if(userInfo.getUid() == null){
            return;
        }

        //user
        EsUser esUser = wrapEsUser(userInfo);

        if(esUser.getPrivacyFind() != null){
            esUser.setPrivacyFind(1);
        }
        esUserDao.index(esUser);

        indexEsPosition(userInfo);//保存至esposition， 建立搜索索引

        saveUserPosition(userInfo);//保存到数据库mysql

    }

    /**
     * 用户建立索引信息
     * @param userJson
     */
    public void indexUser4Json(String userJson) {
        indexUser(TaorenUtils.j2o(userJson, UserInfo.class));
    }




    /**
     *  更新用户索引信息
     * @param userInfo
     */
    public void updateUser(UserInfo userInfo) {
        if(userInfo == null){
            return;
        }
        if(userInfo.getUid() == null){
            userInfo.setUid(userInfo.getId());
        }
        if(userInfo.getUid() == null){
            return;
        }

        EsUser esUser = wrapEsUser(userInfo);
        esUserDao.update(esUser);

        updateEsPosition(userInfo);
    }

    /**
     *  更新用户索引信息
     * @param userJson
     */
    public void updateUser4Json(String userJson) {
        updateUser(TaorenUtils.j2o(userJson, UserInfo.class));
    }

    /**
     * 更新用户地理位置索引信息
     * @param userInfo
     */
    public void updateUserPosition(UserInfo userInfo) {

        if(userInfo == null){
            return;
        }
        if(userInfo.getUid() == null){
            userInfo.setUid(userInfo.getId());
        }
        if(userInfo.getUid() == null){
            return;
        }

        updateEsPosition(userInfo);
    }

    /**
     * 更新用户地理位置索引信息
     * @param userJson
     */
    public void updateUserPosition4Json(String userJson) {
        updateUserPosition(TaorenUtils.j2o(userJson, UserInfo.class));
    }



    private void updateEsPosition(UserInfo userInfo){
        //更新position
        QueryBuilder query = new BoolQueryBuilder()
                .must(QueryBuilders.termQuery("uid", userInfo.getUid() + ""))
                .mustNot(QueryBuilders.termQuery("positionType", 1));

        List<EsPosition> positions = esPositionDao.findPosition(
                new NativeSearchQueryBuilder()
                        .withRoute(userInfo.getUid() + "")
                        .withQuery(query));

        Date now = new Date();

        if(positions == null || positions.size() <=0){
            //user动态position
            indexEsPosition(userInfo);
        }else {

            for (EsPosition position : positions) {

                position.setArea(userInfo.getArea());
                position.setActiveTime(now);
                if (userInfo.getLongitude() != null && userInfo.getLatitude() != null) {
                    position.setLocation(new GeoPoint(userInfo.getLatitude(), userInfo.getLongitude()));
                }
                esPositionDao.update(position);
            }
        }

        saveUserPosition(userInfo);

    }

    //保存position数据至数据库mysql
    private void saveUserPosition(UserInfo userInfo){

        UserPosition userPosition = new UserPosition();
        userPosition.setUid(userInfo.getUid());
        userPosition.setLongitude(userInfo.getLongitude());
        userPosition.setLatitude(userInfo.getLatitude());
        userPosition.setArea(userInfo.getArea());
        userPosition.setActiveTime(new Timestamp(new Date().getTime()));
        userPositionDao.saveUserPosition(userPosition);
    }


    public String deleteUser(long uid) {
//        esLabelDao.deleteByUid(uid + "");
        esPositionDao.deleteByUId(uid + "");
        return esUserDao.delete(uid + "");
    }

    public String reIndexUser(long uid){

        deleteUser(uid);
        UserInfo userInfo = userInfoDao.selectUserInfoById(uid);
        if(userInfo == null){
            return "no existed";
        }
        //user
        EsUser esUser = wrapEsUser(userInfo);

        if(esUser.getPrivacyFind() == null){
            esUser.setPrivacyFind(1);
        }
        esUserDao.index(esUser);

        //user动态position
        EsPosition userPosition = new EsPosition();
        userPosition.setPositionId(esUser.getUid() + "_");
        userPosition.setUid(esUser.getUid());
        userPosition.setPositionType(3);//3表示纯用户移动地理位置

        UserPosition position = userPositionDao.selectUserPositionByUid(uid);
        if(position == null){
            userPosition.setActiveTime(userInfo.getCreateTime());
            userPosition.setArea(userInfo.getArea());
            if(userInfo.getLongitude() != null && userInfo.getLatitude() != null){
                userPosition.setLocation(new GeoPoint(userInfo.getLatitude(), userInfo.getLongitude()));
            }
        }else {
            userPosition.setActiveTime(position.getActiveTime());
            userPosition.setArea(position.getArea());
            userPosition.setLocation(new GeoPoint(position.getLatitude(), position.getLongitude()));
        }
        esPositionDao.index(userPosition);

        Label lb = new Label();
        lb.setUid(uid);
        lb.setStatus(1);
        List<Label> labelList = labelDao.selectLabel(lb);

        for(Label label: labelList){
            //保存label对应的position
            EsPosition labelPosition = new EsPosition();
            labelPosition.setPositionId(label.getUid() + "_" + label.getId());
            labelPosition.setUid(label.getUid() + "");

            labelPosition.setPositionType(label.getPositionType());
            if(labelPosition.getPositionType() == null){
                labelPosition.setPositionType(2);//移动
            }
            labelPosition.setArea(label.getArea());

            if(labelPosition.getPositionType() == 1){//固定label
                labelPosition.setLocation(new GeoPoint(label.getLatitude(), label.getLongitude()));
            }else{
                labelPosition.setLocation(userPosition.getLocation());
            }

            labelPosition.setLabelId(label.getId() + "");
            labelPosition.setLabelName(label.getLabelName());
            labelPosition.setZan(label.getZan());
            labelPosition.setCommentCount(label.getCommentCount());
            labelPosition.setActiveTime(userPosition.getActiveTime());

            esPositionDao.index(labelPosition);

            //再保存label (2015-09-29 屏蔽，不需要了)
//            EsLabel esLabel = new EsLabel();
//
//            esLabel.setLabelId(label.getId() + "");
//            esLabel.setUid(label.getUid() + "");
//            esLabel.setLabelName(label.getLabelName());
//            String labelDetail = label.getLabelDetail();
//            esLabel.setLabelDetail(labelDetail);
//
//            esLabelDao.index(esLabel);
        }

        return "done";
    }

    /**
     * 根据用户id查找用户
     * @param userIds
     */
    public List<EsUserDto> findUsersByIds(List<String> userIds) {

        List<EsUserDto> dtoList = new ArrayList<EsUserDto>();

        IdsQueryBuilder query = new IdsQueryBuilder(IndexTypes.TAOREN_TYPE_USER);

        for(String id: userIds) {
            if(StringUtils.isEmpty(id)){
                continue;
            }
            query.addIds(id.trim());
        }

        List<EsUser> esUsers = esUserDao.findUser(new NativeSearchQueryBuilder().withQuery(query));

        for(EsUser user: esUsers){
            EsUserDto userDto = fillLabelInfo(user);
            if(userDto != null){
                dtoList.add(userDto);
            }
        }

        return dtoList;
    }

    /**
     * 根据用户id查找用户
     * @param userIds
     */
    public String findUsersByIds4Json(String userIds) {
        return TaorenUtils.o2j(findUsersByIds(TaorenUtils.j2o(userIds, ArrayList.class)));
    }


    /**
     *  根据trId查找用户
     */
    public EsUserDto findUserByUid(String uid) {

        EsUser user = esUserDao.findById(uid);
        EsUserDto userDto = fillLabelInfo(user);
        return userDto;
    }

    /**
     *  根据trId查找用户
     */
    public String findUserByUid4Json(String uid) {
        return TaorenUtils.o2j(findUserByUid(uid));
    }

    /**
     *  根据trId查找用户
     */
    public EsUserDto findUserByTrId(String trId) {

        EsUser user = esUserDao.findUserByTrId(trId);
        EsUserDto userDto = fillLabelInfo(user);
        return userDto;
    }

    /**
     *  根据trId查找用户
     */
    public String findUserByTrId4Json(String trId) {
        return TaorenUtils.o2j(findUserByTrId(trId));
    }

    /**
     * 根据phone查找用户
     */
    public EsUserDto findUserByPhone(String phone) {

        EsUser user = esUserDao.findUserByPhone(phone);
        EsUserDto userDto = fillLabelInfo(user);
        return userDto;
    }

    /**
     * 根据phone查找用户
     */
    public String findUserByPhone4Json(String phone) {
        return TaorenUtils.o2j(findUserByPhone(phone));
    }

    /**
     * 获取用户地理位置信息
     * @param uid
     * @param longitude
     * @param latitude
     * @return
     */
    public EsPositionDto getUserPosition(long uid, Double longitude, Double latitude){
        EsPosition position = esPositionDao.findById(uid + "_", uid + "");
        EsPositionDto esPositionDto = new EsPositionDto();

        if(position != null){

            esPositionDto.setActiveTime(position.getActiveTime());
            esPositionDto.setArea(position.getArea());

            if(position.getLocation() != null){
                GeoPointDto geoPointDto = new GeoPointDto();
                geoPointDto.setLat(position.getLocation().getLat());
                geoPointDto.setLon(position.getLocation().getLon());
                esPositionDto.setLocation(geoPointDto);

                if(latitude != null && latitude != null){
                    double distance = GeoDistance.ARC.calculate(latitude, longitude,
                            position.getLocation().getLat(), position.getLocation().getLon(),
                            DistanceUnit.KILOMETERS);

                    esPositionDto.setDistance((Math.round(distance * 1000) / 1000.0));
                }
            }
        }

        return esPositionDto;
    }

    /**
     * 获取用户地理位置信息
     * @param uid
     * @param longitude
     * @param latitude
     * @return
     */
    public String getUserPosition4Json(long uid, Double longitude, Double latitude){
        return TaorenUtils.o2j(getUserPosition(uid, longitude, latitude));
    }


    private void indexEsPosition(UserInfo userInfo){
        EsPosition esPosition = new EsPosition();
        esPosition.setPositionId(userInfo.getUid() + "_" );
        esPosition.setUid(userInfo.getUid() + "");

        esPosition.setPositionType(3);//3表示纯用户移动地理位置
        esPosition.setActiveTime(new Date());
        esPosition.setArea(userInfo.getArea());

        if(userInfo.getLongitude() != null && userInfo.getLatitude() != null){
            esPosition.setLocation(new GeoPoint(userInfo.getLatitude(), userInfo.getLongitude()));
        }
        esPositionDao.index(esPosition);
    }


    private EsUser wrapEsUser(UserInfo userInfo){
        EsUser esUser = new EsUser();

        esUser.setUid(userInfo.getUid() + "");

        esUser.setTrId(userInfo.getTrId());
        esUser.setPhone(userInfo.getPhone());
        esUser.setNickname(userInfo.getNickname());
        esUser.setBirthday(userInfo.getBirthday());
        esUser.setAvatar(userInfo.getAvatar());
        esUser.setGender(userInfo.getGender());
        esUser.setSignature(userInfo.getSignature());
        esUser.setPrivacyFind(userInfo.getPrivacyFind());
        return esUser;
    }

    private EsUserDto fillLabelInfo(EsUser user){
        if(user == null){
            return null;
        }

        EsUserDto userDto = new EsUserDto();
        userDto.setUid(user.getUid());
        userDto.setTrId(user.getTrId());
        userDto.setNickname(user.getNickname());
        userDto.setBirthday(user.getBirthday());
        userDto.setGender(user.getGender());
        userDto.setAvatar(user.getAvatar());
        userDto.setSignature(user.getSignature());
        userDto.setPrivacyFind(user.getPrivacyFind());

        if(user != null && user.getUid() != null){


            List<EsPosition> labelPositions = esPositionDao.findByUId(user.getUid());

//            List<EsLabel> labels = new ArrayList<EsLabel>();

            List<EsLabelDto> labelDtos = new ArrayList<EsLabelDto>();
            for (EsPosition labelPosition: labelPositions){

                if(labelPosition.getPositionType() != null && labelPosition.getPositionType() == 3){//用户移动position
                    if(labelPosition.getLocation() != null){
                        GeoPoint geoPoint = labelPosition.getLocation();
                        GeoPointDto geoPointDto = new GeoPointDto();
                        geoPointDto.setLat(geoPoint.getLat());
                        geoPointDto.setLon(geoPoint.getLon());
                        userDto.setLocation(geoPointDto);
                    }


                    userDto.setGeoHash(labelPosition.getGeohash());
                    userDto.setArea(labelPosition.getArea());
                    userDto.setDistance(labelPosition.getDistance());
                    userDto.setActiveTime(labelPosition.getActiveTime());
                    continue;
                }else {
                    EsLabelDto labelDto = new EsLabelDto();
                    labelDto.setLabelId(labelPosition.getLabelId());
                    labelDto.setUid(labelPosition.getUid());
                    labelDto.setLabelName(labelPosition.getLabelName());

                    labelDto.setArea(labelPosition.getArea());
                    labelDto.setPositionType(labelPosition.getPositionType());
                    labelDto.setGeoHash(labelPosition.getGeohash());

                    if(labelPosition.getLocation() != null){
                        GeoPoint geoPoint = labelPosition.getLocation();
                        GeoPointDto geoPointDto = new GeoPointDto();
                        geoPointDto.setLat(geoPoint.getLat());
                        geoPointDto.setLon(geoPoint.getLon());
                        labelDto.setLocation(geoPointDto);
                    }
                    labelDto.setZan(labelPosition.getZan());
                    labelDto.setCommentCount(labelPosition.getCommentCount());
                    labelDtos.add(labelDto);
                }

            }

            userDto.setLabelList(labelDtos);
        }
        return userDto;
    }

}

package com.taoren.sc.eslabel.service.impl;

import com.taoren.common.util.TaorenUtils;
import com.taoren.model.lb.Label;
import com.taoren.sc.esuser.dao.EsPositionDao;
import com.taoren.sc.esuser.entities.EsPosition;
import com.taoren.service.sc.LabelSearchRemoteService;
import com.taoren.service.user.model.EsPositionDto;
import com.taoren.service.user.model.GeoPointDto;
import org.elasticsearch.ElasticsearchIllegalArgumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.stereotype.Service;

/**
 * Created by wangshuisheng on 2015/6/15.
 */
@Service
public class EsLabelServiceImpl implements LabelSearchRemoteService{

    @Autowired
    EsPositionDao positionDao;

    public void indexLabel(Label label) {

        //保存label对应的position
        EsPosition esPosition = new EsPosition();
        esPosition.setPositionId(label.getUid() + "_" + label.getId());
        esPosition.setUid(label.getUid() + "");

        esPosition.setPositionType(label.getPositionType());
        if(esPosition.getPositionType() == null){
            esPosition.setPositionType(2);//移动
        }
        esPosition.setArea(label.getArea());

        if(label.getLatitude() == null || label.getLongitude() == null){
            EsPosition userPosition = positionDao.findById(label.getUid() + "_", label.getUid() + "");
            if(userPosition != null){
                esPosition.setLocation(userPosition.getLocation());
            }
        } else {
            esPosition.setLocation(new GeoPoint(label.getLatitude(), label.getLongitude()));
        }

        esPosition.setLabelId(label.getId() + "");
        esPosition.setLabelName(label.getLabelName());
        esPosition.setZan(0);
        esPosition.setCommentCount(0);

        positionDao.index(esPosition);
    }

    public void indexLabel4Json(String labelJson) {

        indexLabel(TaorenUtils.j2o(labelJson, Label.class));
    }

    public void updateLabel(Label label) {

        EsPosition esPosition = new EsPosition();
        esPosition.setPositionId(label.getUid() + "_" + label.getId());
        esPosition.setUid(label.getUid() + "");
        esPosition.setPositionType(label.getPositionType());

        if(label.getPositionType() != null && label.getPositionType() == 1){//固定
            esPosition.setArea(label.getArea());

            if(label.getLatitude() != null && label.getLongitude() != null){
                esPosition.setLocation(new GeoPoint(label.getLatitude(), label.getLongitude()));
            }
        }
        positionDao.update(esPosition);

    }

    public void updateLabel4Json(String labelJson) {
        updateLabel(TaorenUtils.j2o(labelJson, Label.class));
    }


    public void deleteLabel(long uid, long labelId) {
//        System.out.println("uid = " + uid);
//        positionDao.delete(uid + "", uid + "_" + labelId);
        positionDao.deleteByLabelId(labelId + "");

    }

    public void zanLabel(long uid, long labelId, int action) {
        if(action == 0){
            return;
        }

        action = action > 0 ? 1:-1;

        positionDao.zanLabel(uid + "", labelId + "", action);
    }

    public void commentLabel(long uid, long labelId, int action) {
        if(action == 0){
            return;
        }
        action = action > 0 ? 1:-1;

        try{
            positionDao.commentLabel(uid + "", labelId + "", action);
        }catch (ElasticsearchIllegalArgumentException e){
            e.printStackTrace();
            EsPosition esPosition = new EsPosition();
            esPosition.setPositionId(uid + "_" + labelId);
            esPosition.setUid(uid + "");
            esPosition.setCommentCount(action);
            positionDao.update(esPosition);

        }

    }


    public EsPositionDto getLabelPosition(long uid,long labelId){
        EsPosition position = positionDao.findById(uid + "_" + labelId, uid + "");
        if(position == null){
            return null;
        }
        EsPositionDto esPositionDto = new EsPositionDto();
        esPositionDto.setUid(position.getUid());
        esPositionDto.setPositionId(position.getPositionId());
        esPositionDto.setLabelId(position.getLabelId());
        esPositionDto.setLabelName(position.getLabelName());
        esPositionDto.setActiveTime(position.getActiveTime());
        esPositionDto.setArea(position.getArea());
        esPositionDto.setPositionType(position.getPositionType());
        if(position.getLocation() != null){
            GeoPointDto geoPointDto = new GeoPointDto();
            geoPointDto.setLat(position.getLocation().getLat());
            geoPointDto.setLon(position.getLocation().getLon());
            esPositionDto.setLocation(geoPointDto);
        }

        esPositionDto.setZan(position.getZan());
        esPositionDto.setCommentCount(position.getCommentCount());

        return esPositionDto;
    }

    public String getUserPosition4Json(long uid, long labelId) {
        return TaorenUtils.o2j(getLabelPosition(uid, labelId));
    }
}

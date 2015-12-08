package com.taoren.sc.esasking.service.impl;

import com.taoren.common.util.TaorenUtils;
import com.taoren.model.ask.Asking;
import com.taoren.model.lb.Label;
import com.taoren.sc.esasking.dao.EsAskingDao;
import com.taoren.sc.esasking.entities.EsAsking;
import com.taoren.sc.esuser.dao.EsPositionDao;
import com.taoren.sc.esuser.entities.EsPosition;
import com.taoren.service.ask.model.EsAskingDto;
import com.taoren.service.sc.AskingSearchRemoteService;
import com.taoren.service.user.model.GeoPointDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by wangshuisheng on 2015/6/15.
 */
@Service
public class EsAskingServiceImpl implements AskingSearchRemoteService {

    @Autowired
    EsAskingDao askingDao;

    public void indexAsking(Asking asking) {
        EsAsking esAsking = new EsAsking();
        esAsking.setUid(asking.getUid() + "");
        esAsking.setAskingId(asking.getId() + "");
        esAsking.setAskingDetail(asking.getAskingDetail());
        esAsking.setReplyCount(0);
        esAsking.setActiveTime(new Date());
        esAsking.setArea(asking.getArea());
        esAsking.setLocation(new GeoPoint(asking.getLatitude(), asking.getLongitude()));

        askingDao.index(esAsking);
    }

    public void indexAsking4Json(String askingJson) {
        indexAsking(TaorenUtils.j2o(askingJson, Asking.class));
    }

    public void deleteAsking(long uid, long askingId) {
        askingDao.delete(uid + "", askingId + "");
    }

    public void replyAsking(long uid, long askingId, int action) {
        if(action == 0){
            return;
        }
        action = action > 0 ? 1:-1;

        askingDao.replyCount(uid + "", askingId + "", action);
    }

    public EsAskingDto getEsAsking(long uid, long askingId) {
        EsAsking asking = askingDao.findAsking(uid + "", askingId + "");
        if(asking != null){
            EsAskingDto dto = new EsAskingDto();
            dto.setId(Long.parseLong(asking.getAskingId()));
            dto.setAskingId(dto.getId());
            dto.setUid(Long.parseLong(asking.getUid()));
            dto.setAskingDetail(asking.getAskingDetail());
            dto.setAddTime(new Timestamp(asking.getActiveTime().getTime()));
            dto.setArea(asking.getArea());
            if(asking.getLocation() != null){
                GeoPointDto pointDto = new GeoPointDto();
                pointDto.setLat(asking.getLocation().getLat());
                pointDto.setLon(asking.getLocation().getLon());
                dto.setLocation(pointDto);
            }
            return dto;

        }
        return null;
    }
}

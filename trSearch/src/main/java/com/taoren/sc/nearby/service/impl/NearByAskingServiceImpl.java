package com.taoren.sc.nearby.service.impl;


import com.taoren.common.util.TaorenUtils;
import com.taoren.sc.esasking.dao.EsAskingDao;
import com.taoren.sc.esasking.entities.EsAsking;
import com.taoren.sc.esuser.dao.EsUserDao;
import com.taoren.sc.esuser.entities.EsUser;
import com.taoren.service.ask.model.EsAskingDto;
import com.taoren.service.sc.NearByAskingSearchRemoteService;
import com.taoren.service.sc.model.SearchParams;
import org.elasticsearch.common.geo.GeoDistance;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.GeoDistanceSortBuilder;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wangshuisheng on 2015/6/11.
 */
@Service
public class NearByAskingServiceImpl implements NearByAskingSearchRemoteService {

    Logger logger = LoggerFactory.getLogger(NearByAskingServiceImpl.class);

    @Autowired
    private EsAskingDao askingDao;

    @Autowired
    private EsUserDao userDao;


    public List<EsAskingDto> nearByAsking(SearchParams params) {

        List<EsAsking> askings = searchAsking(params);
        List<EsAskingDto> askingDtoList = new ArrayList<EsAskingDto>();
        for(EsAsking asking: askings){
            EsUser user = userDao.findById(asking.getUid() + "");

            EsAskingDto askingDto = new EsAskingDto();
            askingDto.setId(Long.parseLong(asking.getAskingId()));
            askingDto.setAskingId(askingDto.getId());
            askingDto.setAskingDetail(asking.getAskingDetail());

            askingDto.setReplyCount(asking.getReplyCount());
            askingDto.setAddTime(new Timestamp(asking.getActiveTime().getTime()));
            askingDto.setDistance(asking.getDistance());
            askingDto.setArea(asking.getArea());

            askingDto.setUid(Long.parseLong(user.getUid()));
            askingDto.setAvatar(user.getAvatar());
            askingDto.setNickname(user.getNickname());
            askingDto.setBirthday(user.getBirthday());

            askingDtoList.add(askingDto);
        }

        return askingDtoList;
    }


    public String nearByAsking4Json(String searchParams) {
        return TaorenUtils.o2j(TaorenUtils.j2o(searchParams, SearchParams.class));
    }

    /**
     * 查找用户position
     * @param params
     * @return
     */
    private List<EsAsking> searchAsking(SearchParams params){

        //初始化param
        params = prepareParams(params);

        //区域filter
        GeoDistanceFilterBuilder filter = getDistanceFilterBuilder(params);

        //距离sort
        GeoDistanceSortBuilder distanceSort = getDistanceSortBuilder(params);
        FieldSortBuilder activeTimeSort = getActiveTimeSortBuilder();



        NativeSearchQueryBuilder searchQueryBuilder = new NativeSearchQueryBuilder()
                .withFilter(filter)
                .withPageable(new PageRequest(params.getPage() - 1, params.getPageSize()))
                .withSort(activeTimeSort)//第一个是时间
                .withSort(distanceSort);//第二个才是距离，与 EsAskingResultExtractor 一致

        return askingDao.findAsking(searchQueryBuilder);
    }



    /**
     * 准备参数
     * @param params
     * @return
     */
    private SearchParams prepareParams(SearchParams params){
        if(params.getRange() == null || params.getRange()<100){
            params.setRange(100);//默认100km
        }

        if(params.getPage() == null || params.getPage() <= 0){
            params.setPage(1);
        }
        if(params.getPageSize() == null){
            params.setPageSize(20);//默认第页20
        }

        return params;
    }

    private GeoDistanceFilterBuilder getDistanceFilterBuilder(SearchParams params){
        return FilterBuilders
                .geoDistanceFilter("location")
                .point(params.getLat(), params.getLon())
                .distance(params.getRange(), DistanceUnit.KILOMETERS)// KILOMETERS为空里的意思。2公里附近的数据
                .optimizeBbox("memory") // Can be also "indexed" or "none"
                .geoDistance(GeoDistance.ARC);

    }

    private GeoDistanceSortBuilder getDistanceSortBuilder(SearchParams params){
        GeoDistanceSortBuilder sort = new GeoDistanceSortBuilder("location");
        sort.unit(DistanceUnit.KILOMETERS);
        sort.order(SortOrder.ASC);

        if(params.getLatitude() != null){
            sort.point(params.getLatitude(), params.getLongitude());
        }else{
            sort.point(params.getLat(), params.getLon());
        }
        sort.geoDistance(GeoDistance.ARC);
        return sort;
    }

    private FieldSortBuilder getActiveTimeSortBuilder(){
        FieldSortBuilder sort = new FieldSortBuilder("activeTime").order(SortOrder.DESC);
        return sort;
    }

}

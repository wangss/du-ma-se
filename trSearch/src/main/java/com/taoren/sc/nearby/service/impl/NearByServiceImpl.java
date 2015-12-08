package com.taoren.sc.nearby.service.impl;


import com.taoren.common.util.StringUtils;
import com.taoren.common.util.TaorenUtils;

import com.taoren.sc.constants.IndexTypes;
import com.taoren.service.lb.model.EsLabelDto;
import com.taoren.sc.esuser.dao.EsPositionDao;
import com.taoren.sc.esuser.dao.EsUserDao;
import com.taoren.sc.esuser.entities.EsPosition;
import com.taoren.service.user.model.EsUserDto;
import com.taoren.service.sc.NearBySearchRemoteService;
import com.taoren.service.sc.model.SearchParams;
import com.taoren.service.user.model.GeoPointDto;
import org.elasticsearch.common.geo.GeoDistance;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.sort.GeoDistanceSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wangshuisheng on 2015/6/11.
 */
@Service
public class NearByServiceImpl implements NearBySearchRemoteService{

    Logger logger = LoggerFactory.getLogger(NearByServiceImpl.class);

    @Autowired
    private EsUserDao userDao;

    @Autowired
    private EsPositionDao positionDao;



    public List<EsUserDto>  nearByUser(SearchParams params) {

        List<EsPosition> positions = new ArrayList<EsPosition>();

        if(params.getLat() != null || params.getLon() != null){
            positions = searchUserPosition(params);
        }

        List<EsUserDto> esUserDtoList = new ArrayList<EsUserDto>();

        for(EsPosition position: positions){

            //用户信息
            EsUserDto userDto = userDao.findById4EsUserDto(position.getUid());

            if(position.getLocation() != null){
                GeoPointDto geoPointDto = new GeoPointDto();
                geoPointDto.setLat(position.getLocation().getLat());
                geoPointDto.setLon(position.getLocation().getLon());
                userDto.setLocation(geoPointDto);
            }
            userDto.setGeoHash(position.getGeohash());
            userDto.setArea(position.getArea());
            userDto.setDistance(position.getDistance());
            userDto.setActiveTime(position.getActiveTime());


            //找到labelPosition， 要排序(倒序)
            List<EsPosition> labelPositions = positionDao.findByUId(userDto.getUid());
            List<EsLabelDto> labelDtos = new ArrayList<EsLabelDto>();

            for (EsPosition labelPosition: labelPositions){

                //过滤纯用户的
                if(labelPosition.getPositionType()!= null && labelPosition.getPositionType() == 3){
                    continue;
                }

                EsLabelDto labelDto = new EsLabelDto();
                labelDto.setLabelId(labelPosition.getLabelId());
                labelDto.setUid(labelPosition.getUid());
                labelDto.setLabelName(labelPosition.getLabelName());
                labelDto.setZan(labelPosition.getZan());
                labelDto.setCommentCount(labelPosition.getCommentCount());


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

                if(labelDto.getPositionType() != null && labelDto.getPositionType() == 1){//1表示固定位置，需要计算distance

                    Double latitude = params.getLatitude();
                    Double longitude = params.getLongitude();
                    if(latitude == null){
                        latitude = params.getLat();
                        longitude = params.getLon();
                    }

                    double distance = GeoDistance.ARC.calculate(latitude, longitude,
                            labelDto.getLocation().getLat(), labelDto.getLocation().getLon(),
                            DistanceUnit.KILOMETERS);
                    labelDto.setDistance((Math.round(distance * 1000) / 1000.0));
                }else{
                    labelDto.setDistance(labelPosition.getDistance());
                }
                labelDtos.add(labelDto);

            }

            userDto.setLabelList(labelDtos);

            esUserDtoList.add(userDto);
        }
        return esUserDtoList;
    }


    public String nearByUser4Json(String searchParams) {
        return TaorenUtils.o2j(nearByUser(TaorenUtils.j2o(searchParams, SearchParams.class)));
    }

    public List<EsUserDto> nearByLabel(SearchParams params) {

        if(StringUtils.isEmpty(params.getKeyword())){
            return nearByUser(params);
        }

        List<EsPosition> positions = new ArrayList<EsPosition>();

        if(params.getLat() != null || params.getLon() != null){
            positions = searchLabelPosition(params);
        }

        List<EsUserDto> esUserDtoList = new ArrayList<EsUserDto>();

        for(EsPosition labelPosition: positions){

            EsUserDto userDto = userDao.findById4EsUserDto(labelPosition.getUid());// 对应user已经找到

            if(labelPosition.getPositionType() != null && labelPosition.getPositionType() == 1){//如果标签位置是固定
                EsPosition userPosition = positionDao.findById(labelPosition.getUid() + "_", labelPosition.getUid());//user动态位置已经找到

                if(userPosition.getLocation() != null){
                    GeoPoint geoPoint = userPosition.getLocation();
                    GeoPointDto geoPointDto = new GeoPointDto();
                    geoPointDto.setLat(geoPoint.getLat());
                    geoPointDto.setLon(geoPoint.getLon());
                    userDto.setLocation(geoPointDto);
                }

                userDto.setGeoHash(userPosition.getGeohash());
                userDto.setArea(userPosition.getArea());
                userDto.setDistance(userPosition.getDistance());
                userDto.setActiveTime(userPosition.getActiveTime());

                Double latitude = params.getLatitude();
                Double longitude = params.getLongitude();
                if(latitude == null){
                    latitude = params.getLat();
                    longitude = params.getLon();
                }

                double distance = GeoDistance.ARC.calculate(latitude, longitude,
                        labelPosition.getLocation().getLat(), labelPosition.getLocation().getLon(),
                        DistanceUnit.KILOMETERS);
                userDto.setDistance((Math.round(distance * 1000) / 1000.0));

            }else{
                if(labelPosition.getLocation() != null){
                    GeoPoint geoPoint = labelPosition.getLocation();
                    GeoPointDto geoPointDto = new GeoPointDto();
                    geoPointDto.setLat(geoPoint.getLat());
                    geoPointDto.setLon(geoPoint.getLon());
                    userDto.setLocation(geoPointDto);
                }

                userDto.setGeoHash(labelPosition.getGeohash());
                userDto.setArea(labelPosition.getArea());
                userDto.setDistance(labelPosition.getDistance());//distance已经计算好了
                userDto.setActiveTime(labelPosition.getActiveTime());
            }


            List<EsLabelDto> labelDtos = new ArrayList<EsLabelDto>();
            EsLabelDto labelDto = new EsLabelDto();
            labelDto.setLabelId(labelPosition.getLabelId());
            labelDto.setUid(labelPosition.getUid());
            labelDto.setLabelName(labelPosition.getLabelName());

            labelDto.setZan(labelPosition.getZan());
            labelDto.setCommentCount(labelPosition.getCommentCount());

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


            labelDto.setDistance(labelPosition.getDistance());

            labelDtos.add(labelDto);
            userDto.setLabelList(labelDtos);


            esUserDtoList.add(userDto);
        }

        return esUserDtoList;
    }
    public String nearByLabel4Json(String searchParams) {
        return TaorenUtils.o2j(nearByLabel(TaorenUtils.j2o(searchParams, SearchParams.class)));
    }


    /**
     * 查找用户position
     * @param params
     * @return
     */
    private List<EsPosition> searchUserPosition(SearchParams params){

        //初始化param
        params = prepareParams(params);

        //区域filter
        GeoDistanceFilterBuilder filter = getDistanceFilterBuilder(params);

        //距离sort
        GeoDistanceSortBuilder sort = getDistanceSortBuilder(params);


        //查询query
        BoolQueryBuilder query = QueryBuilders.boolQuery();


        //针对user类型
        query.must(QueryBuilders.termQuery("positionType", 3));


        //活跃时间
        if(params.getLastActiveTimeRange() != null){
            query.must(getActiveTimeQueryBuilder(params));
        }


        //user中的过滤条件（年龄、性别、隐身模式）
        query.must(QueryBuilders.hasParentQuery(
                IndexTypes.TAOREN_TYPE_POSITION_PARENT,
                getParentQueryBuilder(params)));

        NativeSearchQueryBuilder searchQueryBuilder = new NativeSearchQueryBuilder().
                withQuery(query)
                .withFilter(filter)
                .withPageable(new PageRequest(params.getPage() - 1, params.getPageSize()))
                .withSort(sort);

        return positionDao.findPosition(searchQueryBuilder);
    }

    /**
     * 查找label position
     * @param params
     * @return
     */
    private List<EsPosition> searchLabelPosition(SearchParams params){
        logger.debug(TaorenUtils.o2jPretty(params, true));

        params = prepareParams(params);

        //区域filter
        GeoDistanceFilterBuilder filter = getDistanceFilterBuilder(params);

        //距离sort
        GeoDistanceSortBuilder sort = getDistanceSortBuilder(params);


        //查询query
        BoolQueryBuilder query = QueryBuilders.boolQuery();

        //纯user
        query.mustNot(QueryBuilders.termQuery("positionType", 3));

        //最后活动时间
        if(params.getLastActiveTimeRange() != null){
            query.must(getActiveTimeQueryBuilder(params));
        }

        //关键字
        if(params.getKeyword() != null && StringUtils.areNotEmpty(params.getKeyword())){
            query.must(getKeyWorldQueryBuilder(params));
        }

        //user中的过滤条件（年龄、性别、隐身模式）
        query.must(QueryBuilders.hasParentQuery(
                IndexTypes.TAOREN_TYPE_POSITION_PARENT,
                getParentQueryBuilder(params)));


        NativeSearchQueryBuilder searchQueryBuilder = new NativeSearchQueryBuilder().
                withQuery(query)
                .withFilter(filter)
                .withPageable(new PageRequest(params.getPage() - 1, params.getPageSize()))
                .withSort(sort);

        return positionDao.findPosition(searchQueryBuilder);
    }


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

    private QueryBuilder getActiveTimeQueryBuilder(SearchParams params){
        Date today = new Date();
        long strNow = System.currentTimeMillis();

        long begin = TaorenUtils.getBeforeDateMillis(today, 60 * 24 * 3);

        switch (params.getLastActiveTimeRange()){//	活动时间1 15分钟内；2 60分钟； 4 一天；
            case 1:
                begin = TaorenUtils.getBeforeDateMillis(today, 15);
                break;
            case 2:
                begin = TaorenUtils.getBeforeDateMillis(today, 60);
                break;
            case 3:
                begin = TaorenUtils.getBeforeDateMillis(today, 60 * 24);
                break;
            case 4:
                begin = TaorenUtils.getBeforeDateMillis(today, 60 * 24 * 3);
                break;
        }

        return QueryBuilders.rangeQuery("activeTime").queryName("activeTime")
                .from(begin)
                .to(strNow)
                .includeLower(true)
                .includeUpper(true);
    }

    private QueryBuilder getParentQueryBuilder(SearchParams params){

        BoolQueryBuilder parentBoolQuery = QueryBuilders.boolQuery();

        //隐身
        parentBoolQuery.mustNot(QueryBuilders.termQuery("privacyFind", 2));//排除不让查的

        //性别
        if(params.getGender() != null){
            parentBoolQuery.must(QueryBuilders.termQuery("gender", params.getGender()));
        }

        //年龄
        if(params.getAgeRange() != null){

            long from = System.currentTimeMillis();
            long tooo = from;
            Date today = new Date();

            switch (params.getAgeRange()){//年龄段1 18岁以下；2 18-22岁；3 23-26岁； 4 27-35岁； 35岁以上

                case 1:
                    from = TaorenUtils.getBirthDayMillis(today, 18);
//                    tooo = now;
                    break;

                case 2:
                    from = TaorenUtils.getBirthDayMillis(today, 24);
                    tooo = TaorenUtils.getBirthDayMillis(today, 18);
                    break;

                case 3:
                    from = TaorenUtils.getBirthDayMillis(today, 29);
                    tooo = TaorenUtils.getBirthDayMillis(today, 24);
                    break;

                case 4:
                    from = TaorenUtils.getBirthDayMillis(today, 36);
                    tooo = TaorenUtils.getBirthDayMillis(today, 29);
                    break;

                case 5:
                    from = TaorenUtils.getBirthDayMillis(today, 100);
                    tooo = TaorenUtils.getBirthDayMillis(today, 36);
                    break;

                default:
                    from = TaorenUtils.getBirthDayMillis(today, 100);
//                    tooo = now;
                    break;
            }

            RangeQueryBuilder ageQuery = QueryBuilders.rangeQuery("birthday").queryName("birthday")
                    .from(from)
                    .to(tooo)
                    .includeLower(true)
                    .includeUpper(true);

            parentBoolQuery.must(ageQuery);
        }
        return parentBoolQuery;
    }

    //标签关键字
    private QueryBuilder getKeyWorldQueryBuilder(SearchParams params){

        String[] keys =  params.getKeyword().split(" +");

        BoolQueryBuilder q = QueryBuilders.boolQuery();
        for(String key: keys){
            q.should(QueryBuilders.matchQuery("labelName", key));
        }
        return q;
    }


}

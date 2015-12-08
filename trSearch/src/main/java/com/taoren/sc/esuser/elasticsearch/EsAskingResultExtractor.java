package com.taoren.sc.esuser.elasticsearch;

import com.taoren.common.util.TaorenUtils;
import com.taoren.sc.esasking.entities.EsAsking;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.base.Strings;
import org.elasticsearch.search.SearchHit;
import org.springframework.data.elasticsearch.core.ResultsExtractor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangshuisheng on 2015/7/21.
 */
public class EsAskingResultExtractor implements ResultsExtractor<List<EsAsking>> {

    public List<EsAsking> extract(SearchResponse response) {
        List<EsAsking> askings = new ArrayList<EsAsking>();
        for (SearchHit hit : response.getHits()) {
            if (hit != null) {
                EsAsking esAsking = null;
                if (!Strings.isNullOrEmpty(hit.sourceAsString())) {

                    String source = hit.sourceAsString();
                    Double distance = null;
                    if( hit.getSortValues().length > 0){
                        try{
                            distance = (Double)hit.getSortValues()[1];//第一个是时间， 第二个才是距离
                            distance = Math.round(distance * 1000) / 1000.0;
                        }catch (Exception e){
                            e.printStackTrace();
                        }

                    }
//                    position = TaorenUtils.j2o(source, EsPositionDto.class);
                    esAsking = TaorenUtils.mapToObject(source, EsAsking.class);
                    esAsking.setDistance(distance);

                    askings.add(esAsking);
                }
            }
        }
        
        return askings;
    }
}

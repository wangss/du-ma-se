package com.taoren.sc.esuser.elasticsearch;

import com.taoren.common.util.TaorenUtils;
import com.taoren.sc.esuser.entities.EsPosition;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.base.Strings;
import org.elasticsearch.search.SearchHit;
import org.springframework.data.elasticsearch.core.ResultsExtractor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangshuisheng on 2015/7/21.
 */
public class EsPositionResultExtractor implements ResultsExtractor<List<EsPosition>> {

    public List<EsPosition> extract(SearchResponse response) {
        List<EsPosition> positions = new ArrayList<EsPosition>();
        for (SearchHit hit : response.getHits()) {
            if (hit != null) {
                EsPosition position = null;
                if (!Strings.isNullOrEmpty(hit.sourceAsString())) {

                    String source = hit.sourceAsString();
                    Double distance = null;
                    if( hit.getSortValues().length > 0){
                        try{
                            distance = (Double)hit.getSortValues()[0];
                            distance = Math.round(distance * 1000) / 1000.0;
                        }catch (Exception e){
                            e.printStackTrace();
                        }

                    }
//                    position = TaorenUtils.j2o(source, EsPositionDto.class);
                    position = TaorenUtils.mapToObject(source, EsPosition.class);
                    position.setDistance(distance);

                    positions.add(position);
                }
            }
        }
        
        return positions;
    }
}

package com.taoren.sc.esasking.dao.impl;

import com.taoren.common.util.StringUtils;
import com.taoren.common.util.TaorenUtils;
import com.taoren.sc.base.dao.impl.BaseDaoImpl;
import com.taoren.sc.constants.IndexTypes;
import com.taoren.sc.elasticsearch.MyElasticsearchTemplate;
import com.taoren.sc.esasking.dao.EsAskingDao;
import com.taoren.sc.esasking.entities.EsAsking;
import com.taoren.sc.esuser.elasticsearch.EsAskingResultExtractor;
import com.taoren.sc.esuser.elasticsearch.EsPositionResultExtractor;
import com.taoren.sc.esuser.entities.EsPosition;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wangshuisheng on 2015/6/11.
 */
@Repository
public class EsAskingDaoImpl extends BaseDaoImpl implements EsAskingDao {

    @Override
    protected String getNameSpace() {
        return ".";
    }

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate; // inject the template as ListOperations

    @Autowired
    private MyElasticsearchTemplate myElasticsearchTemplate; // inject the template as ListOperations


    public String index(EsAsking asking) {

        IndexQuery indexQuery = new IndexQueryBuilder()
                .withIndexName(IndexTypes.TAOREN_INDEX)
                .withType(IndexTypes.TAOREN_TYPE_ASKING)
                .withId(asking.getAskingId())
                .withParentId(asking.getUid())
                .withObject(asking).build();

        return elasticsearchTemplate.index(indexQuery);
    }


    public List<EsAsking> findByUid(String uid) {
        return findAsking(new NativeSearchQueryBuilder().withQuery(QueryBuilders.termQuery("uid", uid)));
    }


    public EsAsking findById(String askingId) {
        GetQuery getQuery = new GetQuery();
        getQuery.setId(askingId);
        return elasticsearchTemplate.queryForObject(getQuery,EsAsking.class);
    }

    public EsAsking findAsking(String uid, String askingId) {
        if(StringUtils.isEmpty(uid)){
            return findById(askingId);
        }

        QueryBuilder query = QueryBuilders.termQuery("askingId", askingId);
        List<EsAsking> askings = findAsking(new NativeSearchQueryBuilder().withRoute(uid).withQuery(query));

        if(askings != null && askings.size() > 0){
            return askings.get(0);
        }
        return null;
    }

    public List<EsAsking> findAsking(NativeSearchQueryBuilder searchQueryBuilder) {
        SearchQuery searchQuery = searchQueryBuilder
                .withIndices(IndexTypes.TAOREN_INDEX)
                .withTypes(IndexTypes.TAOREN_TYPE_ASKING).build();
        return elasticsearchTemplate.query(searchQuery, new EsAskingResultExtractor());//此方法中包含自己的mapper
    }




    public String delete(String uid, String askingId) {
        return myElasticsearchTemplate.delete(IndexTypes.TAOREN_INDEX,  IndexTypes.TAOREN_TYPE_ASKING, askingId, uid, uid);
    }

    public String deleteById(String askingId) {
        return elasticsearchTemplate.delete(EsAsking.class, askingId);
    }

    public void deleteByUid(String uid) {
        QueryBuilder query = QueryBuilders.termQuery("uid", uid);
        DeleteQuery deleteQuery = new DeleteQuery();
        deleteQuery.setQuery(query);
        elasticsearchTemplate.delete(deleteQuery, EsAsking.class);
    }

    public void replyCount(String uid, String askingId, int action) {

        UpdateRequest updateRequest = new UpdateRequest();
        updateRequest.parent(uid);
        updateRequest.script("ctx._source.replyCount += count");
        updateRequest.addScriptParam("count", action);


        UpdateQuery updateQuery = new UpdateQueryBuilder()
                .withIndexName(IndexTypes.TAOREN_INDEX)
                .withType(IndexTypes.TAOREN_TYPE_ASKING)
                .withId(askingId)
                .withClass(EsAsking.class)
                .withUpdateRequest(updateRequest)
                .build();

        myElasticsearchTemplate.update(updateQuery);
    }

}

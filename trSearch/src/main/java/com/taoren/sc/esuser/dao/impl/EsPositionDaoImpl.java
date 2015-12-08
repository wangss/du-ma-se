package com.taoren.sc.esuser.dao.impl;

import com.taoren.common.util.StringUtils;
import com.taoren.common.util.TaorenUtils;
import com.taoren.sc.base.dao.impl.BaseDaoImpl;
import com.taoren.sc.elasticsearch.MyElasticsearchTemplate;
import com.taoren.sc.constants.IndexTypes;
import com.taoren.sc.esuser.dao.EsPositionDao;
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
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wangshuisheng on 2015/6/11.
 */
@Repository
public class EsPositionDaoImpl extends BaseDaoImpl implements EsPositionDao {

    @Override
    protected String getNameSpace() {
        return ".";
    }

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate; // inject the template as ListOperations

    @Autowired
    private MyElasticsearchTemplate myElasticsearchTemplate; // inject the template as ListOperations


    public String index(EsPosition position) {

        IndexQuery indexQuery = new IndexQueryBuilder()
                .withIndexName(IndexTypes.TAOREN_INDEX)
                .withType(IndexTypes.TAOREN_TYPE_POSITION)
                .withId(position.getPositionId())
                .withParentId(position.getUid())
                .withObject(position).build();

        return elasticsearchTemplate.index(indexQuery);
    }

    public String delete(EsPosition position) {
        return elasticsearchTemplate.delete(EsPosition.class, position.getPositionId());
    }

    public String delete(String uid, String positonId) {
        return myElasticsearchTemplate.delete(IndexTypes.TAOREN_INDEX,  IndexTypes.TAOREN_TYPE_POSITION, positonId, uid, uid);
    }

    public String deleteById(String positionId) {
        return elasticsearchTemplate.delete(EsPosition.class, positionId);
    }

    public EsPosition update(EsPosition position) {

        UpdateRequest updateRequest = new UpdateRequest();
        updateRequest.parent(position.getUid());
        updateRequest.doc(TaorenUtils.o2j(position));
        UpdateQuery updateQuery = new UpdateQueryBuilder()
                .withIndexName(IndexTypes.TAOREN_INDEX)
                .withType(IndexTypes.TAOREN_TYPE_POSITION)
                .withDoUpsert(true)
                .withId(position.getPositionId())
                .withClass(EsPosition.class)
                .withUpdateRequest(updateRequest)
                .build();

        UpdateResponse response =  myElasticsearchTemplate.update(updateQuery);
        return position;
    }


    public EsPosition findById(String positionId) {
        QueryBuilder query = QueryBuilders.termQuery("positionId", positionId);
        List<EsPosition> positions = findPosition(new NativeSearchQueryBuilder().withQuery(query));

        if(positions != null && positions.size() > 0){
            return positions.get(0);
        }
        return null;
    }

    public EsPosition findById(String positionId, String uid) {
        if(StringUtils.isEmpty(uid)){
            return findById(positionId);
        }

        QueryBuilder query = QueryBuilders.termQuery("positionId", positionId);
        List<EsPosition> positions = findPosition(new NativeSearchQueryBuilder().withRoute(uid).withQuery(query));

        if(positions != null && positions.size() > 0){
            return positions.get(0);
        }
        return null;
    }

    public List<EsPosition> findByUId(String uid) {
        QueryBuilder query = QueryBuilders.termQuery("uid", uid);
        SortBuilder sort = SortBuilders.fieldSort("positionId").missing(0).order(SortOrder.DESC);//倒排列

        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withRoute(uid)
                .withQuery(query)
                .withSort(sort)
                .withIndices(IndexTypes.TAOREN_INDEX)
                .withTypes(IndexTypes.TAOREN_TYPE_POSITION)
                .build();
        return elasticsearchTemplate.queryForList(searchQuery, EsPosition.class);
    }


    public void deleteByUId(String uid) {
        QueryBuilder query = QueryBuilders.termQuery("uid", uid);
        DeleteQuery deleteQuery = new DeleteQuery();
        deleteQuery.setQuery(query);
        elasticsearchTemplate.delete(deleteQuery, EsPosition.class);
    }


    public void deleteByLabelId(String labelId) {
        QueryBuilder query = QueryBuilders.termQuery("labelId", labelId);
        DeleteQuery deleteQuery = new DeleteQuery();
        deleteQuery.setQuery(query);
        elasticsearchTemplate.delete(deleteQuery, EsPosition.class);
    }
    public List<EsPosition> findPosition(NativeSearchQueryBuilder searchQueryBuilder) {
        SearchQuery searchQuery = searchQueryBuilder
                .withIndices(IndexTypes.TAOREN_INDEX)
                .withTypes(IndexTypes.TAOREN_TYPE_POSITION).build();
        return elasticsearchTemplate.query(searchQuery, new EsPositionResultExtractor());//此方法中包含自己的mapper
    }


    public void zanLabel(String uid, String labelId, int action) {

        UpdateRequest updateRequest = new UpdateRequest();
        updateRequest.parent(uid);
        updateRequest.script("ctx._source.zan += count");
        updateRequest.addScriptParam("count", action);


        UpdateQuery updateQuery = new UpdateQueryBuilder()
                .withIndexName(IndexTypes.TAOREN_INDEX)
                .withType(IndexTypes.TAOREN_TYPE_POSITION)
                .withId(uid + "_" + labelId)
                .withClass(EsPosition.class)
                .withUpdateRequest(updateRequest)
                .build();

        myElasticsearchTemplate.update(updateQuery);
    }

    public void commentLabel(String uid, String labelId, int action) {

        UpdateRequest updateRequest = new UpdateRequest();
        updateRequest.parent(uid);
        updateRequest.script("ctx._source.commentCount += count");
        updateRequest.addScriptParam("count", action);


        UpdateQuery updateQuery = new UpdateQueryBuilder()
                .withIndexName(IndexTypes.TAOREN_INDEX)
                .withType(IndexTypes.TAOREN_TYPE_POSITION)
                .withId(uid + "_" + labelId)
                .withClass(EsPosition.class)
                .withUpdateRequest(updateRequest)
                .build();

        myElasticsearchTemplate.update(updateQuery);
    }

}

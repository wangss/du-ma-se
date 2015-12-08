package com.taoren.sc.esuser.dao.impl;

import com.taoren.common.util.TaorenUtils;
import com.taoren.sc.base.dao.impl.BaseDaoImpl;
import com.taoren.sc.constants.IndexTypes;
import com.taoren.sc.esuser.dao.EsUserDao;
import com.taoren.sc.esuser.entities.EsUser;
import com.taoren.service.user.model.EsUserDto;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.index.query.QueryBuilders;
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
public class EsUserDaoImpl extends BaseDaoImpl implements EsUserDao {

    @Override
    protected String getNameSpace() {
        return ".";
    }

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate; // inject the template as ListOperations

//    @Autowired
//    private MyElasticsearchTemplate elasticsearchTemplate; // inject the template as ListOperations



    public String index(EsUser user) {

        IndexQuery indexQuery = new IndexQueryBuilder()
                .withIndexName(IndexTypes.TAOREN_INDEX)
                .withType(IndexTypes.TAOREN_TYPE_USER)
                .withId(user.getUid())
                .withObject(user)
                .build();
        return elasticsearchTemplate.index(indexQuery);
    }

    public String delete(EsUser user) {
       return delete(user.getUid());
    }

    public String delete(String uid){
        return  elasticsearchTemplate.delete(EsUser.class, uid);
    }

    public EsUser update(EsUser user){

        IndexRequest indexRequest = new IndexRequest();
        indexRequest.source(TaorenUtils.o2j(user));

        UpdateQuery updateQuery = new UpdateQueryBuilder()
                .withIndexName(IndexTypes.TAOREN_INDEX)
                .withType(IndexTypes.TAOREN_TYPE_USER)
                .withId(user.getUid())
                .withClass(EsUser.class)
                .withIndexRequest(indexRequest)
                .withDoUpsert(true).build();
        // when
        UpdateResponse response =  elasticsearchTemplate.update(updateQuery);
        return user;
    }


    public EsUser findById(String uid) {
        GetQuery getQuery = new GetQuery();
        getQuery.setId(uid);
        return elasticsearchTemplate.queryForObject(getQuery,EsUser.class);
    }

    public EsUserDto findById4EsUserDto(String uid) {
        EsUser user = findById(uid);
        return convert2Dto(user);
    }


    public EsUser findUserByTrId(String trId) {
        List<EsUser> users = findUser(new NativeSearchQueryBuilder().withQuery(QueryBuilders.termQuery("trId", trId)));

        if(users != null && users.size() > 0){
            return users.get(0);
        }
        return null;
    }

    public EsUser findUserByPhone(String phone) {
        List<EsUser> users = findUser(new NativeSearchQueryBuilder().withQuery(QueryBuilders.termQuery("phone", phone)));

        if(users != null && users.size() > 0){
            return users.get(0);
        }
        return null;
    }


    public List<EsUser> findUser(NativeSearchQueryBuilder searchQueryBuilder) {
        SearchQuery searchQuery = searchQueryBuilder
                .withIndices(IndexTypes.TAOREN_INDEX)
                .withTypes(IndexTypes.TAOREN_TYPE_USER)
                .withPageable(new PageRequest(0, 20))
                .build();
        return elasticsearchTemplate.queryForList(searchQuery, EsUser.class);
    }

    private EsUserDto convert2Dto(EsUser user){
        if(user != null){
            EsUserDto userDto = new EsUserDto();
            userDto.setUid(user.getUid());
            userDto.setTrId(user.getTrId());
            userDto.setNickname(user.getNickname());
            userDto.setBirthday(user.getBirthday());
            userDto.setGender(user.getGender());
            userDto.setAvatar(user.getAvatar());
            userDto.setSignature(user.getSignature());
            userDto.setPrivacyFind(user.getPrivacyFind());
            return userDto;
        }
        return null;
    }
}

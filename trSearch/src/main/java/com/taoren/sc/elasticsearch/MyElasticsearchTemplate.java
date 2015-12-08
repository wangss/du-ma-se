/*
 *
 */
package com.taoren.sc.elasticsearch;


import org.elasticsearch.action.deletebyquery.DeleteByQueryRequestBuilder;
import org.elasticsearch.action.update.UpdateRequestBuilder;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.elasticsearch.core.*;
import org.springframework.data.elasticsearch.core.convert.ElasticsearchConverter;
import org.springframework.data.elasticsearch.core.convert.MappingElasticsearchConverter;
import org.springframework.data.elasticsearch.core.mapping.ElasticsearchPersistentEntity;
import org.springframework.data.elasticsearch.core.mapping.SimpleElasticsearchMappingContext;
import org.springframework.data.elasticsearch.core.query.DeleteQuery;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.data.elasticsearch.core.query.UpdateQuery;
import org.springframework.util.Assert;

import java.util.List;

import static org.apache.commons.lang.StringUtils.isNotBlank;

/**
 * MyElasticsearchTemplate
 *
 * @author shuisheng
 */

public class MyElasticsearchTemplate {

    private static final Logger logger = LoggerFactory.getLogger(ElasticsearchTemplate.class);
    private Client client;
    private ElasticsearchConverter elasticsearchConverter;
    private ResultsMapper resultsMapper;
    private String searchTimeout;

    public MyElasticsearchTemplate(Client client) {
        this.client = client;
    }

    public void setSearchTimeout(String searchTimeout) {
        this.searchTimeout = searchTimeout;
    }



    public UpdateResponse update(UpdateQuery query) {
        String indexName = query.getIndexName();
        String type = query.getType();
        Assert.notNull(indexName, "No index defined for Query");
        Assert.notNull(type, "No type define for Query");
        Assert.notNull(query.getId(), "No Id define for Query");
        Assert.notNull(query.getUpdateRequest(), "No IndexRequest define for Query");
        UpdateRequestBuilder updateRequestBuilder = client.prepareUpdate(indexName, type, query.getId());
        if (query.DoUpsert()) {
            updateRequestBuilder.setDocAsUpsert(true)
                    .setRouting(query.getUpdateRequest().routing())
                    .setDoc(query.getUpdateRequest().doc())
                    .setScript(query.getUpdateRequest().script(), query.getUpdateRequest().scriptType())
                    .setScriptParams(query.getUpdateRequest().scriptParams())
                    .setScriptLang(query.getUpdateRequest().scriptLang());
        } else {
            updateRequestBuilder
                    .setRouting(query.getUpdateRequest().routing())
                    .setDoc(query.getUpdateRequest().doc())
                    .setScript(query.getUpdateRequest().script(), query.getUpdateRequest().scriptType())
                    .setScriptParams(query.getUpdateRequest().scriptParams())
                    .setScriptLang(query.getUpdateRequest().scriptLang());

        }
        return updateRequestBuilder.execute().actionGet();
    }


    public void delete(DeleteQuery deleteQuery) {
        Assert.notNull(deleteQuery.getIndex(), "No index defined for Query");
        Assert.notNull(deleteQuery.getType(), "No type define for Query");
        client.prepareDeleteByQuery(deleteQuery.getIndex())
                .setTypes(deleteQuery.getType())
                .setRouting()
                .setQuery(deleteQuery.getQuery())
                .execute().actionGet();

    }



    public String delete(String indexName, String type, String id) {
        return client.prepareDelete(indexName, type, id).execute().actionGet().getId();
    }

    public String delete(String indexName, String type, String id, String parent) {
        return client.prepareDelete(indexName, type, id).setParent(parent).execute().actionGet().getId();
    }

    public String delete(String indexName, String type, String id, String parent, String rounting) {
        String ia = client.prepareDelete(indexName, type, id).setParent(parent).setRouting(rounting).execute().actionGet().getId();
        System.out.println("---------------------------" + ia);
        return ia;
    }

}

package com.taoren.sc.elasticsearch.controller;

import com.taoren.sc.esasking.entities.EsAsking;
import com.taoren.sc.esuser.entities.EsPosition;
import com.taoren.sc.esuser.entities.EsUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by wangshuisheng on 2015/6/12.
 */
@Scope("prototype")
@Controller
@RequestMapping("/es")
public class EsController {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @RequestMapping(value = "index/create")
    public void indexCreate(){
        elasticsearchTemplate.createIndex(EsUser.class);
//        elasticsearchTemplate.createIndex(EsPosition.class);

        elasticsearchTemplate.putMapping(EsUser.class);
        elasticsearchTemplate.putMapping(EsPosition.class);
        elasticsearchTemplate.putMapping(EsAsking.class);

        elasticsearchTemplate.refresh(EsUser.class, true);
        elasticsearchTemplate.refresh(EsPosition.class,true);
        elasticsearchTemplate.refresh(EsAsking.class,true);

        System.out.println("--------------es/create---------done");
    }


    @RequestMapping(value = "index/delete")
    public void indexDelete(){
        elasticsearchTemplate.deleteIndex(EsUser.class);
//        elasticsearchTemplate.deleteIndex(EsPosition.class);
//        elasticsearchTemplate.deleteIndex(EsLabel.class);
        System.out.println("-------------es/delete--------done");


    }

    @RequestMapping(value = "mapping/user")
    public void mappingUser(){
        elasticsearchTemplate.putMapping(EsUser.class);
        elasticsearchTemplate.refresh(EsUser.class, true);
        System.out.println("-------mapping/user--------done");

    }


    @RequestMapping(value = "mapping/position")
    public void mappingPosition(){
        elasticsearchTemplate.putMapping(EsPosition.class);
        elasticsearchTemplate.refresh(EsPosition.class, true);
        System.out.println("-------mapping/user--------done");

    }
    @RequestMapping(value = "mapping/asking")
    public void mappingAsking(){
        elasticsearchTemplate.putMapping(EsAsking.class);
        elasticsearchTemplate.refresh(EsAsking.class, true);
        System.out.println("-------mapping/user--------done");

    }




}

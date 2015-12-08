package com.taoren.lb.test;

import com.taoren.common.util.TaorenUtils;
import com.taoren.model.ask.Asking;
import com.taoren.model.lb.LabelComment;
import com.taoren.service.ask.model.EsAskingDto;
import com.taoren.service.lb.model.LabelCommentListRespDto;
import com.taoren.service.lb.model.LabelListRespDto;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wangshuisheng on 2015/6/29.
 */
public class T {
    public static void main(String[] args) throws Exception{
//        String name = "中文";
//        System.out.println(name);
//        name=java.net.URLEncoder.encode(name, "UTF-8");
//        System.out.println(name);
//        name=java.net.URLEncoder.encode(name,"UTF-8");
//        System.out.println(name);
//        name=java.net.URLDecoder.decode(name, "UTF-8");
//        System.out.println(name);
//        name = java.net.URLDecoder.decode(name, "UTF-8");
//        System.out.println(name);



        List<EsAskingDto> askingList = new ArrayList<EsAskingDto>();

        for(int i=1; i<=2; i++){
            EsAskingDto asking = new EsAskingDto();
            asking.setId(10000000000L);
            asking.setUid(2000000001L);
            asking.setNickname("昵称" + i);
            asking.setAvatar("/asking/url" + i);

            asking.setBirthday(new Date());
            asking.setAskingDetail("喊话详情。。。" + i);
            asking.setAddTime(new Timestamp(new Date().getTime()));
            asking.setDistance(0.001);
            asking.setReplyCount(2);

            askingList.add(asking);

        }


        String str =  TaorenUtils.o2j(askingList);
        System.out.println(str);
    }
}

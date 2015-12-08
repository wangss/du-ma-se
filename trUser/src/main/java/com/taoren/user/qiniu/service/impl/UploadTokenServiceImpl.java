package com.taoren.user.qiniu.service.impl;


import com.qiniu.util.Auth;
import com.taoren.common.util.TaorenUtils;
import com.taoren.service.user.UploadTokenRemoteService;
import com.taoren.service.user.model.QiniuToken;
import com.taoren.service.user.model.UploadTokenDto;
import com.taoren.user.account.dao.UserDao;
import com.taoren.user.qiniu.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by Administrator on 2015/5/19.
 */
@Service
public class UploadTokenServiceImpl implements UploadTokenRemoteService {

    Logger logger = LoggerFactory.getLogger(UploadTokenServiceImpl.class);

    @Autowired
    private UserDao userDao;

    Auth auth = Auth.create(Constants.ACCESS_KEY, Constants.SECRET_KEY);


    public UploadTokenDto headerUploadToken(long uid) {

        UploadTokenDto respDto = new UploadTokenDto();

        long headId = userDao.selectUserHeadId();
        String key = "/img/head/"+ uid + "/"
                + TaorenUtils.getDateSlashFormat(new Date())  + "/" + headId;

        String token = auth.uploadToken(Constants.HEAD_SPACE, key);
        List<QiniuToken>  tokenList = new ArrayList<QiniuToken>();
        tokenList.add(new QiniuToken(key, token));
        respDto.setTokenList(tokenList);
        respDto.setResultCode(1);

        return respDto;
    }

    public String headerUploadToken4Json(long uid) {

        return TaorenUtils.o2j(headerUploadToken(uid));
    }


    public UploadTokenDto labelMediaUploadToken(long uid, int count) {
        if(count > 9){
            count = 9;
        }

        UploadTokenDto respDto = new UploadTokenDto();
        List<QiniuToken>  tokenList = new ArrayList<QiniuToken>();

        for(int i=0;i<count;i++){
            long mediaId = userDao.selectLabelMediaId();
            String key = "/img/label/" + uid + "/"
                    + TaorenUtils.getDateSlashFormat(new Date()) + "/" + mediaId;

            String token =  auth.uploadToken(Constants.LABEL_SPACE, key);
            tokenList.add(new QiniuToken(key, token));
        }

        respDto.setTokenList(tokenList);
        respDto.setResultCode(1);
        return respDto;
    }

    public String labelMediaUploadToken4Json(long uid, int count) {
        return TaorenUtils.o2j(labelMediaUploadToken(uid, count));
    }


    public UploadTokenDto askingMediaUploadToken(long uid, int count) {
        if(count > 9){
            count = 9;
        }

        UploadTokenDto respDto = new UploadTokenDto();
        List<QiniuToken>  tokenList = new ArrayList<QiniuToken>();

        for(int i=0;i<count;i++){
            long mediaId = userDao.selectAskingMediaId();
            String key = "/img/asking/" + uid + "/"
                    + TaorenUtils.getDateSlashFormat(new Date()) + "/" + mediaId;

            String token =  auth.uploadToken(Constants.LABEL_SPACE, key);
            tokenList.add(new QiniuToken(key, token));
        }

        respDto.setTokenList(tokenList);
        respDto.setResultCode(1);
        return respDto;
    }


    public String askinglMediaUploadToken4Json(long uid, int count) {
        return TaorenUtils.o2j(askingMediaUploadToken(uid, count));
    }
}

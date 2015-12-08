package com.taoren.service.user.model;

import com.taoren.service.base.model.BaseRespDto;

import java.util.List;

/**
 * Created by Administrator on 2015/5/21.
 */
public class UploadTokenDto extends BaseRespDto {


    private List<QiniuToken> tokenList;

    public List<QiniuToken> getTokenList() {
        return tokenList;
    }

    public void setTokenList(List<QiniuToken> tokenList) {
        this.tokenList = tokenList;
    }
}

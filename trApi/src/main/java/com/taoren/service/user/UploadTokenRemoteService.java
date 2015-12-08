package com.taoren.service.user;

import com.taoren.service.user.model.UploadTokenDto;

/**
 * Created by wangshuisheng on 2015/6/24.
 */
public interface UploadTokenRemoteService {

    public UploadTokenDto headerUploadToken(long uid);
    public String headerUploadToken4Json(long uid);

    public UploadTokenDto labelMediaUploadToken(long uid, int count);
    public String labelMediaUploadToken4Json(long uid, int count);

    public UploadTokenDto askingMediaUploadToken(long uid, int count);
    public String askinglMediaUploadToken4Json(long uid, int count);

}

package com.taoren.service.sc;

import com.taoren.service.sc.model.SearchParams;
import com.taoren.service.user.model.EsUserDto;

import java.util.List;

/**
 * Created by wangshuisheng on 2015/5/21.
 */
public interface NearBySearchRemoteService {

    public List<EsUserDto> nearByLabel(SearchParams params);
    public String nearByLabel4Json(String searchParams);

    public List<EsUserDto> nearByUser(SearchParams params);
    public String nearByUser4Json(String searchParams);


}

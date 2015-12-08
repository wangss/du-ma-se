package com.taoren.service.sc;

import com.taoren.service.ask.model.EsAskingDto;
import com.taoren.service.sc.model.SearchParams;
import com.taoren.service.user.model.EsUserDto;

import java.util.List;

/**
 * Created by wangshuisheng on 2015/5/21.
 */
public interface NearByAskingSearchRemoteService {

    public List<EsAskingDto> nearByAsking(SearchParams params);
    public String nearByAsking4Json(String searchParams);

}

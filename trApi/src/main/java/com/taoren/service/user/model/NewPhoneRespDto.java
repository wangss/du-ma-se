package com.taoren.service.user.model;

import com.taoren.service.base.model.BaseRespDto;

/**
 * Created by Administrator on 2015/5/21.
 */
public class NewPhoneRespDto extends BaseRespDto {
    private String phone;

    private Boolean canRegister;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public Boolean getCanRegister() {
        return canRegister;
    }

    public void setCanRegister(Boolean canRegister) {
        this.canRegister = canRegister;
    }
}

package com.taoren.service.base.model;

import com.taoren.model.lb.Label;

import java.util.List;

/**
 * Created by Administrator on 2015/5/21.
 */
public class ListRespDto<T> extends BaseRespDto {

    private Integer page;
    private Integer pageSize;
    private Integer totalRow;
    private List<T> list;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalRow() {
        return totalRow;
    }

    public void setTotalRow(Integer totalRow) {
        this.totalRow = totalRow;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}

package com.taoren.sc.base.dao.impl;


import com.taoren.sc.base.dao.BaseDao;

/**
 * Created by wangshuisheng on 2015/5/27.
 */
public abstract class BaseDaoImpl implements BaseDao {
    //mybatis的命名空间
    protected abstract String getNameSpace();
}

package com.taoren.user.base.dao.impl;

import com.taoren.user.base.dao.BaseDao;
import org.apache.ibatis.session.SqlSession;

/**
 * Created by wangshuisheng on 2015/5/27.
 */
public abstract class BaseDaoImpl implements BaseDao{

    //mybatis的命名空间
    protected abstract String getNameSpace();
}

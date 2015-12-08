package com.taoren.sc.esuser.dao;

import com.taoren.sc.base.dao.BaseDao;
import com.taoren.sc.esuser.entities.EsUser;
import com.taoren.service.user.model.EsUserDto;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;

import java.util.List;

/**
 * Created by wangshuisheng on 2015/6/11.
 */
public interface EsUserDao extends BaseDao {

    /**
     * 将用户信息建立索引
     * @param user
     * @return
     */
    public String index(EsUser user);



    /**
     * 更新用户索引信息
     * @param user
     * @return
     */
    public EsUser update(EsUser user);

    /**
     * 删除用户索引信息
     * @param user
     * @return
     */
    public String delete(EsUser user);

    /**
     * 删除用户索引信息
     * @param uid
     * @return
     */
    public String delete(String uid);

    /**
     * @param uid
     * @return
     */
    public EsUser findById(String uid);

    /**
     * @param uid
     * @return
     */
    public EsUserDto findById4EsUserDto(String uid);

    /**
     * 查找
     * @param searchQueryBuilder
     * @return
     */
    public List<EsUser> findUser(NativeSearchQueryBuilder searchQueryBuilder);


    /**
     * 通过trId查找
     * @param trId
     * @return
     */
    public EsUser findUserByTrId(String trId);

    /**
     * 通过trId查找
     * @param phone
     * @return
     */
    public EsUser findUserByPhone(String phone);

}

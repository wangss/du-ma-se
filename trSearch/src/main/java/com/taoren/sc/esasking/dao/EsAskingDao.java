package com.taoren.sc.esasking.dao;

import com.taoren.sc.base.dao.BaseDao;
import com.taoren.sc.esasking.entities.EsAsking;
import com.taoren.sc.esuser.entities.EsPosition;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;

import java.util.List;

/**
 * Created by wangshuisheng on 2015/6/11.
 */
public interface EsAskingDao extends BaseDao {

    /**
     * 建立索引
     * @param asking
     * @return
     */
    public String index(EsAsking asking);


    /**
     * 查找
     * @param askingId
     * @return
     */
    public EsAsking findById(String askingId);

    /**
     * @param uid
     * @return
     */
    public List<EsAsking> findByUid(String uid);

    /* 查找
    * @param
    * @retur
    */
    public EsAsking findAsking(String uid, String askingId);

    public List<EsAsking> findAsking(NativeSearchQueryBuilder searchQueryBuilder);

    /**
     * 删除用户索引信息
     * @param
     * @return
     */
    public String deleteById(String askingId);



    /**
     * @param uid
     * @return
     */
    public void deleteByUid(String uid);


    public String delete(String uid, String askingId);


    public void replyCount(String uid, String askingId, int action);

}

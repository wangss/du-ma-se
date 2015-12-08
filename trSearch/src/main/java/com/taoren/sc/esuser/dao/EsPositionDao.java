package com.taoren.sc.esuser.dao;

import com.taoren.sc.base.dao.BaseDao;
import com.taoren.sc.esuser.entities.EsPosition;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;

import java.util.List;

/**
 * Created by wangshuisheng on 2015/6/11.
 */
public interface EsPositionDao extends BaseDao {

    /**
     * 将position信息建立索引
     * @param position
     * @return
     */
    public String index(EsPosition position);



    /**
     * 更新用户索引信息
     * @param position
     * @return
     */
    public EsPosition update(EsPosition position);

    /**
     * 删除索引信息
     * @param position
     * @return
     */
    public String delete(EsPosition position);


    public String delete(String uid, String positonId);

    /**
     * 删除用户索引信息
     * @param
     * @return
     */
    public String deleteById(String positionId);

    /**
     * 查找
     * @param positionId
     * @return
     */
    public EsPosition findById(String positionId);

    /**
     * 查找
     * @param positionId
     * @return
     */
    public EsPosition findById(String positionId, String uid);

    /**
     * @param uid
     * @return
     */
    public List<EsPosition> findByUId(String uid);


    /**
     * @param uid
     * @return
     */
    public void deleteByUId(String uid);

    public void deleteByLabelId(String labelId) ;

    public List<EsPosition> findPosition(NativeSearchQueryBuilder searchQueryBuilder);


    public void zanLabel(String uid, String labelId, int action);

    public void commentLabel(String uid, String labelId, int action);


}

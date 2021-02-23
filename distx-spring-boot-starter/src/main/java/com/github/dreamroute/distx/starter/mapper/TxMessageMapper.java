package com.github.dreamroute.distx.starter.mapper;

import com.github.dreamroute.distx.starter.domain.TxMessage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 
 * @author w.dehai
 *
 */
public interface TxMessageMapper {

    /**
     * 批量查询
     * 
     * @param minId 最小id
     * @param maxId 最大id
     * @return 返回消息列表
     */
    List<TxMessage> selectByIdRange(@Param("minId") long minId, @Param("maxId") long maxId);

    int insert(TxMessage message);

    TxMessage selectByPrimaryKey(Long id);

    void deleteByPrimaryKey(Long id);

    List<TxMessage> selectTxMessageByPage(@Param("start") int start, @Param("limit") int limit);
}

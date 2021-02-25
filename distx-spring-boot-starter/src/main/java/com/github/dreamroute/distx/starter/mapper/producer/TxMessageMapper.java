package com.github.dreamroute.distx.starter.mapper.producer;

import com.github.dreamroute.distx.starter.domain.TxMessage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 
 * @author w.dehai
 *
 */
public interface TxMessageMapper {

    int insert(TxMessage message);

    TxMessage selectByPrimaryKey(Long id);

    void deleteByPrimaryKey(Long id);

    List<TxMessage> selectTxMessageByPage(@Param("start") int start, @Param("limit") int limit, @Param("faildTimes") int faildTimes);

    void addFaildTimes(@Param("id") long id);
}
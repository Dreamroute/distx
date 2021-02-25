package com.github.dreamroute.distx.starter.mapper.consumer;

import com.github.dreamroute.distx.starter.domain.TxMessageCommit;
import org.apache.ibatis.annotations.Insert;

/**
 * @author w.dehai
 */
public interface TxMessageCommitMapper {

    @Insert("insert into tx_message_commit(`body`, `create_time`, `message_table_id`) values(#{body}, #{createTime}, #{messageTableId})")
    void insert(TxMessageCommit msg);
}

package com.github.dreamroute.distx.sample;

import com.github.dreamroute.distx.starter.domain.TxMessageCommit;

/**
 * 
 * @author w.dehai
 *
 */
public interface TxMessageCommitService {

    /**
     * 新增消息
     * 
     * @param msg
     */
    void insert(TxMessageCommit msg);

}

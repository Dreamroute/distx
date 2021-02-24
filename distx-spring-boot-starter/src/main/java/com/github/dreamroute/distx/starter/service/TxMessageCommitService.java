package com.github.dreamroute.distx.starter.service;

import com.github.dreamroute.distx.starter.domain.TxMessageCommit;

/**
 * @author w.dehai
 */
public interface TxMessageCommitService {

    /**
     * 新增消息
     */
    void insert(TxMessageCommit msg);

}

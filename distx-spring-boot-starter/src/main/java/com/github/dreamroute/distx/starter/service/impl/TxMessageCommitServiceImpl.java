package com.github.dreamroute.distx.starter.service.impl;

import com.github.dreamroute.distx.starter.domain.TxMessageCommit;
import com.github.dreamroute.distx.starter.mapper.consumer.TxMessageCommitMapper;
import com.github.dreamroute.distx.starter.service.TxMessageCommitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

/**
 * 
 * @author w.dehai
 *
 */
@Transactional(rollbackFor = Exception.class)
public class TxMessageCommitServiceImpl implements TxMessageCommitService {

    @Autowired
    private TxMessageCommitMapper txMessageCommitMapper;

    @Override
    public void insert(TxMessageCommit msg) {
        msg.setCreateTime(new Timestamp(System.currentTimeMillis()));
        txMessageCommitMapper.insert(msg);
    }

}

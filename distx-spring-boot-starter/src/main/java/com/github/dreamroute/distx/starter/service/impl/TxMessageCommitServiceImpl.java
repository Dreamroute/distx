package com.github.dreamroute.distx.starter.service.impl;

import com.github.dreamroute.distx.starter.domain.TxMessageCommit;
import com.github.dreamroute.distx.starter.mapper.TxMessageCommitMapper;
import com.github.dreamroute.distx.starter.service.TxMessageCommitService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

/**
 * 
 * @author w.dehai
 *
 */
@Service
@AllArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class TxMessageCommitServiceImpl implements TxMessageCommitService {

    private TxMessageCommitMapper txMessageCommitMapper;

    @Override
    public void insert(TxMessageCommit msg) {
        msg.setCreateTime(new Timestamp(System.currentTimeMillis()));
        txMessageCommitMapper.insert(msg);
    }

}

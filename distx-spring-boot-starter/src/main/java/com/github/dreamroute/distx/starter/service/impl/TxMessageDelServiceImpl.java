package com.github.dreamroute.distx.starter.service.impl;

import com.github.dreamroute.distx.starter.domain.TxMessageDel;
import com.github.dreamroute.distx.starter.mapper.producer.TxMessageDelMapper;
import com.github.dreamroute.distx.starter.service.TxMessageDelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

/**
 * @author w.dehai
 */
@Transactional(rollbackFor = Exception.class)
public class TxMessageDelServiceImpl implements TxMessageDelService {

    @Autowired
    private TxMessageDelMapper txMessageDelMapper;

    @Override
    public int insert(TxMessageDel txMessageDel) {
        txMessageDel.setCreateTime(new Timestamp(System.currentTimeMillis()));
        return txMessageDelMapper.insert(txMessageDel);
    }

    @Override
    public TxMessageDel selectById(Long id) {
        return txMessageDelMapper.selectByPrimaryKey(id);
    }

}

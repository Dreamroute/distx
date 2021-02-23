package com.github.dreamroute.distx.starter.service.impl;

import com.github.dreamroute.distx.starter.domain.TxMessageDel;
import com.github.dreamroute.distx.starter.mapper.TxMessageDelMapper;
import com.github.dreamroute.distx.starter.service.TxMessageDelService;
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
public class TxMessageDelServiceImpl implements TxMessageDelService {

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

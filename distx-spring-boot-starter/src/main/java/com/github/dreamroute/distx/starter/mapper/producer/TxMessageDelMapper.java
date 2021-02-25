package com.github.dreamroute.distx.starter.mapper.producer;

import com.github.dreamroute.distx.starter.domain.TxMessageDel;

/**
 * 
 * @author w.dehai
 *
 */
public interface TxMessageDelMapper {
    int insert(TxMessageDel txMessageDel);

    TxMessageDel selectByPrimaryKey(Long id);
}

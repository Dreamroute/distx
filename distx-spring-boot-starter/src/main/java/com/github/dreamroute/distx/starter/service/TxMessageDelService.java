package com.github.dreamroute.distx.starter.service;

import com.github.dreamroute.distx.starter.domain.TxMessageDel;

/**
 * 操作逻辑删除表Service
 *
 * @author w.dehai
 */
public interface TxMessageDelService {

    /**
     * 新增消息
     */
    int insert(TxMessageDel txMessageDel);

    /**
     * 根据id查询
     */
    TxMessageDel selectById(Long id);

}

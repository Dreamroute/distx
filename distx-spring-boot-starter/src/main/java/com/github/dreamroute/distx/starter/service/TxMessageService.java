package com.github.dreamroute.distx.starter.service;

import com.github.dreamroute.distx.starter.domain.TxMessage;

import java.util.List;

/**
 * 消息表service
 *
 * @author w.dehai
 */
public interface TxMessageService {

    /**
     * 新增消息，业务系统需要调用此方法
     */
    int insert(TxMessage message);

    /**
     * 逻辑删除，业务系统不需要使用此方法
     */
    void deleteById(Long id);

    /**
     * 查询消息表，业务系统不需要使用此方法
     *
     * @param pageSize 每一次同步条数到消息队列，默认5条，可以通过${rocketmq.pageSize}修改
     * @param pageNo 页码
     * @return 返回消息
     */
    List<TxMessage> selectTxMessageByPage(int pageSize, int pageNo);

    /**
     * 参考syncTxMessage2RocketMQ(int start)方法
     */
    void syncTxMessage2RocketMq();

    void addFaildTimes(long id);

}

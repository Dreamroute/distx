package com.github.dreamroute.distx.starter.listener;

import com.github.dreamroute.distx.starter.rocketmq.DistxProperties;
import com.github.dreamroute.distx.starter.service.TxMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

public class SyncTask {

    @Autowired
    private TxMessageService txMessageService;

    @Scheduled(cron = DistxProperties.CRON_VALUE)
    public void syncMsg2MQ() {
        txMessageService.syncTxMessage2RocketMq();
    }

}

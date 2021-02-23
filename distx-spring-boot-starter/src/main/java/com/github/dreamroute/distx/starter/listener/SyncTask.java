package com.github.dreamroute.distx.starter.listener;

import com.github.dreamroute.distx.starter.service.TxMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SyncTask {

    @Autowired
    private TxMessageService txMessageService;

    @Scheduled(cron = "${distx.cron}")
    public void syncMsg2MQ() {
        txMessageService.syncTxMessage2RocketMq();
    }

}

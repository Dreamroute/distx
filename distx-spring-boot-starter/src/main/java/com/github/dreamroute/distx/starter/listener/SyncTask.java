package com.github.dreamroute.distx.starter.listener;

import com.github.dreamroute.distx.starter.domain.TxMessage;
import com.github.dreamroute.distx.starter.service.TxMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.lang.System.currentTimeMillis;

@Component
public class SyncTask {

    @Autowired
    private TxMessageService txMessageService;

    @Scheduled(cron = "${distx.cron}")
    public void syncMsg2MQ() {
        List<TxMessage> msgs = txMessageService.selectTxMessageByPage(5, 1);
        System.err.println(msgs);
    }
}

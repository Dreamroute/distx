package com.github.dreamroute.distx.sample;

import com.alibaba.fastjson.JSON;
import com.github.dreamroute.distx.starter.domain.TxMessage;
import com.github.dreamroute.distx.starter.mapper.TxMessageMapper;
import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.destination.DataSourceDestination;
import com.ninja_squad.dbsetup.operation.Insert;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static com.ninja_squad.dbsetup.Operations.insertInto;
import static com.ninja_squad.dbsetup.Operations.truncate;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * CRUD测试类
 *
 * @author w.dehai
 */
@Slf4j
@SpringBootTest
class TxMessageServiceTest {

    @Autowired
    private TxMessageService txMessageService;
    @Autowired
    private TxMessageMapper txMessageMapper;
    @Autowired
    private DataSource dataSource;

    @Value("${rocketmq.pageSize:5}")
    private int pageSize;

    @BeforeEach
    void init() {
        new DbSetup(new DataSourceDestination(dataSource), truncate("tx_message")).launch();
        new DbSetup(new DataSourceDestination(dataSource), truncate("tx_message_del")).launch();
    }

    @Test
    void insertTest() {
        TxMessage message = TxMessage.builder().topic("tx-msg").tag("tx-msg").createTime(new Timestamp(System.currentTimeMillis())).body("tx-msg").build();
        int result = txMessageService.insert(message);
        assertEquals(1, result);
    }

    @Test
    void deleteByIdTest() {
        Insert insert = insertInto("tx_message")
                .columns("id", "topic", "tag", "body", "create_time")
                .values(1L, "topic", "tag", "body", new Timestamp(System.currentTimeMillis()))
                .build();
        new DbSetup(new DataSourceDestination(dataSource), insert).launch();
        txMessageService.deleteById(1L);
    }

    private Long id;
    private String topic;
    private String tag;
    private String body;
    private Timestamp createTime;

    @Test
    void selectTxMessageByPageTest() {
        List<TxMessage> data = txMessageService.selectTxMessageByPage(5, 3);
        System.err.println(data);
    }

    @Test
    void selectByIdRangeTest() {
        List<TxMessage> result = txMessageMapper.selectByIdRange(56611L, 56615L);
        System.err.println(result);
    }

    @Test
    void insertDbTest() throws InterruptedException {
        AtomicInteger count = new AtomicInteger(0);
        int size = 1;
        long start = System.currentTimeMillis();
        ExecutorService pool = new ThreadPoolExecutor(10, 10, 5, TimeUnit.SECONDS, new LinkedBlockingDeque<>(100), r -> new Thread(r, "nnm"));

        List<Callable<String>> tasks = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            tasks.add(() -> {
                int value = new Random().nextInt(3) + 1;
                TxMessage message = new TxMessage(null, "fin-stable-dev-30", "tag" + value, "CC", null);
                txMessageService.insert(message);
                log.info("===> ###新增消息表：{}, 插入数据条数: {}", JSON.toJSONString(message), count.incrementAndGet());
                return null;
            });
        }
        pool.invokeAll(tasks);
        long end = System.currentTimeMillis();
        long consum = end - start;
        System.err.println("插入" + size + "条数据耗时" + consum);
    }

    /**
     * 正常同步（用于生产环境）
     */
    @Test
    void syncTest() throws Exception {
        txMessageService.syncTxMessage2RocketMq();
    }

    /**
     * 多线程同步（用于测试环境）
     */
//    @Test
//    void syncTestWithThreadpool() throws Exception {
//        List<TxMessage> result = txMessageService.selectTxMessageByPage(1, 1);
//        Long firstRowId = null;
//        if (result != null && !result.isEmpty()) {
//            firstRowId = result.get(0).getId();
//        }
//
//        if (firstRowId != null) {
//            int count = txMessageMapper.selectCount(null);
//            int totalPage = count / pageSize;
//            if (count % this.pageSize != 0) {
//                totalPage++;
//            }
//
//            long first = firstRowId.longValue();
//            AtomicInteger page = new AtomicInteger(-1);
//
//            long start = System.currentTimeMillis();
//            ExecutorService pool = new ThreadPoolExecutor(10, 10, 5, TimeUnit.SECONDS, new LinkedBlockingDeque<>(100), r -> new Thread(r, "nnm"));
//            List<Callable<String>> tasks = new ArrayList<>();
//            for (int i=0; i<totalPage; i++) {
//                tasks.add(() -> {
//                    int pg = page.incrementAndGet();
//                    txMessageService.syncTxMessage2RocketMq(first + pg * pageSize, first + (pg + 1) * pageSize);
//                    return null;
//                });
//            }
//            pool.invokeAll(tasks);
//            long end = System.currentTimeMillis();
//            long consum = end - start;
//            System.err.println("耗时" + consum);
//            Thread.sleep(Long.MAX_VALUE);
//        }
//    }
    @Test
    void startContainerTest() throws Exception {
        Thread.sleep(Long.MAX_VALUE);
    }

}

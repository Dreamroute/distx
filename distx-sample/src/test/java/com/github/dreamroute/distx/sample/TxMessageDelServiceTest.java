package com.github.dreamroute.distx.sample;

import com.github.dreamroute.distx.starter.domain.TxMessageDel;
import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.destination.DataSourceDestination;
import com.ninja_squad.dbsetup.operation.Insert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Timestamp;

import static com.ninja_squad.dbsetup.Operations.insertInto;
import static com.ninja_squad.dbsetup.Operations.truncate;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author w.dehai
 */
@SpringBootTest
class TxMessageDelServiceTest {

    @Autowired
    private TxMessageDelService txMessageDelService;
    @Autowired
    private DataSource dataSource;

    @BeforeEach
    void init() {
        new DbSetup(new DataSourceDestination(dataSource), truncate("tx_message_del")).launch();
    }

    @Test
    void insertTest() {
        TxMessageDel tmd = new TxMessageDel();
        tmd.setId(1L);
        tmd.setTopic("topic");
        tmd.setTag("tag");
        tmd.setBody("body");
        tmd.setCreateTime(new Timestamp(System.currentTimeMillis()));
        int result = txMessageDelService.insert(tmd);
        assertEquals(1, result);
    }

    @Test
    void selectByPrimaryKeyTest() {
        Insert insert = insertInto("tx_message_del")
                .columns("id", "topic", "tag", "body", "create_time")
                .values(1L, "topic", "tag", "body-del", new Timestamp(System.currentTimeMillis()))
                .build();
        new DbSetup(new DataSourceDestination(dataSource), insert).launch();
        TxMessageDel txMessageDel = txMessageDelService.selectById(1L);
        assertEquals("body-del", txMessageDel.getBody());
    }
}

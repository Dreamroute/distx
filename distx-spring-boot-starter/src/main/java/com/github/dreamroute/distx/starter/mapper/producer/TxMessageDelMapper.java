package com.github.dreamroute.distx.starter.mapper.producer;

import com.github.dreamroute.distx.starter.domain.TxMessageDel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

/**
 * 
 * @author w.dehai
 *
 */
public interface TxMessageDelMapper {

    @Insert("INSERT INTO `me`.`tx_message_del`(`id`, `topic`, `tag`, `body`, `create_time`) VALUES (#{id}, #{topic}, #{tag}, #{body}, #{createTime});")
    int insert(TxMessageDel txMessageDel);

    @Select("select id, topic, tag, body, create_time createTime from tx_message_del where id = #{id}")
    TxMessageDel selectByPrimaryKey(Long id);
}

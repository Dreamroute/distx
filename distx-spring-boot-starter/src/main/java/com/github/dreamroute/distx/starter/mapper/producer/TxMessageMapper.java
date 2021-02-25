package com.github.dreamroute.distx.starter.mapper.producer;

import com.github.dreamroute.distx.starter.domain.TxMessage;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author w.dehai
 */
public interface TxMessageMapper {

    @Insert("insert into tx_message(`topic`, `tag`, `body`, `create_time`) values(#{topic}, #{tag}, #{body}, #{createTime})")
    int insert(TxMessage message);

    @Select("select id, topic, tag, body, create_time createTime from tx_message where id = #{id}")
    TxMessage selectByPrimaryKey(Long id);

    @Delete("delete from tx_message where id = #{id} limit 1")
    void deleteByPrimaryKey(Long id);

    @Select("select id, topic, tag, body, create_time createTime from tx_message where faild_times < #{faildTimes} limit #{start}, #{limit}")
    List<TxMessage> selectTxMessageByPage(@Param("start") int start, @Param("limit") int limit, @Param("faildTimes") int faildTimes);

    @Update("update tx_message set faild_times = faild_times + 1 where id = #{id}")
    void addFaildTimes(@Param("id") long id);
}

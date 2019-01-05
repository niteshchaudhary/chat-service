package com.ua.chat.db;

import java.util.List;

import com.ua.chat.api.Message;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

@RegisterMapper(MessageMapper.class)
public interface MessageDao {

    @SqlQuery("select * from message;")
    public List<Message> getMessages();

    @SqlQuery("select * from messages where id = :id")
    public Message getMessage(@Bind("id") final int id);

    @SqlUpdate("insert into messages(name, code) values(:name, :code)")
    void createMessage(@BindBean final Message part);

    @SqlUpdate("update messages set name = coalesce(:name, name), code = coalesce(:code, code) where id = :id")
    void editMessage(@BindBean final Message part);

    @SqlUpdate("delete from messages where id = :id")
    int deleteMessages(@Bind("id") final int id);

    @SqlQuery("select last_insert_id();")
    public int lastInsertId();

}

package com.ua.chat.db;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import com.ua.chat.core.Message;

//package com.ua.chat.db;
//
//import com.ua.chat.api.Message;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//import org.skife.jdbi.v2.StatementContext;
//import org.skife.jdbi.v2.tweak.ResultSetMapper;
//
public class MessageMapper implements ResultSetMapper<Message>{

	@Override
	public Message map(int index, ResultSet r, StatementContext ctx) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
//    private static final String ID = "id";
//    private static final String NAME = "name";
//    private static final String CODE = "code";
//
//    public Message map(int i, ResultSet resultSet, StatementContext statementContext)
//            throws SQLException {
//        return new Message(resultSet.getInt(ID)); //, resultSet.getString(NAME), resultSet.getString(CODE));
//    }
//
}

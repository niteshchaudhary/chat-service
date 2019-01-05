package com.ua.chat.core;

import java.util.List;
import java.util.Objects;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;

import com.ua.chat.api.Message;
import com.ua.chat.db.MessageDao;
import org.skife.jdbi.v2.exceptions.UnableToExecuteStatementException;
import org.skife.jdbi.v2.exceptions.UnableToObtainConnectionException;
import org.skife.jdbi.v2.sqlobject.CreateSqlObject;

//import com.toptal.blog.dao.PartsDao;
//import com.toptal.blog.model.Part;

public abstract class ChatService {
    private static final String PART_NOT_FOUND = "Part id %s not found.";
    private static final String DATABASE_REACH_ERROR =
            "Could not reach the MySQL database. The database may be down or there may be network connectivity issues. Details: ";
    private static final String DATABASE_CONNECTION_ERROR =
            "Could not create a connection to the MySQL database. The database configurations are likely incorrect. Details: ";
    private static final String DATABASE_UNEXPECTED_ERROR =
            "Unexpected error occurred while attempting to reach the database. Details: ";
    private static final String SUCCESS = "Success...";
    private static final String UNEXPECTED_ERROR = "An unexpected error occurred while deleting part.";

    @CreateSqlObject
    abstract MessageDao partsDao();


    public List<Message> getMessages() {
        System.out.println("1111111");
        return partsDao().getMessages();
    }

    public Message getPart(int id) {
        Message part = partsDao().getMessage(id);
        if (Objects.isNull(part)) {
            throw new WebApplicationException(String.format(PART_NOT_FOUND, id), Status.NOT_FOUND);
        }
        return part;
    }

//    public Message createPart(Message message) {
//        partsDao().createPart(message);
//        return partsDao().getPart(partsDao().lastInsertId());
//    }
//
//    public Message editPart(Message part) {
//        if (Objects.isNull(partsDao().getPart(part.getId()))) {
//            throw new WebApplicationException(String.format(PART_NOT_FOUND, part.getId()),
//                    Status.NOT_FOUND);
//        }
//        partsDao().editPart(part);
//        return partsDao().getPart(part.getId());
//    }
//
//    public String deletePart(final int id) {
//        int result = partsDao().deletePart(id);
//        switch (result) {
//            case 1:
//                return SUCCESS;
//            case 0:
//                throw new WebApplicationException(String.format(PART_NOT_FOUND, id), Status.NOT_FOUND);
//            default:
//                throw new WebApplicationException(UNEXPECTED_ERROR, Status.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    public String performHealthCheck() {
//        try {
//            partsDao().getParts();
//        } catch (UnableToObtainConnectionException ex) {
//            return checkUnableToObtainConnectionException(ex);
//        } catch (UnableToExecuteStatementException ex) {
//            return checkUnableToExecuteStatementException(ex);
//        } catch (Exception ex) {
//            return DATABASE_UNEXPECTED_ERROR + ex.getCause().getLocalizedMessage();
//        }
//        return null;
//    }
//
//    private String checkUnableToObtainConnectionException(UnableToObtainConnectionException ex) {
//        if (ex.getCause() instanceof java.sql.SQLNonTransientConnectionException) {
//            return DATABASE_REACH_ERROR + ex.getCause().getLocalizedMessage();
//        } else if (ex.getCause() instanceof java.sql.SQLException) {
//            return DATABASE_CONNECTION_ERROR + ex.getCause().getLocalizedMessage();
//        } else {
//            return DATABASE_UNEXPECTED_ERROR + ex.getCause().getLocalizedMessage();
//        }
//    }
//
//    private String checkUnableToExecuteStatementException(UnableToExecuteStatementException ex) {
//        if (ex.getCause() instanceof java.sql.SQLSyntaxErrorException) {
//            return DATABASE_CONNECTION_ERROR + ex.getCause().getLocalizedMessage();
//        } else {
//            return DATABASE_UNEXPECTED_ERROR + ex.getCause().getLocalizedMessage();
//        }
//    }
}

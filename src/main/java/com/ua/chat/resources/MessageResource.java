package com.ua.chat.resources;

import com.ua.chat.core.Message;
import com.ua.chat.db.MessageDAO;

import io.dropwizard.hibernate.UnitOfWork;
import io.swagger.annotations.*;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;


@SuppressWarnings("RSReferenceInspection")
@Path("/message")
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "Message api", description = "Message api")
public class MessageResource {


	 private final MessageDAO messageDAO;

	    public MessageResource(MessageDAO messageDAO) {
	        this.messageDAO = messageDAO;
	    }

    @POST
    @ApiOperation(
            value = "Create a message")
    @Path("/message/")
    @ApiResponses({
            @ApiResponse(code = 200, message = "message was created"),
            @ApiResponse(code = 400, message = "'userName' and 'text' are required in the payload"),
    })
    @UnitOfWork
    public Response createMessage(
            @ApiParam(value = "message to be created", required = true)
            @Valid Message chatMessageResponse
    ) {
		        //create aa new id and return
    	messageDAO.create(chatMessageResponse);
        return Response.ok(chatMessageResponse.getId()).build();
    }

    @GET
    @ApiOperation(value = "Returns all messages.")
    @Path("/all/")
    @UnitOfWork
    public List<Message> getTexts(
            @ApiParam(value = "Corresponding user's messages", required = true)
            @PathParam("userName") String userName) {
    	return messageDAO.findAll();

    }

    @GET
    @ApiOperation(value = "Returns all messages.")
    @Path("/findById/")
    @UnitOfWork
    public Optional<Message> getTextById(
            @ApiParam(value = "Message for a Id", required = true)
            @QueryParam("id") int id) {
        return messageDAO.findById(id);

    }

    @GET
    @ApiOperation(value = "Returns all messages.")
    @Path("/findByName/")
    @UnitOfWork
    public List<Message> getTextsByName(
            @ApiParam(value = "Message for a Name", required = true)
            @QueryParam("name") String name) {
        return messageDAO.findByName(name);

    }

}

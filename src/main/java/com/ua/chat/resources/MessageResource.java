package com.ua.chat.resources;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.ua.chat.core.Message;
import com.ua.chat.db.ChatMessageDAO;

import io.dropwizard.hibernate.UnitOfWork;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@SuppressWarnings("RSReferenceInspection")
@Path("/message")
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "Message api", description = "Message api")
public class MessageResource {


	 private final ChatMessageDAO messageDAO;

	    public MessageResource(ChatMessageDAO messageDAO) {
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
    public List<Message> getTexts() {
    	return messageDAO.findAll();

    }

    @GET
    @ApiOperation(value = "Returns by id .")
    @Path("/findById/")
    @UnitOfWork
    public List<Message> getTextById(
            @ApiParam(value = "Message for a Id", required = true)
            @QueryParam("id") long id) {
        return messageDAO.findById(id);

    }

    @GET
    @ApiOperation(value = "Returns  messages by name")
    @Path("/findByName/")
    @UnitOfWork
    public List<Message> getTextsByName(
            @ApiParam(value = "Message for a Name", required = true)
            @QueryParam("name") String name) {
        return messageDAO.findByName(name);

    }

}

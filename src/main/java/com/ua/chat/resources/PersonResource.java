package com.ua.chat.resources;

import com.ua.chat.api.ChatMessageResponse;
import com.ua.chat.api.Message;
import com.ua.chat.api.UserMessageResponse;
import com.ua.chat.core.ChatService;
import com.ua.chat.core.Person;
import com.ua.chat.db.PersonDAO;

import io.dropwizard.hibernate.UnitOfWork;
//import com.ua.chat.db.ChatDB;
import io.swagger.annotations.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;


@SuppressWarnings("RSReferenceInspection")
@Path("/person")
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "Person api", description = "Person api")
public class PersonResource {

    
	 private final PersonDAO peopleDAO;

	    public PersonResource(PersonDAO peopleDAO) {
	        this.peopleDAO = peopleDAO;
	    }
	
    @POST
    @ApiOperation(
            value = "Create a person")
    @Path("/person/")
    @ApiResponses({
            @ApiResponse(code = 200, message = "person was created"),
            @ApiResponse(code = 400, message = "'userName' and 'text' are required in the payload"),
    })
    @UnitOfWork
    public Response createPerson(
            @ApiParam(value = "person to be created", required = true)
            @Valid Person chatMessageResponse
    ) {
		        //create aa new id and return
    	peopleDAO.create(chatMessageResponse);
        return Response.ok(chatMessageResponse).build();
    }
    
    @GET
    @ApiOperation(value = "Returns all persons.")
    @Path("/all/")
    @UnitOfWork
    public List<Person> getTexts(
            @ApiParam(value = "Corresponding user's messages", required = true)
            @PathParam("userName") String userName) {
    	return peopleDAO.findAll();
        
    }

}

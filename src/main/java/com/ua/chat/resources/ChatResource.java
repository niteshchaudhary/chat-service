package com.ua.chat.resources;

import com.ua.chat.api.ChatMessageResponse;
import com.ua.chat.api.Message;
import com.ua.chat.api.UserMessageResponse;
import com.ua.chat.core.ChatService;
import com.ua.chat.core.Person;

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
@Path("/events")
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "Chat api", description = "Chat api")
public class ChatResource {

    private ChatService chatService;

    public ChatResource(ChatService chatService) {
        this.chatService = chatService;
    }


    @GET
    @ApiOperation(value = "Returns the Chat message for given id.")
    @Path("/id/")
    public ChatMessageResponse getMessage(
        @ApiParam(value = "Corresponding user's chat id", required = true)
        @PathParam("id") int id) {

        ChatMessageResponse chatMessageResponse = new ChatMessageResponse();
        chatMessageResponse.setName("test");
        chatMessageResponse.setText("text");
        chatMessageResponse.setExpirationDate(Instant.now());

       // Message message = ChatDB.getMessage(1);
        System.out.println("11111111");
        //System.out.println(ChatDB.getMessage(1).toString());
       // System.out.println(chatDB.findAll());
        System.out.println(chatService.getMessages());
        return chatMessageResponse;
    }

    @GET
    @ApiOperation(value = "Returns the Chat messages for given user.")
    @Path("/username/")
    public UserMessageResponse getTexts(
            @ApiParam(value = "Corresponding user's messages", required = true)
            @PathParam("userName") String userName) {

        ArrayList<Message> messageList = new ArrayList<>();
        Message message1 = new Message(4);
        message1.setId(1);
        message1.setText("test");

        Message message2 = new Message(4);
        message2.setId(2);
        message2.setText("new text");
        messageList.add(message1);
        messageList.add(message2);


        UserMessageResponse userMessageResponse = new UserMessageResponse();
        userMessageResponse.setMessages(messageList);
        return userMessageResponse;
    }

    @POST
    @ApiOperation(
            value = "Create a message")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Message was created"),
            @ApiResponse(code = 400, message = "'userName' and 'text' are required in the payload"),
    })
    public Response create(
            @ApiParam(value = "Message body to be created", required = true)
            @Valid ChatMessageResponse chatMessageResponse
    ) {
        ChatMessageResponse chatMessageResponse1 = new ChatMessageResponse();
        chatMessageResponse1.setText("text");
        chatMessageResponse1.setName("new name");
        chatMessageResponse1.setExpirationDate(Instant.now());

        //create aa new id and return
        return Response.ok(chatMessageResponse1).build();
    }
    
    
    
    
}

package pl.kl.chat_rest.messages.adapters.rest;

import lombok.Setter;
import pl.kl.chat_rest.messages.domain.Message;
import pl.kl.chat_rest.messages.ports.MessageService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Setter
@Path("messages")
public class MessageController {

    @Inject
    private MessageService messageService;
    @Inject
    private RestMessageMapper messageMapper;

    @Produces(MediaType.APPLICATION_JSON)
    @GET
    @Path("client/{name}")
    public Response getMessagesByClientName(@PathParam("name") String name) {
        final List<Message> messages = messageService.getAllByClientName(name);
        final List<MessageDto> dtoList = messageMapper.toDto(messages);
        return Response.ok(dtoList)
                .build();
    }

    @Produces(MediaType.APPLICATION_JSON)
    @GET
    @Path("channel/{name}")
    public Response getMessagesByChannelName(@PathParam("name") String name) {
        final List<Message> messages = messageService.getAllByChannelName(name);
        final List<MessageDto> dtoList = messageMapper.toDto(messages);
        return Response.ok(dtoList)
                .build();
    }

}

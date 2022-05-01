package pl.kl.chat_app.channels.adapters.rest;

import lombok.Setter;
import pl.kl.chat_app.channels.domain.Channel;
import pl.kl.chat_app.channels.ports.ChannelService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@Path("channels")
public class ChannelController {

    @Setter
    @Context
    private UriInfo uriInfo;
    @Setter
    @Inject
    private ChannelService channelService;
    @Setter
    @Inject
    private RestChannelMapper channelMapper;

    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @POST
    public Response createChannel(ChannelDto channelDto) {
        final Channel channel = channelService.createChannel(channelDto.getName());
        final ChannelDto dto = channelMapper.toDto(channel);
        return Response.created(getLocation(dto.getName()))
                .entity(dto)
                .build();
    }

    @Produces(MediaType.APPLICATION_JSON)
    @GET
    @Path("{name}")
    public Response getChannelByName(@PathParam("name") String name) {
        final Channel channel = channelService.getChannelByName(name);
        final ChannelDto dto = channelMapper.toDto(channel);
        return Response.ok(getLocation(dto.getName()))
                .entity(dto)
                .build();
    }

    // TODO: sprawdzic czy to bedzie potrzebne
//    @Produces(MediaType.APPLICATION_JSON)
//    @GET
//    @Path("{id}")
//    public Response getChannelById(@PathParam("id") String id) {
//        final Channel channel = channelService.getChannelById(id);
//        final ChannelDto dto = channelMapper.toDto(channel);
//        return Response.ok(getLocation(dto.getId()))
//                .entity(dto)
//                .build();
//    }

    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public Response getAllClients() {
        final List<Channel> channels = channelService.getAllChannels();
        final List<ChannelDto> dtoList = channelMapper.toDto(channels);
        return Response.ok(dtoList)
                .build();
    }

    private URI getLocation(String id) {
        return uriInfo.getAbsolutePathBuilder().path(id).build();
    }

}

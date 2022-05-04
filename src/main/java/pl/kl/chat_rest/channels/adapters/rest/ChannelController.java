package pl.kl.chat_rest.channels.adapters.rest;

import lombok.Setter;
import pl.kl.chat_rest.channels.domain.Channel;
import pl.kl.chat_rest.channels.ports.ChannelService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@Setter
@Path("channels")
public class ChannelController {

    @Context
    private UriInfo uriInfo;
    @Inject
    private ChannelService channelService;
    @Inject
    private RestChannelMapper channelMapper;

    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @POST
    public Response createChannel(ChannelCreateDto channelDto) {
        final Channel channel = channelService.create(channelDto.getName());
        final ChannelDto dto = channelMapper.toDto(channel);
        return Response.created(getLocation(dto.getName()))
                .entity(dto)
                .build();
    }

    @Produces(MediaType.APPLICATION_JSON)
    @GET
    @Path("{name}")
    public Response getChannelByName(@PathParam("name") String name) {
        final Channel channel = channelService.getByName(name);
        final ChannelDto dto = channelMapper.toDto(channel);
        return Response.ok(getLocation(dto.getName()))
                .entity(dto)
                .build();
    }

    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public Response getAllClients() {
        final List<Channel> channels = channelService.getAll();
        final List<ChannelDto> dtoList = channelMapper.toDto(channels);
        return Response.ok(dtoList)
                .build();
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @PUT
    public Response setChannelClient(ChannelUpdateDto channelDto) {
        final Channel channel = channelService.addClient(channelDto.getName(), channelDto.getClientName());
        final ChannelDto dto = channelMapper.toDto(channel);
        return Response.ok(getLocation(dto.getName()))
                .entity(dto)
                .build();
    }

    private URI getLocation(String id) {
        return uriInfo.getAbsolutePathBuilder().path(id).build();
    }

}

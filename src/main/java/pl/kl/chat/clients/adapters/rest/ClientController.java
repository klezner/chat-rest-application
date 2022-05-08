package pl.kl.chat.clients.adapters.rest;

import lombok.Setter;
import pl.kl.chat.clients.domain.Client;
import pl.kl.chat.clients.ports.ClientService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@Setter
@Path("clients")
public class ClientController {

    @Context
    private UriInfo uriInfo;
    @Inject
    private ClientService clientService;
    @Inject
    private RestClientMapper clientMapper;

    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @POST
    public Response createClient(ClientCreateDto clientDto) {
        final Client client = clientService.create(clientDto.getName());
        final ClientDto dto = clientMapper.toDto(client);
        return Response.created(getLocation(dto.getName()))
                .entity(dto)
                .build();
    }

    @Produces(MediaType.APPLICATION_JSON)
    @GET
    @Path("{name}")
    public Response getClientByName(@PathParam("name") String name) {
        final Client client = clientService.getByName(name);
        final ClientDto dto = clientMapper.toDto(client);
        return Response.ok(getLocation(dto.getName()))
                .entity(dto)
                .build();
    }

    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public Response getAllClients() {
        final List<Client> clients = clientService.getAll();
        final List<ClientDto> dtoList = clientMapper.toDto(clients);
        return Response.ok(dtoList)
                .build();
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @PUT
    public Response setClientActiveChannel(ClientUpdateDto clientDto) {
        final Client client = clientService.setActiveChannel(clientDto.getName(), clientDto.getActiveChannel());
        final ClientDto dto = clientMapper.toDto(client);
        return Response.ok(getLocation(dto.getName()))
                .entity(dto)
                .build();
    }

    private URI getLocation(String id) {
        return uriInfo.getAbsolutePathBuilder().path(id).build();
    }

}

package pl.kl.chat_app.clients.adapters.rest;

import lombok.Setter;
import pl.kl.chat_app.clients.domain.Client;
import pl.kl.chat_app.clients.ports.ClientService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@Path("clients")
public class ClientController {

    @Setter
    @Context
    private UriInfo uriInfo;
    @Setter
    @Inject
    private ClientService clientService;
    @Setter
    @Inject
    private RestClientMapper clientMapper;

    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @POST
    public Response createClient(ClientDto clientDto) {
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
        final List<Client> clients = clientService.getAllClients();
        final List<ClientDto> dtoList = clientMapper.toDto(clients);
        return Response.ok(dtoList)
                .build();
    }

    private URI getLocation(String id) {
        return uriInfo.getAbsolutePathBuilder().path(id).build();
    }

}

package pl.kl.chat_client.handlers.rest.clients;

import lombok.RequiredArgsConstructor;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import pl.kl.chat_client.common.ExceptionDto;
import pl.kl.chat_client.common.ResponseDto;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@RequiredArgsConstructor
public class ClientRestClient implements ClientClient {

    private static final String CLIENTS_RESOURCE_PATH = "http://localhost:8080/chat-rest-application-1.0-SNAPSHOT/api/clients";
    private static final String DEFAULT_CHANNEL_NAME = "general";
    private final ResteasyClient resteasyClient;

    @Override
    public ResponseDto createClient(String name) {
        final ClientCreateDto clientCreateDto = ClientCreateDto.builder()
                .name(name)
                .build();
        final Response response = resteasyClient.target(CLIENTS_RESOURCE_PATH)
                .request()
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.entity(clientCreateDto, MediaType.APPLICATION_JSON));
        if (Response.Status.CREATED.getStatusCode() == response.getStatus()) {
            return response.readEntity(ClientDto.class);
        }
        return response.readEntity(ExceptionDto.class);
    }

    @Override
    public ResponseDto getClientByName(String name) {
        final Response response = resteasyClient.target(CLIENTS_RESOURCE_PATH)
                .path("{name}")
                .resolveTemplate("name", name)
                .request()
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .get();
        if (Response.Status.OK.getStatusCode() == response.getStatus()) {
            return response.readEntity(ClientDto.class);
        }
        return response.readEntity(ExceptionDto.class);
    }

    @Override
    public List<ClientDto> getAllClients() {
        final Response response = resteasyClient.target(CLIENTS_RESOURCE_PATH)
                .request()
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .get();
        return response.readEntity(new GenericType<List<ClientDto>>() {
        });
    }

    @Override
    public ResponseDto setClientActiveChannel(String name, String activeChannel) {
        final ClientUpdateDto clientUpdateDto = ClientUpdateDto.builder()
                .name(name)
                .activeChannel(activeChannel)
                .build();
        final Response response = resteasyClient.target(CLIENTS_RESOURCE_PATH)
                .request()
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .put(Entity.entity(clientUpdateDto, MediaType.APPLICATION_JSON));
        if (Response.Status.OK.getStatusCode() == response.getStatus()) {
            return response.readEntity(ClientDto.class);
        }
        return response.readEntity(ExceptionDto.class);
    }

}

package pl.kl.chat.handlers.rest.messages;

import lombok.RequiredArgsConstructor;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@RequiredArgsConstructor
public class MessageRestClient implements MessageClient {

    private static final String CLIENT_MESSAGES_RESOURCE_PATH = "http://localhost:8080/chat-rest-application-1.0-SNAPSHOT/api/messages/client";
    private static final String CHANNEL_MESSAGES_RESOURCE_PATH = "http://localhost:8080/chat-rest-application-1.0-SNAPSHOT/api/messages/channel";
    private final ResteasyClient resteasyClient;

    @Override
    public List<MessageDto> getMessagesByClientName(String name) {
        final Response response = resteasyClient.target(CLIENT_MESSAGES_RESOURCE_PATH)
                .path("{name}")
                .resolveTemplate("name", name)
                .request()
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .get();
        return response.readEntity(new GenericType<List<MessageDto>>() {
        });
    }

    @Override
    public List<MessageDto> getMessagesByChannelName(String name) {
        final Response response = resteasyClient.target(CHANNEL_MESSAGES_RESOURCE_PATH)
                .path("{name}")
                .resolveTemplate("name", name)
                .request()
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .get();
        return response.readEntity(new GenericType<List<MessageDto>>() {
        });
    }

}

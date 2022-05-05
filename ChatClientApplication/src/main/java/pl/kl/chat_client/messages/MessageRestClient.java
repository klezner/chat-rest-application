package pl.kl.chat_client.messages;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Log
@RequiredArgsConstructor
public class MessageRestClient {

    private static final String CLIENT_MESSAGES_RESOURCE_PATH = "http://localhost:8080/chat-rest-application-1.0-SNAPSHOT/api/messages/client";
    private static final String CHANNEL_MESSAGES_RESOURCE_PATH = "http://localhost:8080/chat-rest-application-1.0-SNAPSHOT/api/messages/channel";
    private final ResteasyClient resteasyClient;

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

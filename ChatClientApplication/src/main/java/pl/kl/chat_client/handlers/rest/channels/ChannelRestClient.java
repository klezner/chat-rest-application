package pl.kl.chat_client.handlers.rest.channels;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import pl.kl.chat_client.common.ExceptionDto;
import pl.kl.chat_client.common.ResponseDto;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Log
@RequiredArgsConstructor
public class ChannelRestClient implements ChannelClient {

    private static final String CHANNELS_RESOURCE_PATH = "http://localhost:8080/chat-rest-application-1.0-SNAPSHOT/api/channels";
    private final ResteasyClient resteasyClient;

    @Override
    public ResponseDto createChannel(String name) {
        final ChannelCreateDto channelCreateDto = ChannelCreateDto.builder()
                .name(name)
                .build();
        final Response response = resteasyClient.target(CHANNELS_RESOURCE_PATH)
                .request()
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.entity(channelCreateDto, MediaType.APPLICATION_JSON));
        if (response.getStatus() == Response.Status.CREATED.getStatusCode()) {
            final ChannelDto channelDto = response.readEntity(ChannelDto.class);
            // TODO: usunąć
            log.info("createChannel: " + channelDto.getId() + " - " + channelDto.getName() + " - " + String.join(" ", channelDto.getClients()));
            //
            return channelDto;
        }
        final ExceptionDto exceptionDto = response.readEntity(ExceptionDto.class);
        // TODO: usunąć
        log.info("exception: " + exceptionDto.getTimestamp() + " - " + exceptionDto.getDescription());
        //
        return exceptionDto;
    }

    @Override
    public ResponseDto getChannelByName(String name) {
        final Response response = resteasyClient.target(CHANNELS_RESOURCE_PATH)
                .path("{name}")
                .resolveTemplate("name", name)
                .request()
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .get();
        if (response.getStatus() == Response.Status.OK.getStatusCode()) {
            final ChannelDto channelDto = response.readEntity(ChannelDto.class);
            // TODO: usunąć
            log.info("getChannelByName: " + channelDto.getId() + " - " + channelDto.getName() + " - " + String.join(" ", channelDto.getClients()));
            //
            return channelDto;
        }
        final ExceptionDto exceptionDto = response.readEntity(ExceptionDto.class);
        // TODO: usunąć
        log.info("exception: " + exceptionDto.getTimestamp() + " - " + exceptionDto.getDescription());
        //
        return exceptionDto;
    }

    @Override
    public List<ChannelDto> getAllChannels() {
        final Response response = resteasyClient.target(CHANNELS_RESOURCE_PATH)
                .request()
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .get();
        return response.readEntity(new GenericType<List<ChannelDto>>() {
        });
    }

    @Override
    public String setChannelClient(String name, String clientName) {
        final ChannelUpdateDto channelUpdateDto = ChannelUpdateDto.builder()
                .name(name)
                .clientName(clientName)
                .build();
        final Response response = resteasyClient.target(CHANNELS_RESOURCE_PATH)
                .request()
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .put(Entity.entity(channelUpdateDto, MediaType.APPLICATION_JSON));
        if (response.getStatus() == Response.Status.OK.getStatusCode()) {
            final ChannelDto channelDto = response.readEntity(ChannelDto.class);
            // TODO: usunąć
            log.info("setChannelClient: " + channelDto.getId() + " - " + channelDto.getName() + " - " + String.join(" ", channelDto.getClients()));
            //
            return channelDto.toString();
        }
        final ExceptionDto exceptionDto = response.readEntity(ExceptionDto.class);
        // TODO: usunąć
        log.info("exception: " + exceptionDto.getTimestamp() + " - " + exceptionDto.getDescription());
        //
        return exceptionDto.getDescription();
    }

}

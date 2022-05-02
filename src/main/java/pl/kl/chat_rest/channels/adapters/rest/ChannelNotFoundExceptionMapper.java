package pl.kl.chat_rest.channels.adapters.rest;

import pl.kl.chat_rest.channels.domain.ChannelNotFoundException;
import pl.kl.chat_rest.common.ExceptionDto;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ChannelNotFoundExceptionMapper implements ExceptionMapper<ChannelNotFoundException> {

    private static final String DEFAULT_MESSAGE = "Channel not found";

    @Override
    public Response toResponse(ChannelNotFoundException e) {
        return Response.status(Response.Status.NOT_FOUND)
                .entity(new ExceptionDto(DEFAULT_MESSAGE))
                .build();
    }

}

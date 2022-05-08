package pl.kl.chat.channels.adapters.rest;

import pl.kl.chat.channels.domain.ChannelNotFoundException;
import pl.kl.chat.common.ExceptionDto;

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

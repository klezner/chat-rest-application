package pl.kl.chat.channels.adapters.rest;

import pl.kl.chat.channels.domain.ChannelAlreadyExistsException;
import pl.kl.chat.common.ExceptionDto;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ChannelAlreadyExistsExceptionMapper implements ExceptionMapper<ChannelAlreadyExistsException> {

    private static final String DEFAULT_MESSAGE = "Channel already exists";

    @Override
    public Response toResponse(ChannelAlreadyExistsException e) {
        return Response.status(Response.Status.NOT_FOUND)
                .entity(new ExceptionDto(DEFAULT_MESSAGE))
                .build();
    }

}

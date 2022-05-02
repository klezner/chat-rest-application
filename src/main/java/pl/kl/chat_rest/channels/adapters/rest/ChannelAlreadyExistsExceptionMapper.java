package pl.kl.chat_rest.channels.adapters.rest;

import pl.kl.chat_rest.channels.domain.ChannelAlreadyExistsException;
import pl.kl.chat_rest.common.ExceptionDto;

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

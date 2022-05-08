package pl.kl.chat.clients.adapters.rest;

import pl.kl.chat.clients.domain.ClientAlreadyExistsException;
import pl.kl.chat.common.ExceptionDto;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ClientAlreadyExistsExceptionMapper implements ExceptionMapper<ClientAlreadyExistsException> {

    private static final String DEFAULT_MESSAGE = "Client already exists";

    @Override
    public Response toResponse(ClientAlreadyExistsException e) {
        return Response.status(Response.Status.NOT_FOUND)
                .entity(new ExceptionDto(DEFAULT_MESSAGE))
                .build();
    }

}

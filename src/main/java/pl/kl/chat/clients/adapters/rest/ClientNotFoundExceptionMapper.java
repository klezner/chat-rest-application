package pl.kl.chat.clients.adapters.rest;

import pl.kl.chat.clients.domain.ClientNotFoundException;
import pl.kl.chat.common.ExceptionDto;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ClientNotFoundExceptionMapper implements ExceptionMapper<ClientNotFoundException> {

    private static final String DEFAULT_MESSAGE = "Client not found";

    @Override
    public Response toResponse(ClientNotFoundException exception) {
        return Response.status(Response.Status.NOT_FOUND)
                .entity(new ExceptionDto(DEFAULT_MESSAGE))
                .build();
    }

}

package pl.kl.chat_client.handlers.rest.clients;

import pl.kl.chat_client.common.ResponseDto;

import java.util.List;

public interface ClientClient {

    ResponseDto createClient(String name);

    ResponseDto getClientByName(String name);

    List<ClientDto> getAllClients();

    ResponseDto setClientActiveChannel(String name, String activeChannel);

}

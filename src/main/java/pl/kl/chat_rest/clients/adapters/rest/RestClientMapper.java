package pl.kl.chat_rest.clients.adapters.rest;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import pl.kl.chat_rest.clients.domain.Client;

import java.util.List;

@Mapper
public interface RestClientMapper {

    Client toDomain(ClientDto dto);

    ClientDto toDto(Client client);

    @IterableMapping(elementTargetType = ClientDto.class)
    List<ClientDto> toDto(List<Client> clients);

}

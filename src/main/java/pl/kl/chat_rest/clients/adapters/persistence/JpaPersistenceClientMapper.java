package pl.kl.chat_rest.clients.adapters.persistence;

import org.mapstruct.Mapper;
import pl.kl.chat_rest.clients.domain.Client;

@Mapper
public interface JpaPersistenceClientMapper {

    ClientEntity toEntity(Client client);

    Client toDomain(ClientEntity entity);

}

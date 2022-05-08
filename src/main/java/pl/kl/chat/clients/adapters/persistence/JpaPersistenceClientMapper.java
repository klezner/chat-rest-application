package pl.kl.chat.clients.adapters.persistence;

import org.mapstruct.Mapper;
import pl.kl.chat.clients.domain.Client;

@Mapper
public interface JpaPersistenceClientMapper {

    ClientEntity toEntity(Client client);

    Client toDomain(ClientEntity entity);

}

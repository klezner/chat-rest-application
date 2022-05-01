package pl.kl.chat_app.clients.adapters.persistence;

import org.mapstruct.Mapper;
import pl.kl.chat_app.clients.domain.Client;

@Mapper(componentModel = "cdi")
public interface JpaPersistenceClientMapper {

    ClientEntity toEntity(Client client);

    Client toDomain(ClientEntity entity);

}

package pl.kl.chat_rest.clients.ports;

import pl.kl.chat_rest.clients.domain.Client;

import java.util.List;
import java.util.Optional;

public interface ClientRepository {

    Client save(Client client);

    Optional<Client> getByName(String name);

    List<Client> getAll();

    Client update(Client client);

}

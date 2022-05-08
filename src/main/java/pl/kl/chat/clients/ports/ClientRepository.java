package pl.kl.chat.clients.ports;

import pl.kl.chat.clients.domain.Client;

import java.util.List;
import java.util.Optional;

public interface ClientRepository {

    Client save(Client client);

    Optional<Client> getByName(String name);

    List<Client> getAll();

    Client update(Client client);

}

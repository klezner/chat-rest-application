package pl.kl.chat_app.clients.ports;

import pl.kl.chat_app.clients.domain.Client;

import java.util.List;
import java.util.Optional;

public interface ClientRepository {

    Client save(Client client);

    Optional<Client> getByName(String name);

    List<Client> getAll();

}

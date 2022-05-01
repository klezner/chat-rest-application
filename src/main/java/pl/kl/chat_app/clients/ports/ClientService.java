package pl.kl.chat_app.clients.ports;

import pl.kl.chat_app.clients.domain.Client;

import java.util.List;

public interface ClientService {

    Client create(String name);

    Client getByName(String name);

    List<Client> getAllClients();

}

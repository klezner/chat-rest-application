package pl.kl.chat_rest.clients.ports;

import pl.kl.chat_rest.clients.domain.Client;

import java.util.List;

public interface ClientService {

    Client create(String name);

    Client getByName(String name);

    List<Client> getAll();

    Client setActiveChannel(String name, String activeChannel);

}

package pl.kl.chat.clients.ports;

import pl.kl.chat.clients.domain.Client;

import java.util.List;

public interface ClientService {

    Client create(String name);

    Client getByName(String name);

    List<Client> getAll();

    Client setActiveChannel(String name, String activeChannel);

}

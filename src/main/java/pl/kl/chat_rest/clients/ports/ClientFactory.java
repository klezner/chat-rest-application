package pl.kl.chat_rest.clients.ports;

import pl.kl.chat_rest.common.IdGenerator;

public interface ClientFactory {

    ClientService clientService(IdGenerator idGenerator, ClientRepository clientRepository);

}

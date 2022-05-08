package pl.kl.chat.clients.ports;

import pl.kl.chat.common.IdGenerator;

public interface ClientFactory {

    ClientService clientService(IdGenerator idGenerator, ClientRepository clientRepository);

}

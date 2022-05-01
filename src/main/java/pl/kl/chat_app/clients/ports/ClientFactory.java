package pl.kl.chat_app.clients.ports;

import pl.kl.chat_app.common.IdGenerator;

public interface ClientFactory {

    ClientService clientService(IdGenerator idGenerator, ClientRepository clientRepository);

}

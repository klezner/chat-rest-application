package pl.kl.chat.clients.domain;

import pl.kl.chat.clients.ports.ClientFactory;
import pl.kl.chat.clients.ports.ClientRepository;
import pl.kl.chat.clients.ports.ClientService;
import pl.kl.chat.common.IdGenerator;

public class MainClientFactory implements ClientFactory {

    @Override
    public ClientService clientService(IdGenerator idGenerator, ClientRepository clientRepository) {
        return new ClientProcessor(idGenerator, clientRepository);
    }

}

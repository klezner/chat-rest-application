package pl.kl.chat_rest.clients.domain;

import pl.kl.chat_rest.clients.ports.ClientFactory;
import pl.kl.chat_rest.clients.ports.ClientRepository;
import pl.kl.chat_rest.clients.ports.ClientService;
import pl.kl.chat_rest.common.IdGenerator;

public class MainClientFactory implements ClientFactory {

    @Override
    public ClientService clientService(IdGenerator idGenerator, ClientRepository clientRepository) {
        return new ClientProcessor(idGenerator, clientRepository);
    }

}

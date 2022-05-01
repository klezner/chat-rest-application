package pl.kl.chat_app.clients.domain;

import pl.kl.chat_app.clients.ports.ClientFactory;
import pl.kl.chat_app.clients.ports.ClientRepository;
import pl.kl.chat_app.clients.ports.ClientService;
import pl.kl.chat_app.common.IdGenerator;

public class MainClientFactory implements ClientFactory {

    @Override
    public ClientService clientService(IdGenerator idGenerator, ClientRepository clientRepository) {
        return new ClientProcessor(idGenerator, clientRepository);
    }

}

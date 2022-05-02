package pl.kl.chat_rest.clients;

import pl.kl.chat_rest.clients.domain.MainClientFactory;
import pl.kl.chat_rest.clients.ports.ClientFactory;
import pl.kl.chat_rest.clients.ports.ClientRepository;
import pl.kl.chat_rest.clients.ports.ClientService;
import pl.kl.chat_rest.common.IdGenerator;

import javax.enterprise.inject.Produces;
import javax.inject.Singleton;

@Singleton
public class ClientConfiguration {

    private static final ClientFactory CLIENT_FACTORY = new MainClientFactory();

    @Singleton
    @Produces
    public ClientService clientService(IdGenerator idGenerator, ClientRepository clientRepository) {
        return CLIENT_FACTORY.clientService(idGenerator, clientRepository);
    }

}

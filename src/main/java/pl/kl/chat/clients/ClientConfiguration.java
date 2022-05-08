package pl.kl.chat.clients;

import pl.kl.chat.clients.domain.MainClientFactory;
import pl.kl.chat.clients.ports.ClientFactory;
import pl.kl.chat.clients.ports.ClientRepository;
import pl.kl.chat.clients.ports.ClientService;
import pl.kl.chat.common.IdGenerator;

import javax.enterprise.inject.Produces;
import javax.inject.Singleton;

@Singleton
class ClientConfiguration {

    private static final ClientFactory CLIENT_FACTORY = new MainClientFactory();

    @Singleton
    @Produces
    public ClientService clientService(IdGenerator idGenerator, ClientRepository clientRepository) {
        return CLIENT_FACTORY.clientService(idGenerator, clientRepository);
    }

}

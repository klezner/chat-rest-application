package pl.kl.chat_app.clients;

import pl.kl.chat_app.clients.domain.MainClientFactory;
import pl.kl.chat_app.clients.ports.ClientFactory;
import pl.kl.chat_app.clients.ports.ClientRepository;
import pl.kl.chat_app.clients.ports.ClientService;
import pl.kl.chat_app.common.IdGenerator;

import javax.enterprise.inject.Produces;
import javax.inject.Singleton;

@Singleton
public class ClientConfiguration {

    private static final ClientFactory CLIENT_FACTORY = new MainClientFactory();

//    @Singleton
//    @Produces
//    public JpaPersistenceClientMapper jpaPersistenceClientMapper() {
//        return Mappers.getMapper(JpaPersistenceClientMapper.class);
//    }

    @Singleton
    @Produces
    public ClientService clientService(IdGenerator idGenerator, ClientRepository clientRepository) {
        return CLIENT_FACTORY.clientService(idGenerator, clientRepository);
    }

//    @Singleton
//    @Produces
//    public RestClientMapper restClientMapper() {
//        return Mappers.getMapper(RestClientMapper.class);
//    }

}

package pl.kl.chat.clients.domain;

import lombok.RequiredArgsConstructor;
import pl.kl.chat.clients.ports.ClientRepository;
import pl.kl.chat.clients.ports.ClientService;
import pl.kl.chat.common.IdGenerator;

import java.util.List;

@RequiredArgsConstructor
class ClientProcessor implements ClientService {

    private static final String DEFAULT_CHANNEL = "general";
    private final IdGenerator idGenerator;
    private final ClientRepository clientRepository;

    @Override
    public Client create(String name) {
        ifClientExists(name);
        final Client client = Client.builder()
                .id(idGenerator.getNext())
                .name(name)
                .activeChannel(DEFAULT_CHANNEL)
                .build();
        return clientRepository.save(client);
    }

    private void ifClientExists(String name) {
        if (clientRepository.getByName(name).isPresent()) {
            throw new ClientAlreadyExistsException();
        }
    }

    @Override
    public Client getByName(String name) {
        return clientRepository.getByName(name)
                .orElseThrow(ClientNotFoundException::new);
    }

    @Override
    public List<Client> getAll() {
        return clientRepository.getAll();
    }

    @Override
    public Client setActiveChannel(String name, String activeChannel) {
        final Client client = getByName(name);
        client.setActiveChannel(activeChannel);
        return clientRepository.update(client);
    }
}

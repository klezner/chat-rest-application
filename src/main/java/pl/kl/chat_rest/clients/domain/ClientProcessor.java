package pl.kl.chat_rest.clients.domain;

import lombok.RequiredArgsConstructor;
import pl.kl.chat_rest.clients.ports.ClientRepository;
import pl.kl.chat_rest.clients.ports.ClientService;
import pl.kl.chat_rest.common.IdGenerator;

import java.util.List;

@RequiredArgsConstructor
class ClientProcessor implements ClientService {

    private final IdGenerator idGenerator;
    private final ClientRepository clientRepository;

    @Override
    public Client create(String name) {
        if (clientRepository.getByName(name).isPresent()) {
            throw new ClientAlreadyExistsException();
        }
        final Client client = Client.builder()
                .id(idGenerator.getNext())
                .name(name)
//                .activeChannel(new Channel()) // TODO: ustawic defaultowy channel
                .build();
        return clientRepository.save(client);
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
}

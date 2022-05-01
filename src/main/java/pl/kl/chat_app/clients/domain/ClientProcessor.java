package pl.kl.chat_app.clients.domain;

import lombok.RequiredArgsConstructor;
import pl.kl.chat_app.clients.ports.ClientRepository;
import pl.kl.chat_app.clients.ports.ClientService;
import pl.kl.chat_app.common.IdGenerator;

import java.util.List;

@RequiredArgsConstructor
class ClientProcessor implements ClientService {

    private final IdGenerator idGenerator;
    private final ClientRepository clientRepository;

    @Override
    public Client create(String name) {
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
    public List<Client> getAllClients() {
        return clientRepository.getAll();
    }
}

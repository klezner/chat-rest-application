package pl.kl.chat_rest.clients.adapters.persistence;

import lombok.RequiredArgsConstructor;
import pl.kl.chat_rest.clients.domain.Client;
import pl.kl.chat_rest.clients.ports.ClientRepository;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Singleton
@RequiredArgsConstructor(onConstructor_ = @Inject)
public class JpaClientRepositoryAdapter implements ClientRepository {

    private final JpaPersistenceClientMapper clientMapper;
    private final JpaClientRepository clientRepository;

    @Override
    public Client save(Client client) {
        final ClientEntity clientEntity = clientMapper.toEntity(client);
        final ClientEntity persistedEntity = clientRepository.save(clientEntity);
        return clientMapper.toDomain(persistedEntity);
    }

    @Override
    public Optional<Client> getByName(String name) {
        return clientRepository.getByName(name)
                .map(clientMapper::toDomain);
    }

    @Override
    public List<Client> getAll() {
        return clientRepository.getAll().stream()
                .map(clientMapper::toDomain)
                .collect(Collectors.toList());
    }
}

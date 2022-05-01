package pl.kl.chat_app.clients.adapters.persistence;

import lombok.Setter;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Singleton
public class JpaClientRepository {

    @Setter
    @PersistenceContext
    private EntityManager entityManager;

    public ClientEntity save(ClientEntity clientEntity) {
        entityManager.persist(clientEntity);
        return clientEntity;
    }

    public Optional<ClientEntity> getByName(String name) {
        final ClientEntity clientEntity = entityManager.createNamedQuery(ClientEntity.GET_BY_NAME, ClientEntity.class)
                .setParameter("name", name)
                .getSingleResult();
        return Optional.ofNullable(clientEntity);
    }

    public List<ClientEntity> getAll() {
        return entityManager.createNamedQuery(ClientEntity.GET_ALL, ClientEntity.class)
                .getResultList();
    }

}

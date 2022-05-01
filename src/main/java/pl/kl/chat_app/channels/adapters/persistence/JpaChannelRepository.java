package pl.kl.chat_app.channels.adapters.persistence;

import lombok.Setter;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Singleton
public class JpaChannelRepository {

    @Setter
    @PersistenceContext
    private EntityManager entityManager;

    public ChannelEntity save(ChannelEntity channelEntity) {
        entityManager.persist(channelEntity);
        return channelEntity;
    }

    public Optional<ChannelEntity> getById(String id) {
        final ChannelEntity channelEntity = entityManager.createNamedQuery(
                        ChannelEntity.GET_BY_NAME, ChannelEntity.class)
                .setParameter("id", id)
                .getSingleResult();
        return Optional.ofNullable(channelEntity);
    }

    public Optional<ChannelEntity> getByName(String name) {
        final ChannelEntity channelEntity = entityManager.createNamedQuery(
                        ChannelEntity.GET_BY_NAME, ChannelEntity.class)
                .setParameter("name", name)
                .getSingleResult();
        return Optional.ofNullable(channelEntity);
    }

    public List<ChannelEntity> getAll() {
        return entityManager.createNamedQuery(ChannelEntity.GET_ALL, ChannelEntity.class)
                .getResultList();
    }

}

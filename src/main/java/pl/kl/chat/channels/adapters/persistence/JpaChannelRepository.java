package pl.kl.chat.channels.adapters.persistence;

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

    public Optional<ChannelEntity> getByName(String name) {
//        TO DLA getSingleResult() RZUCA NoResultException???
//        ZRZUCENIE WYNIKU DO LISTY I PROBA WYCIAGNIECIA PIERWSZEGO ChannelEntity
//        BY RZUCIC ChannelNotFoundException lub ChannelAlreadyExistsException
        return entityManager.createNamedQuery(ChannelEntity.GET_BY_NAME, ChannelEntity.class)
                .setParameter("name", name)
                .getResultList().stream()
                .findFirst();
    }

    public List<ChannelEntity> getAll() {
        return entityManager.createNamedQuery(ChannelEntity.GET_ALL, ChannelEntity.class)
                .getResultList();
    }

    public ChannelEntity update(ChannelEntity channelEntity) {
        return entityManager.merge(channelEntity);
    }
}

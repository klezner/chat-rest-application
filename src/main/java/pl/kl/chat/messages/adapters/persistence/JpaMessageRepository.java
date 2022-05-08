package pl.kl.chat.messages.adapters.persistence;

import lombok.Setter;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Singleton
public class JpaMessageRepository {

    @Setter
    @PersistenceContext
    private EntityManager entityManager;

    public MessageEntity save(MessageEntity messageEntity) {
        entityManager.persist(messageEntity);
        return messageEntity;
    }

    public List<MessageEntity> getAllByClientName(String name) {
        return entityManager.createNamedQuery(MessageEntity.GET_ALL_BY_CLIENT_NAME, MessageEntity.class)
                .setParameter("name", name)
                .getResultList();
    }

    public List<MessageEntity> getAllByChannelName(String name) {
        return entityManager.createNamedQuery(MessageEntity.GET_ALL_BY_CHANNEL_NAME, MessageEntity.class)
                .setParameter("name", name)
                .getResultList();
    }

}

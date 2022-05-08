package pl.kl.chat.messages.adapters.persistence;

import lombok.RequiredArgsConstructor;
import pl.kl.chat.messages.domain.Message;
import pl.kl.chat.messages.ports.MessageRepository;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Singleton
@RequiredArgsConstructor(onConstructor_ = @Inject)
public class JpaMessageRepositoryAdapter implements MessageRepository {

    private final JpaPersistenceMessageMapper messageMapper;
    private final JpaMessageRepository messageRepository;

    @Override
    public Message save(Message message) {
        final MessageEntity messageEntity = messageMapper.toEntity(message);
        final MessageEntity persistedEntity = messageRepository.save(messageEntity);
        return messageMapper.toDomain(persistedEntity);
    }

    @Override
    public List<Message> getAllByClientName(String name) {
        return messageRepository.getAllByClientName(name).stream()
                .map(messageMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Message> getAllByChannelName(String name) {
        return messageRepository.getAllByChannelName(name).stream()
                .map(messageMapper::toDomain)
                .collect(Collectors.toList());
    }

}

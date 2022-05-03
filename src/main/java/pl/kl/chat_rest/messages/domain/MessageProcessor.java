package pl.kl.chat_rest.messages.domain;

import lombok.RequiredArgsConstructor;
import pl.kl.chat_rest.common.IdGenerator;
import pl.kl.chat_rest.common.TimeProvider;
import pl.kl.chat_rest.messages.ports.MessageRepository;
import pl.kl.chat_rest.messages.ports.MessageService;

import java.util.List;

@RequiredArgsConstructor
public class MessageProcessor implements MessageService {

    private final TimeProvider timeProvider;
    private final IdGenerator idGenerator;
    private final MessageRepository messageRepository;

    @Override
    public Message create(String clientSentBy, String channelSentTo, String content) {
        final Message message = Message.builder()
                .id(idGenerator.getNext())
                .clientSentBy(clientSentBy)
                .channelSentTo(channelSentTo)
                .content(content)
                .timestamp(timeProvider.getTimestamp())
                .build();
        return messageRepository.save(message);
    }

    @Override
    public List<Message> getAllByClientName(String name) {
        return messageRepository.getAllByClientName(name);
    }

    @Override
    public List<Message> getAllByChannelName(String name) {
        return messageRepository.getAllByChannelName(name);
    }

}

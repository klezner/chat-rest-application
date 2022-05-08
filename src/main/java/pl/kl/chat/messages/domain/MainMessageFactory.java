package pl.kl.chat.messages.domain;

import pl.kl.chat.common.IdGenerator;
import pl.kl.chat.common.TimeProvider;
import pl.kl.chat.messages.ports.MessageFactory;
import pl.kl.chat.messages.ports.MessageRepository;
import pl.kl.chat.messages.ports.MessageService;

public class MainMessageFactory implements MessageFactory {

    @Override
    public MessageService messageService(TimeProvider timeProvider, IdGenerator idGenerator, MessageRepository messageRepository) {
        return new MessageProcessor(timeProvider, idGenerator, messageRepository);
    }

}

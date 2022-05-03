package pl.kl.chat_rest.messages.domain;

import pl.kl.chat_rest.common.IdGenerator;
import pl.kl.chat_rest.common.TimeProvider;
import pl.kl.chat_rest.messages.ports.MessageFactory;
import pl.kl.chat_rest.messages.ports.MessageRepository;
import pl.kl.chat_rest.messages.ports.MessageService;

public class MainMessageFactory implements MessageFactory {

    @Override
    public MessageService messageService(TimeProvider timeProvider, IdGenerator idGenerator, MessageRepository messageRepository) {
        return new MessageProcessor(timeProvider, idGenerator, messageRepository);
    }

}

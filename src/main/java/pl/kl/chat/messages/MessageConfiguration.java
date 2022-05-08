package pl.kl.chat.messages;

import pl.kl.chat.common.IdGenerator;
import pl.kl.chat.common.TimeProvider;
import pl.kl.chat.messages.domain.MainMessageFactory;
import pl.kl.chat.messages.ports.MessageFactory;
import pl.kl.chat.messages.ports.MessageRepository;
import pl.kl.chat.messages.ports.MessageService;

import javax.enterprise.inject.Produces;
import javax.inject.Singleton;

@Singleton
class MessageConfiguration {

    private static final MessageFactory MESSAGE_FACTORY = new MainMessageFactory();

    @Singleton
    @Produces
    public MessageService messageService(TimeProvider timeProvider, IdGenerator idGenerator, MessageRepository messageRepository) {
        return MESSAGE_FACTORY.messageService(timeProvider, idGenerator, messageRepository);
    }

}

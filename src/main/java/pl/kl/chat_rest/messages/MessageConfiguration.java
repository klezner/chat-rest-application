package pl.kl.chat_rest.messages;

import pl.kl.chat_rest.common.IdGenerator;
import pl.kl.chat_rest.common.TimeProvider;
import pl.kl.chat_rest.messages.domain.MainMessageFactory;
import pl.kl.chat_rest.messages.ports.MessageFactory;
import pl.kl.chat_rest.messages.ports.MessageRepository;
import pl.kl.chat_rest.messages.ports.MessageService;

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

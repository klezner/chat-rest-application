package pl.kl.chat_rest.messages.ports;

import pl.kl.chat_rest.common.IdGenerator;
import pl.kl.chat_rest.common.TimeProvider;

public interface MessageFactory {

    MessageService messageService(TimeProvider timeProvider, IdGenerator idGenerator, MessageRepository messageRepository);

}

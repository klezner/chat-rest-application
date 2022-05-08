package pl.kl.chat.messages.ports;

import pl.kl.chat.common.IdGenerator;
import pl.kl.chat.common.TimeProvider;

public interface MessageFactory {

    MessageService messageService(TimeProvider timeProvider, IdGenerator idGenerator, MessageRepository messageRepository);

}

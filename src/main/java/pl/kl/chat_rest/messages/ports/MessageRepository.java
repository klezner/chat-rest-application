package pl.kl.chat_rest.messages.ports;

import pl.kl.chat_rest.messages.domain.Message;

import java.util.List;

public interface MessageRepository {

    Message save(Message message);

    List<Message> getAllByClientName(String name);

    List<Message> getAllByChannelName(String name);

}

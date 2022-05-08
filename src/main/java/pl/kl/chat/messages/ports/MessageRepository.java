package pl.kl.chat.messages.ports;

import pl.kl.chat.messages.domain.Message;

import java.util.List;

public interface MessageRepository {

    Message save(Message message);

    List<Message> getAllByClientName(String name);

    List<Message> getAllByChannelName(String name);

}

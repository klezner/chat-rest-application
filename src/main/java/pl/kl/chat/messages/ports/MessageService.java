package pl.kl.chat.messages.ports;

import pl.kl.chat.messages.domain.Message;

import java.util.List;

public interface MessageService {

    Message create(String clientSentBy, String channelSentTo, String content);

    List<Message> getAllByClientName(String name);

    List<Message> getAllByChannelName(String name);

}

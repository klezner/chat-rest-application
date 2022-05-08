package pl.kl.chat.handlers.rest.messages;

import java.util.List;

public interface MessageClient {

    List<MessageDto> getMessagesByClientName(String name);

    List<MessageDto> getMessagesByChannelName(String name);

}

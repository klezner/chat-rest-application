package pl.kl.chat_app.channels.domain;

import lombok.Builder;
import lombok.Value;
import pl.kl.chat_app.clients.domain.Client;
import pl.kl.chat_app.messages.Message;

import java.util.List;
import java.util.Set;

@Builder
@Value
public class Channel {

    String id;
    String name;
    Set<Client> clients;
    List<Message> archivedMessages;

}

package pl.kl.chat_rest.channels.domain;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class Channel {

    String id;
    String name;
//    Set<Client> clients;
//    List<Message> archivedMessages;

}

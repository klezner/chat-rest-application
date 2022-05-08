package pl.kl.chat.channels.domain;

import lombok.Builder;
import lombok.Value;

import java.util.Set;

@Builder
@Value
public class Channel {

    String id;
    String name;
    Set<String> clients;
//    Set<Client> clients;
//    List<Message> archivedMessages;

}

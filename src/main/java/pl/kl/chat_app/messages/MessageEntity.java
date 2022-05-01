package pl.kl.chat_app.messages;

import pl.kl.chat_app.channels.domain.Channel;
import pl.kl.chat_app.clients.domain.Client;

import java.time.Instant;

//@EqualsAndHashCode(of = "id")
//@Data
//@Entity(name = "Message")
public class MessageEntity {

    //    @Id
    String id;
    Instant timestamp;
    Client sendBy;
    String content;
    Channel sentTo;

}

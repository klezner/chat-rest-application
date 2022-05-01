package pl.kl.chat_app.messages;

import lombok.Value;
import pl.kl.chat_app.channels.domain.Channel;
import pl.kl.chat_app.clients.domain.Client;

import java.time.Instant;

@Value
public class Message {

    String id;
    Instant timestamp;
    Client sendBy;
    String content;
    Channel sentTo;

}

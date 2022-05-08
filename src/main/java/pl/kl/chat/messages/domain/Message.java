package pl.kl.chat.messages.domain;

import lombok.Builder;
import lombok.Value;

import java.time.Instant;

@Builder
@Value
public class Message {

    String id;
    String clientSentBy;
    String channelSentTo;
    String content;
    Instant timestamp;

}

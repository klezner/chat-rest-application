package pl.kl.chat_rest.messages.adapters.rest;

import lombok.Data;

import java.time.Instant;

@Data
public class MessageDto {

    String clientSentBy;
    String channelSentTo;
    String content;
    Instant timestamp;

}

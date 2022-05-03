package pl.kl.chat_rest.messages.adapters.messaging;

import lombok.ToString;

import java.io.Serializable;

@ToString
public class MessageTransferred implements Serializable {

    private static final long serialVersionUID = 1L;

    String clientSentBy;
    String channelSentTo;
    String content;

}

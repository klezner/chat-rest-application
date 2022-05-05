package pl.kl.chat_client.messages;

import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

@ToString // TODO: usunąć
@Getter
public class MessageDto implements Serializable {

    String clientSentBy;
    String channelSentTo;
    String content;
    String timestamp;

}

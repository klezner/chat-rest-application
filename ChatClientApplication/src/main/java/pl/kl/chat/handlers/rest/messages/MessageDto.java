package pl.kl.chat.handlers.rest.messages;

import lombok.Getter;
import pl.kl.chat.common.ResponseDto;

import java.io.Serializable;

@Getter
public class MessageDto extends ResponseDto implements Serializable {

    String clientSentBy;
    String channelSentTo;
    String content;
    String timestamp;

}

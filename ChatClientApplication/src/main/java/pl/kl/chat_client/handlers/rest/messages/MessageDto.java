package pl.kl.chat_client.handlers.rest.messages;

import lombok.Getter;
import lombok.ToString;
import pl.kl.chat_client.common.ResponseDto;

import java.io.Serializable;

@ToString // TODO: usunąć
@Getter
public class MessageDto extends ResponseDto implements Serializable {

    String clientSentBy;
    String channelSentTo;
    String content;
    String timestamp;

}

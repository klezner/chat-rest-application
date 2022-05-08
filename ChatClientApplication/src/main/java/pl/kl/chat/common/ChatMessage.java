package pl.kl.chat.common;

import lombok.Builder;
import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Builder
public class ChatMessage implements Serializable {

    @Serial
    private static final long serialVersionUID = 7526472295622776147L;

    String clientSentBy;
    String channelSentTo;
    String content;

}

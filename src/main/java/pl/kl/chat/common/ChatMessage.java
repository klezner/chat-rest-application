package pl.kl.chat.common;

import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;

@Getter
public class ChatMessage implements Serializable {

    @Serial
    private static final long serialVersionUID = 7526472295622776147L;

    private String clientSentBy;
    private String channelSentTo;
    private ChatMessageType type;
    private String content;
    private String fileName;
    private byte[] fileContentBytes;

}

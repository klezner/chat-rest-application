package pl.kl.chat_client.jmsmessageservice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

@ToString
@Getter
@AllArgsConstructor
public class MessageTransferred implements Serializable {

    @Serial
    private static final long serialVersionUID = 7526472295622776147L;

    String clientSentBy;
    String channelSentTo;
    String content;

}

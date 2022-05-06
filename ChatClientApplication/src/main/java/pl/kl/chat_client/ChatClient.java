package pl.kl.chat_client;

import lombok.Getter;
import lombok.Setter;

public class ChatClient {

    @Setter
    @Getter
    private String name;
    @Setter
    @Getter
    private String activeChannel;

}

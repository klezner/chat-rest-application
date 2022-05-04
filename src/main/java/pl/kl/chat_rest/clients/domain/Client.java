package pl.kl.chat_rest.clients.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
public class Client {

    private final String id;
    private final String name;
    @Setter
    private String activeChannel;
//    Channel activeChannel;

}

package pl.kl.chat_rest.clients.domain;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class Client {

    String id;
    String name;
    String activeChannel;
//    Channel activeChannel;

}

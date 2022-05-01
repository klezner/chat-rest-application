package pl.kl.chat_app.clients.domain;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class Client {

    String id;
    String name;
//    Channel activeChannel;

}

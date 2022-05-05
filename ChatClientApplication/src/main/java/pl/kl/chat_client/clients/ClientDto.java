package pl.kl.chat_client.clients;

import lombok.Getter;
import lombok.ToString;

@ToString // TODO: usunąć
@Getter
public class ClientDto {

    String id;
    String name;
    String activeChannel;

}

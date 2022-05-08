package pl.kl.chat_client.handlers.rest.clients;

import lombok.Getter;
import pl.kl.chat_client.common.ResponseDto;

@Getter
public class ClientDto extends ResponseDto {

    String id;
    String name;
    String activeChannel;

}

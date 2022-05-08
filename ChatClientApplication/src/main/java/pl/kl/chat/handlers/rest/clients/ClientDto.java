package pl.kl.chat.handlers.rest.clients;

import lombok.Getter;
import pl.kl.chat.common.ResponseDto;

@Getter
public class ClientDto extends ResponseDto {

    String id;
    String name;
    String activeChannel;

}

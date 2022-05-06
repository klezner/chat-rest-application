package pl.kl.chat_client.handlers.rest.clients;

import lombok.Getter;
import lombok.ToString;
import pl.kl.chat_client.common.ResponseDto;

@ToString // TODO: usunąć
@Getter
public class ClientDto extends ResponseDto {

    String id;
    String name;
    String activeChannel;

}

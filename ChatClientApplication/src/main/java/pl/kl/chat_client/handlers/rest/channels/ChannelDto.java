package pl.kl.chat_client.handlers.rest.channels;

import lombok.Getter;
import lombok.ToString;
import pl.kl.chat_client.common.ResponseDto;

import java.util.Set;

@ToString // TODO: usunąć
@Getter
public class ChannelDto extends ResponseDto {

    String id;
    String name;
    Set<String> clients;

}

package pl.kl.chat.handlers.rest.channels;

import lombok.Getter;
import pl.kl.chat.common.ResponseDto;

import java.util.Set;

@Getter
public class ChannelDto extends ResponseDto {

    String id;
    String name;
    Set<String> clients;

}

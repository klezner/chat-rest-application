package pl.kl.chat_client.channels;

import lombok.Getter;
import lombok.ToString;

import java.util.Set;

@ToString // TODO: usunąć
@Getter
public class ChannelDto {

    String id;
    String name;
    Set<String> clients;

}

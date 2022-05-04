package pl.kl.chat_rest.channels.ports;

import pl.kl.chat_rest.channels.domain.Channel;

import java.util.List;
import java.util.Optional;

public interface ChannelRepository {

    Channel save(Channel channel);

    Optional<Channel> getByName(String name);

    List<Channel> getAll();

    Channel update(Channel channel);

}

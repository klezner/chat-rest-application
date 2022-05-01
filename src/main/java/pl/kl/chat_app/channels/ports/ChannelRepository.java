package pl.kl.chat_app.channels.ports;

import pl.kl.chat_app.channels.domain.Channel;

import java.util.List;
import java.util.Optional;

public interface ChannelRepository {

    Channel save(Channel channel);

    Optional<Channel> getByName(String name);

    Optional<Channel> getById(String id);

    List<Channel> getAll();
}

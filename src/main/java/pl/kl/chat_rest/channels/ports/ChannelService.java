package pl.kl.chat_rest.channels.ports;

import pl.kl.chat_rest.channels.domain.Channel;

import java.util.List;

public interface ChannelService {

    Channel create(String name);

    Channel getByName(String name);

    List<Channel> getAll();

    Channel addClient(String name, String clientName);

}

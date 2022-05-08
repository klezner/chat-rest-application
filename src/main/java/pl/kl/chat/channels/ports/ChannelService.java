package pl.kl.chat.channels.ports;

import pl.kl.chat.channels.domain.Channel;

import java.util.List;

public interface ChannelService {

    Channel create(String name);

    Channel getByName(String name);

    List<Channel> getAll();

    Channel addClient(String name, String clientName);

}

package pl.kl.chat_app.channels.ports;

import pl.kl.chat_app.channels.domain.Channel;

import java.util.List;

public interface ChannelService {

    Channel createChannel(String name);

    void joinChannel(String name);

    Channel getChannelByName(String name);

    Channel getChannelById(String id);

    List<Channel> getAllChannels();

}

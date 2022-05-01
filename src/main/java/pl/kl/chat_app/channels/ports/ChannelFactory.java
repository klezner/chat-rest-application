package pl.kl.chat_app.channels.ports;

import pl.kl.chat_app.common.IdGenerator;

public interface ChannelFactory {

    ChannelService channelService(IdGenerator idGenerator, ChannelRepository channelRepository);

}

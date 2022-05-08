package pl.kl.chat.channels.ports;

import pl.kl.chat.common.IdGenerator;

public interface ChannelFactory {

    ChannelService channelService(IdGenerator idGenerator, ChannelRepository channelRepository);

}

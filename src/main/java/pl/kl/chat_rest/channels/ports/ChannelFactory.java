package pl.kl.chat_rest.channels.ports;

import pl.kl.chat_rest.common.IdGenerator;

public interface ChannelFactory {

    ChannelService channelService(IdGenerator idGenerator, ChannelRepository channelRepository);

}

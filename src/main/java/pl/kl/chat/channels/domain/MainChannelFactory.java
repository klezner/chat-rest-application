package pl.kl.chat.channels.domain;

import pl.kl.chat.channels.ports.ChannelFactory;
import pl.kl.chat.channels.ports.ChannelRepository;
import pl.kl.chat.channels.ports.ChannelService;
import pl.kl.chat.common.IdGenerator;

public class MainChannelFactory implements ChannelFactory {

    @Override
    public ChannelService channelService(IdGenerator idGenerator, ChannelRepository channelRepository) {
        return new ChannelProcessor(idGenerator, channelRepository);
    }

}

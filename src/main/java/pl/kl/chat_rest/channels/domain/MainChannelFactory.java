package pl.kl.chat_rest.channels.domain;

import pl.kl.chat_rest.channels.ports.ChannelFactory;
import pl.kl.chat_rest.channels.ports.ChannelRepository;
import pl.kl.chat_rest.channels.ports.ChannelService;
import pl.kl.chat_rest.common.IdGenerator;

public class MainChannelFactory implements ChannelFactory {

    @Override
    public ChannelService channelService(IdGenerator idGenerator, ChannelRepository channelRepository) {
        return new ChannelProcessor(idGenerator, channelRepository);
    }

}

package pl.kl.chat_app.channels.domain;

import pl.kl.chat_app.channels.ports.ChannelFactory;
import pl.kl.chat_app.channels.ports.ChannelRepository;
import pl.kl.chat_app.channels.ports.ChannelService;
import pl.kl.chat_app.common.IdGenerator;

public class MainChannelFactory implements ChannelFactory {

    @Override
    public ChannelService channelService(IdGenerator idGenerator, ChannelRepository channelRepository) {
        return new ChannelProcessor(idGenerator, channelRepository);
    }

}

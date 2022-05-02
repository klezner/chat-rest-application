package pl.kl.chat_rest.channels;

import pl.kl.chat_rest.channels.domain.MainChannelFactory;
import pl.kl.chat_rest.channels.ports.ChannelFactory;
import pl.kl.chat_rest.channels.ports.ChannelRepository;
import pl.kl.chat_rest.channels.ports.ChannelService;
import pl.kl.chat_rest.common.IdGenerator;

import javax.enterprise.inject.Produces;
import javax.inject.Singleton;

@Singleton
public class ChannelConfiguration {

    private static final ChannelFactory CHANNEL_FACTORY = new MainChannelFactory();

    @Singleton
    @Produces
    public ChannelService channelService(IdGenerator idGenerator, ChannelRepository channelRepository) {
        return CHANNEL_FACTORY.channelService(idGenerator, channelRepository);
    }

}

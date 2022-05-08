package pl.kl.chat.channels;

import pl.kl.chat.channels.domain.MainChannelFactory;
import pl.kl.chat.channels.ports.ChannelFactory;
import pl.kl.chat.channels.ports.ChannelRepository;
import pl.kl.chat.channels.ports.ChannelService;
import pl.kl.chat.common.IdGenerator;

import javax.enterprise.inject.Produces;
import javax.inject.Singleton;

@Singleton
class ChannelConfiguration {

    private static final ChannelFactory CHANNEL_FACTORY = new MainChannelFactory();

    @Singleton
    @Produces
    public ChannelService channelService(IdGenerator idGenerator, ChannelRepository channelRepository) {
        return CHANNEL_FACTORY.channelService(idGenerator, channelRepository);
    }

}

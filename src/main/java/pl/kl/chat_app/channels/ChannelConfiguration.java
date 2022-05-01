package pl.kl.chat_app.channels;

import pl.kl.chat_app.channels.domain.MainChannelFactory;
import pl.kl.chat_app.channels.ports.ChannelFactory;
import pl.kl.chat_app.channels.ports.ChannelRepository;
import pl.kl.chat_app.channels.ports.ChannelService;
import pl.kl.chat_app.common.IdGenerator;

import javax.enterprise.inject.Produces;
import javax.inject.Singleton;

@Singleton
public class ChannelConfiguration {

    private static final ChannelFactory CHANNEL_FACTORY = new MainChannelFactory();

//    @Singleton
//    @Produces
//    public JpaPersistenceChannelMapper jpaPersistenceChannelMapper() {
//        return Mappers.getMapper(JpaPersistenceChannelMapper.class);
//    }

    @Singleton
    @Produces
    public ChannelService channelService(IdGenerator idGenerator, ChannelRepository channelRepository) {
        return CHANNEL_FACTORY.channelService(idGenerator, channelRepository);
    }

//    @Singleton
//    @Produces
//    public RestChannelMapper restChannelMapper() {
//        return Mappers.getMapper(RestChannelMapper.class);
//    }

}

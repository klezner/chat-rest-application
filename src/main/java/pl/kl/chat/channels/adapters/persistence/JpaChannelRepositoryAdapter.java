package pl.kl.chat.channels.adapters.persistence;

import lombok.RequiredArgsConstructor;
import pl.kl.chat.channels.domain.Channel;
import pl.kl.chat.channels.ports.ChannelRepository;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Singleton
@RequiredArgsConstructor(onConstructor_ = @Inject)
public class JpaChannelRepositoryAdapter implements ChannelRepository {

    private final JpaPersistenceChannelMapper channelMapper;
    private final JpaChannelRepository channelRepository;

    @Override
    public Channel save(Channel channel) {
        final ChannelEntity channelEntity = channelMapper.toEntity(channel);
        final ChannelEntity persistedEntity = channelRepository.save(channelEntity);
        return channelMapper.toDomain(persistedEntity);
    }

    @Override
    public Optional<Channel> getByName(String name) {
        return channelRepository.getByName(name)
                .map(channelMapper::toDomain);
    }

    @Override
    public List<Channel> getAll() {
        return channelRepository.getAll().stream()
                .map(channelMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Channel update(Channel channel) {
        final ChannelEntity channelEntity = channelMapper.toEntity(channel);
        final ChannelEntity persistedEntity = channelRepository.update(channelEntity);
        return channelMapper.toDomain(persistedEntity);
    }

}

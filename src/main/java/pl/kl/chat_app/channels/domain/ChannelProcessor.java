package pl.kl.chat_app.channels.domain;

import lombok.RequiredArgsConstructor;
import pl.kl.chat_app.channels.ports.ChannelRepository;
import pl.kl.chat_app.channels.ports.ChannelService;
import pl.kl.chat_app.common.IdGenerator;

import java.util.HashSet;
import java.util.List;

@RequiredArgsConstructor
class ChannelProcessor implements ChannelService {

    private final IdGenerator idGenerator;
    private final ChannelRepository channelRepository;

    @Override
    public Channel createChannel(String name) {
        final Channel channel = Channel.builder()
                .id(idGenerator.getNext())
                .name(name)
                .clients(new HashSet<>()) // TODO: utworzyc
                .build();
        return channelRepository.save(channel);
    }

    @Override
    public void joinChannel(String name) {
        // TODO: dodac ustawianie userowi kanalu
    }

    @Override
    public Channel getChannelByName(String name) {
        return channelRepository.getByName(name)
                .orElseThrow(ChannelNotFoundException::new);
    }

    @Override
    public Channel getChannelById(String id) {
        return channelRepository.getById(id)
                .orElseThrow(ChannelNotFoundException::new);
    }

    @Override
    public List<Channel> getAllChannels() {
        return channelRepository.getAll();
    }

}

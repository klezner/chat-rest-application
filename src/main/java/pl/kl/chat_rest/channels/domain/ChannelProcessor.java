package pl.kl.chat_rest.channels.domain;

import lombok.RequiredArgsConstructor;
import pl.kl.chat_rest.channels.ports.ChannelRepository;
import pl.kl.chat_rest.channels.ports.ChannelService;
import pl.kl.chat_rest.common.IdGenerator;

import java.util.HashSet;
import java.util.List;

@RequiredArgsConstructor
class ChannelProcessor implements ChannelService {

    private final IdGenerator idGenerator;
    private final ChannelRepository channelRepository;

    @Override
    public Channel create(String name) {
        ifChannelExists(name);
        final Channel channel = Channel.builder()
                .id(idGenerator.getNext())
                .name(name)
                .clients(new HashSet<>())
                .build();
        return channelRepository.save(channel);
    }

    private void ifChannelExists(String name) {
        if (channelRepository.getByName(name).isPresent()) {
            throw new ChannelAlreadyExistsException();
        }
    }

    @Override
    public Channel getByName(String name) {
        return channelRepository.getByName(name)
                .orElseThrow(ChannelNotFoundException::new);
    }

    @Override
    public List<Channel> getAll() {
        return channelRepository.getAll();
    }

    @Override
    public Channel addClient(String name, String clientName) {
        final Channel channel = getByName(name);
        channel.getClients().add(clientName);
        return channelRepository.update(channel);
    }


}

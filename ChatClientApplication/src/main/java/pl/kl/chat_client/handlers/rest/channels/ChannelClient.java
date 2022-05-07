package pl.kl.chat_client.handlers.rest.channels;

import pl.kl.chat_client.common.ResponseDto;

import java.util.List;

public interface ChannelClient {

    ResponseDto createChannel(String name);

    ResponseDto getChannelByName(String name);

    List<ChannelDto> getAllChannels();

    ResponseDto setChannelClient(String name, String clientName);

}

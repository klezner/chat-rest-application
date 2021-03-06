
package pl.kl.chat.channels.adapters.rest;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import pl.kl.chat.channels.domain.Channel;

import java.util.List;

@Mapper
public interface RestChannelMapper {

//    Channel toDomain(ChannelDto dto);

    ChannelDto toDto(Channel channel);

    @IterableMapping(elementTargetType = ChannelDto.class)
    List<ChannelDto> toDto(List<Channel> channels);

}

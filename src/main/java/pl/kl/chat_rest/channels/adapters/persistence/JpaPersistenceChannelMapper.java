package pl.kl.chat_rest.channels.adapters.persistence;

import org.mapstruct.Mapper;
import pl.kl.chat_rest.channels.domain.Channel;

@Mapper
public interface JpaPersistenceChannelMapper {

    ChannelEntity toEntity(Channel channel);

    Channel toDomain(ChannelEntity entity);

}

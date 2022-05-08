package pl.kl.chat.channels.adapters.persistence;

import org.mapstruct.Mapper;
import pl.kl.chat.channels.domain.Channel;

@Mapper
public interface JpaPersistenceChannelMapper {

    ChannelEntity toEntity(Channel channel);

    Channel toDomain(ChannelEntity entity);

}

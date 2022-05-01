package pl.kl.chat_app.channels.adapters.persistence;

import org.mapstruct.Mapper;
import pl.kl.chat_app.channels.domain.Channel;

@Mapper(componentModel = "cdi")
public interface JpaPersistenceChannelMapper {

    ChannelEntity toEntity(Channel channel);

    Channel toDomain(ChannelEntity entity);

}

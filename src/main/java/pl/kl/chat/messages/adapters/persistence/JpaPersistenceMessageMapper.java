package pl.kl.chat.messages.adapters.persistence;

import org.mapstruct.Mapper;
import pl.kl.chat.messages.domain.Message;

@Mapper
public interface JpaPersistenceMessageMapper {

    MessageEntity toEntity(Message message);

    Message toDomain(MessageEntity entity);

}

package pl.kl.chat_rest.messages.adapters.persistence;

import org.mapstruct.Mapper;
import pl.kl.chat_rest.messages.domain.Message;

@Mapper
public interface JpaPersistenceMessageMapper {

    MessageEntity toEntity(Message message);

    Message toDomain(MessageEntity entity);

}

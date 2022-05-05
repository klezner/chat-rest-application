package pl.kl.chat_rest.messages.adapters.messaging;

import org.mapstruct.Mapper;
import pl.kl.chat_rest.messages.domain.Message;

@Mapper
public interface TransferredMessageMapper {

    Message toDomain(MessageTransferred messageTransferred);

}

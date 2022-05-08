package pl.kl.chat.messages.adapters.messaging;

import org.mapstruct.Mapper;
import pl.kl.chat.common.ChatMessage;
import pl.kl.chat.messages.domain.Message;

@Mapper
public interface TransferredMessageMapper {

    Message toDomain(ChatMessage chatMessage);

}

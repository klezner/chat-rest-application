package pl.kl.chat.messages.adapters.rest;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import pl.kl.chat.messages.domain.Message;

import java.util.List;

@Mapper
public interface RestMessageMapper {

    @IterableMapping(elementTargetType = MessageDto.class)
    List<MessageDto> toDto(List<Message> messages);

}

package pl.kl.chat_client.common;

import lombok.Getter;
import lombok.ToString;

@ToString // TODO: usunąć
@Getter
public class ExceptionDto extends ResponseDto {

    String timestamp;
    String description;

}

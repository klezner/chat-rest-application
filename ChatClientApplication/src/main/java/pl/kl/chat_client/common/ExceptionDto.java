package pl.kl.chat_client.common;

import lombok.Getter;

@Getter
public class ExceptionDto extends ResponseDto {

    String timestamp;
    String description;

}

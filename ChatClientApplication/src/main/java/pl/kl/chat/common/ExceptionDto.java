package pl.kl.chat.common;

import lombok.Getter;

@Getter
public class ExceptionDto extends ResponseDto {

    String timestamp;
    String description;

}

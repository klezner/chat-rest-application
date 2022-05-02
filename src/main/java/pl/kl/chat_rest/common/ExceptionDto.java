package pl.kl.chat_rest.common;

import lombok.Value;

import java.time.Instant;

@Value
public class ExceptionDto {

    Instant timestamp = Instant.now();
    String description;

}

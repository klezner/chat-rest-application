package pl.kl.chat_rest.common;

import java.time.Instant;

public interface TimeProvider {

    Instant getTimestamp();

}

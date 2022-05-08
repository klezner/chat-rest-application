package pl.kl.chat.common;

import java.time.Instant;

public interface TimeProvider {

    Instant getTimestamp();

}

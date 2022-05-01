package pl.kl.chat_app.common;

import java.time.Instant;

public interface TimeProvider {

    Instant getTimestamp();

}

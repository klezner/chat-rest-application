package pl.kl.chat_rest.common;

import java.time.Instant;

public class SystemTimeProvider implements TimeProvider {

    @Override
    public Instant getTimestamp() {
        return Instant.now();
    }

}

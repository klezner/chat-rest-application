package pl.kl.chat.common;

import javax.inject.Singleton;
import java.util.UUID;

@Singleton
public class UuidGenerator implements IdGenerator {

    @Override
    public String getNext() {
        return UUID.randomUUID().toString();
    }

}

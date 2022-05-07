package pl.kl.chat_client.messagecache;

import java.util.Optional;

public interface ChannelMessageCache {

    Optional<MessageCache> getCachedMessagesFromChannel(String channel);

    void cacheMessageFromChannel(String client, String channel, String message);

}

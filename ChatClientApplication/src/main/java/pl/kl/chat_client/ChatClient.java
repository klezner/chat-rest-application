package pl.kl.chat_client;

import lombok.Getter;
import lombok.Setter;
import pl.kl.chat_client.messagecache.ChannelMessageCache;
import pl.kl.chat_client.messagecache.ChannelMessageCacheMap;
import pl.kl.chat_client.messagecache.SynchronizedChannelMessageCacheMapProxy;

public class ChatClient {

    @Setter
    @Getter
    private String name;
    @Setter
    @Getter
    private String activeChannel;
    @Getter
    private final ChannelMessageCache messageCache;

    public ChatClient() {
        this.messageCache = new SynchronizedChannelMessageCacheMapProxy(new ChannelMessageCacheMap());
    }

}

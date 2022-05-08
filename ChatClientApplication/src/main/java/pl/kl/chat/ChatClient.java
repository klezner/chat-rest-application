package pl.kl.chat;

import lombok.Getter;
import lombok.Setter;
import pl.kl.chat.messagecache.ChannelMessageCache;
import pl.kl.chat.messagecache.ChannelMessageCacheMap;
import pl.kl.chat.messagecache.SynchronizedChannelMessageCacheMapProxy;

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

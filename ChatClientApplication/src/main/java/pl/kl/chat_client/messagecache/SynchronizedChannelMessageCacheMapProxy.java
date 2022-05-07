package pl.kl.chat_client.messagecache;

import java.util.Optional;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class SynchronizedChannelMessageCacheMapProxy implements ChannelMessageCache {

    private final ChannelMessageCache channelMessageCache;
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    public SynchronizedChannelMessageCacheMapProxy(ChannelMessageCache channelMessageCache) {
        this.channelMessageCache = channelMessageCache;
    }

    @Override
    public Optional<MessageCache> getCachedMessagesFromChannel(String channel) {
        lock.readLock().lock();
        final Optional<MessageCache> cachedMessagesFromChannel = channelMessageCache.getCachedMessagesFromChannel(channel);
        lock.readLock().unlock();
        return cachedMessagesFromChannel;
    }

    @Override
    public void cacheMessageFromChannel(String client, String channel, String message) {
        lock.writeLock().lock();
        channelMessageCache.cacheMessageFromChannel(client, channel, message);
        lock.writeLock().unlock();
    }

}

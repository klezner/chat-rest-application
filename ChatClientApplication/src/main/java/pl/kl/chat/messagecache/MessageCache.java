package pl.kl.chat.messagecache;

public interface MessageCache {

    void addMessageToCache(String message);

    String getLastCachedMessage();

}

package pl.kl.chat_client.messagecache;

public interface MessageCache {

    void addMessageToCache(String message);

    String getLastCachedMessage();

}

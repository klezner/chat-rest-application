package pl.kl.chat_client;

import java.net.URI;

public class UriHelper {

    private static final String CLIENTS_RESOURCE_PATH = "http://localhost:8080/chat-rest-application-1.0-SNAPSHOT/api/clients";
    private static final String CHANNELS_RESOURCE_PATH = "http://localhost:8080/chat-rest-application-1.0-SNAPSHOT/api/channels";
    private static final String MESSAGES_RESOURCE_PATH = "http://localhost:8080/chat-rest-application-1.0-SNAPSHOT/api/messages";

    public static URI prepareClientByNameResourcePath(String name) {
        return URI.create(CLIENTS_RESOURCE_PATH + "/" + name);
    }

    public static URI prepareClientsResourcePath() {
        return URI.create(CLIENTS_RESOURCE_PATH);
    }

}

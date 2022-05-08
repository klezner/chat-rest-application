package pl.kl.chat.handlers.rest;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.internal.ResteasyClientBuilderImpl;
import pl.kl.chat.handlers.rest.channels.ChannelClient;
import pl.kl.chat.handlers.rest.channels.ChannelRestClient;
import pl.kl.chat.handlers.rest.clients.ClientClient;
import pl.kl.chat.handlers.rest.clients.ClientRestClient;
import pl.kl.chat.handlers.rest.messages.MessageClient;
import pl.kl.chat.handlers.rest.messages.MessageRestClient;

public class MainRestClientFactory implements RestClientFactory {

    @Override
    public ResteasyClient createResteasyClient() {
        return new ResteasyClientBuilderImpl().build();
    }

    @Override
    public ChannelClient createChannelClient(ResteasyClient resteasyClient) {
        return new ChannelRestClient(resteasyClient);
    }

    @Override
    public ClientClient createClientClient(ResteasyClient resteasyClient) {
        return new ClientRestClient(resteasyClient);
    }

    @Override
    public MessageClient createMessageClient(ResteasyClient resteasyClient) {
        return new MessageRestClient(resteasyClient);
    }

}

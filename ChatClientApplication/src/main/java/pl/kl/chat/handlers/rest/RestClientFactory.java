package pl.kl.chat.handlers.rest;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import pl.kl.chat.handlers.rest.channels.ChannelClient;
import pl.kl.chat.handlers.rest.clients.ClientClient;
import pl.kl.chat.handlers.rest.messages.MessageClient;

public interface RestClientFactory {

    ResteasyClient createResteasyClient();

    ChannelClient createChannelClient(ResteasyClient resteasyClient);

    ClientClient createClientClient(ResteasyClient resteasyClient);

    MessageClient createMessageClient(ResteasyClient resteasyClient);

}

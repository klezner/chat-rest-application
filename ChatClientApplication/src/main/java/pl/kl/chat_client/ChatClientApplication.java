package pl.kl.chat_client;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.internal.ResteasyClientBuilderImpl;
import pl.kl.chat_client.messages.MessageRestClient;

public class ChatClientApplication {

    public static void main(String[] args) {

        final ResteasyClient resteasyClient = new ResteasyClientBuilderImpl().build();

        //--------------------//
//        final ClientRestClient clientRestClient = new ClientRestClient(resteasyClient);
//
//        final String newClient = clientRestClient.createClient("maro");
//        System.out.println(newClient);
//
//        final String getClient = clientRestClient.getClientByName("dupa");
//        System.out.println(getClient);
//
//        clientRestClient.getAllClients().forEach(System.out::println);
//
//        final String setClientActiveChannel = clientRestClient.setClientActiveChannel("abdul", "general");
//        System.out.println(setClientActiveChannel);
        //--------------------//
        System.out.println("//--------------------//");
        //--------------------//
//        final ChannelRestClient channelRestClient = new ChannelRestClient(resteasyClient);
//
//        final String newChannel = channelRestClient.createChannel("new");
//        System.out.println(newChannel);
//
//        final String getChannel = channelRestClient.getChannelByName("new");
//        System.out.println(getChannel);
//
//        channelRestClient.getAllChannels().forEach(System.out::println);
//
//        final String setChannelClient = channelRestClient.setChannelClient("general", "maro");
//        System.out.println(setChannelClient);
        //--------------------//
        System.out.println("//--------------------//");
        //--------------------//
        final MessageRestClient messageRestClient = new MessageRestClient(resteasyClient);

        System.out.println("getMessagesByClientName");
        messageRestClient.getMessagesByClientName("miro").forEach(System.out::println);
        System.out.println("getMessagesByChannelName");
        messageRestClient.getMessagesByChannelName("general").forEach(System.out::println);
    }

}

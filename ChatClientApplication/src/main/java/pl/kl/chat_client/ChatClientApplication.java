package pl.kl.chat_client;

import pl.kl.chat_client.ui.UserInterface;

import java.io.IOException;

public class ChatClientApplication {

    public static void main(String[] args) throws IOException {

        final ChatClient chatClient = new ChatClient();

        final UserInterface userInterface = new UserInterface(chatClient);

        userInterface.loginUser();

        userInterface.handleUser();

//        final ResteasyClient resteasyClient = new ResteasyClientBuilderImpl().build();

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
//        final MessageRestClient messageRestClient = new MessageRestClient(resteasyClient);
//
//        System.out.println("getMessagesByClientName");
//        messageRestClient.getMessagesByClientName("miro").forEach(System.out::println);
//        System.out.println("getMessagesByChannelName");
//        messageRestClient.getMessagesByChannelName("general").forEach(System.out::println);
    }

}

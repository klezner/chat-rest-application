package pl.kl.chat.handlers.service;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import pl.kl.chat.ChatClient;
import pl.kl.chat.common.Actions;
import pl.kl.chat.common.ExceptionDto;
import pl.kl.chat.common.ResponseDto;
import pl.kl.chat.handlers.rest.MainRestClientFactory;
import pl.kl.chat.handlers.rest.RestClientFactory;
import pl.kl.chat.handlers.rest.channels.ChannelClient;
import pl.kl.chat.handlers.rest.channels.ChannelDto;
import pl.kl.chat.handlers.rest.clients.ClientClient;
import pl.kl.chat.handlers.rest.clients.ClientDto;
import pl.kl.chat.handlers.rest.messages.MessageClient;
import pl.kl.chat.messagecache.MessageCache;
import pl.kl.chat.messagecache.MessageCacheList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

public class ChatClientService implements ClientService {

    private static final String DEFAULT_CHANNEL_NAME = "general";
    private final ChatClient chatClient;
    private final BufferedReader reader;
    private final PrintWriter printer;
    private final RestClientFactory restClientFactory;
    private final ResteasyClient resteasyClient;
    private final ClientClient clientClient;
    private final ChannelClient channelClient;
    private final MessageClient messageClient;

    public ChatClientService(ChatClient chatClient, BufferedReader reader, PrintWriter printer) {
        this.chatClient = chatClient;
        this.reader = reader;
        this.printer = printer;
        this.restClientFactory = new MainRestClientFactory();
        this.resteasyClient = restClientFactory.createResteasyClient();
        this.clientClient = restClientFactory.createClientClient(resteasyClient);
        this.channelClient = restClientFactory.createChannelClient(resteasyClient);
        this.messageClient = restClientFactory.createMessageClient(resteasyClient);
    }

    public void loginClient() throws IOException {
        printer.println("Welcome, please enter your username: ");
        ResponseDto response = null;
        String name = reader.readLine().trim();
        while (!(response instanceof ClientDto) || name.isBlank() || name.isEmpty()) {
            if (name.isEmpty()) {
                printer.println("Username is empty. Please enter different username: ");
                name = reader.readLine();
            } else if (name.isBlank()) {
                printer.println("Username is blank. Please enter different username: ");
                name = reader.readLine();
            } else {
                response = clientClient.createClient(name);
                channelClient.createChannel(DEFAULT_CHANNEL_NAME);
                channelClient.setChannelClient(DEFAULT_CHANNEL_NAME, name);
                if (response instanceof ExceptionDto) {
                    printer.println("User already exists! Please enter different username: ");
                    name = reader.readLine();
                }
            }
        }
        chatClient.setName(((ClientDto) response).getName());
        chatClient.setActiveChannel(((ClientDto) response).getActiveChannel());
        printer.printf("%s, welcome on %s channel!%n", chatClient.getName(), chatClient.getActiveChannel());
    }

    public void handleInvalidAction() {
        printer.println("Invalid action");
    }

    public void syncChannel() {
        final MessageCache cachedMessages = chatClient.getMessageCache().getCachedMessagesFromChannel(chatClient.getActiveChannel())
                .orElse(new MessageCacheList());
        String message;
        while ((message = cachedMessages.getLastCachedMessage()) != null) {
            printer.println(message);
        }
    }

    public void handleInvalidChannelAction() {
        printer.println("Invalid channel action");
    }

    public void handleChannelCreateOrJoin(String channelName) {
        if (channelName.isEmpty() || channelName.isBlank()) {
            printer.println("Channel name you have entered is empty, please enter not empty channel name");
        } else {
            final ResponseDto response = channelClient.createChannel(channelName);
            channelClient.setChannelClient(channelName, chatClient.getName());
            clientClient.setClientActiveChannel(chatClient.getName(), channelName);
            chatClient.setActiveChannel(channelName);
            if (response instanceof ChannelDto) {
                printer.printf("Channel: %s has been created. You have joined channel: %s%n", channelName, chatClient.getActiveChannel());
                syncChannel();
            } else {
                printer.printf("Channel: %s exists. You have joined channel: %s%n", channelName, chatClient.getActiveChannel());
                syncChannel();
            }
        }
    }

    public void handleChannelUsernamesGet() {
        final String clients = String.join(" ", ((ChannelDto) channelClient.getChannelByName(chatClient.getActiveChannel())).getClients());
        printer.printf("Channel: %s clients: %s%n", chatClient.getActiveChannel(), clients);
    }

    public void handleChannelHistory() {
        final List<String> chatHistory = messageClient.getMessagesByChannelName(chatClient.getActiveChannel()).stream()
                .map(messageDto -> messageDto.getTimestamp() + ": " + messageDto.getClientSentBy() + " -> " + messageDto.getChannelSentTo() + ": " + messageDto.getContent())
                .toList();
        if (chatHistory.isEmpty()) {
            printer.printf("No such history for channel: %s%n", chatClient.getActiveChannel());
        } else {
            chatHistory.forEach(printer::println);
        }
    }

    public void handleAllChannelsGet() {
        final String channels = channelClient.getAllChannels().stream()
                .map(ChannelDto::getName)
                .collect(Collectors.joining(" "));
        printer.printf("All channels: %s%n", channels);
    }

    public void handleAllUsernamesGet() {
        final String clients = clientClient.getAllClients().stream()
                .map(ClientDto::getName)
                .collect(Collectors.joining(" "));
        printer.printf("All clients: %s%n", clients);
    }

    public void handleAboutMe() {
        final ClientDto client = (ClientDto) clientClient.getClientByName(chatClient.getName());
        final String clientChannels = channelClient.getAllChannels().stream()
                .filter(channelDto -> channelDto.getClients().contains(chatClient.getName()))
                .map(ChannelDto::getName)
                .collect(Collectors.joining(" "));
        final String messageFormat = String.format("Username: %s | Subscribed channels: %s | Active channel: %s", client.getName(), clientChannels, client.getActiveChannel());
        printer.println(messageFormat);
    }

    public void handleHelp() {
        final String messageFormat = String.format("Available commands:%n" +
                        "%s -> about me (username, subscribed channels, active channel)%n" +
                        "%s -> get all users%n" +
                        "%s -> get all channels%n" +
                        "%s -> get my channel history%n" +
                        "%s %s channel_name -> create and join new channels or join channel if exists (still subscribed)%n" +
                        "%s %s -> get all channel users (subscribing channel)%n" +
                        "%s file_name -> upload file to server%n" +
                        "/downlo@d file_name -> download file to server%n" + // bug with word "download"
                        "%s -> disconnect server and close client",
                Actions.ME.getInput(),
                Actions.ALL_CLIENTS.getInput(),
                Actions.ALL_CHANNELS.getInput(),
                Actions.CHAT_HISTORY.getInput(),
                Actions.CHANNEL.getInput(), Actions.CHANNEL_JOIN.getInput(),
                Actions.CHANNEL.getInput(), Actions.ALL_CLIENTS.getInput(),
                Actions.SEND_FILE.getInput(),
//                Actions.DOWNLOAD_FILE.getInput(),
                Actions.CLOSE_CONNECTION.getInput());
        printer.println(messageFormat);
    }

    public void handleLogout() {
        printer.println("Disconnecting...");
        Runtime.getRuntime().exit(0);
    }

}

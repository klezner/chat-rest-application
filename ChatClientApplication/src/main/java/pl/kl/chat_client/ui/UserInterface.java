package pl.kl.chat_client.ui;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import pl.kl.chat_client.ChatClient;
import pl.kl.chat_client.common.Actions;
import pl.kl.chat_client.common.ExceptionDto;
import pl.kl.chat_client.common.ResponseDto;
import pl.kl.chat_client.handlers.rest.ChatClientFactory;
import pl.kl.chat_client.handlers.rest.MainChatClientFactory;
import pl.kl.chat_client.handlers.rest.channels.ChannelClient;
import pl.kl.chat_client.handlers.rest.channels.ChannelDto;
import pl.kl.chat_client.handlers.rest.clients.ClientClient;
import pl.kl.chat_client.handlers.rest.clients.ClientDto;
import pl.kl.chat_client.handlers.rest.messages.MessageClient;
import pl.kl.chat_client.jmsmessageservice.JmsMessageListener;
import pl.kl.chat_client.jmsmessageservice.JmsMessageSender;

import javax.jms.JMSException;
import javax.naming.NamingException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

public class UserInterface {

    private static final String DEFAULT_CHANNEL_NAME = "general";
    private static final String CMD_PREFIX = "/";
    private static final String SPLIT_REGEX = " ";
    private static final int MAX_SPLIT_LIMIT = 3;
    private final ChatClient chatClient;
    private final UserInterfaceFactory userInterfaceFactory = new MainUserInterfaceFactory();
    private final BufferedReader reader = userInterfaceFactory.createBufferedReader();
    private final PrintWriter printer = userInterfaceFactory.createPrintWriter();
    private final ChatClientFactory chatClientFactory = new MainChatClientFactory();
//    private final ProxyFactory proxyFactory;
//    private final ConnectionFactory connectionFactory;
//    private final Topic topic;

    //    private final JmsMessageListener messageListener;
    private final JmsMessageSender messageSender;
    private final ResteasyClient resteasyClient = chatClientFactory.createResteasyClient();
    private final ClientClient clientClient = chatClientFactory.createClientClient(resteasyClient);
    private final ChannelClient channelClient = chatClientFactory.createChannelClient(resteasyClient);
    private final MessageClient messageClient = chatClientFactory.createMessageClient(resteasyClient);

    public UserInterface(ChatClient chatClient) throws NamingException {
        this.chatClient = chatClient;
//        this.messageListener = new JmsMessageListener();
        this.messageSender = new JmsMessageSender();

//        this.proxyFactory = chatClientFactory.createProxyFactory();
//        this.connectionFactory = chatClientFactory.createConnectionFactory(proxyFactory);
//        this.topic = chatClientFactory.createTopic(proxyFactory);
//        this.messageListener = chatClientFactory.createMessageListener(connectionFactory, topic, chatClient.getName(), chatClient.getActiveChannel());
//        this.messageSender = chatClientFactory.createMessageSender(connectionFactory, topic, reader, chatClient.getName(), chatClient.getActiveChannel());
    }

    public void loginUser() throws IOException {
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
        printer.printf("Welcome %s!%n", name);
    }

    public void handleUser() throws IOException, JMSException {
        // TODO: pomyśleć co z tym
        new Thread(new JmsMessageListener()).start();
        String message;
        while ((message = reader.readLine()) != null) {
            if (message.startsWith(CMD_PREFIX)) {
                final String[] splitMessage = message.split(SPLIT_REGEX, MAX_SPLIT_LIMIT);
                final String command = splitMessage[0];
                if (Actions.CLOSE_CONNECTION.getInput().equalsIgnoreCase(command) && splitMessage.length == 1) {
                    // TODO:
                    handleLogout();
                } else if (Actions.HELP.getInput().equalsIgnoreCase(command) && splitMessage.length == 1) {
                    handleHelp();
                } else if (Actions.ME.getInput().equalsIgnoreCase(command) && splitMessage.length == 1) {
                    handleAboutMe();
                } else if (Actions.ALL_CLIENTS.getInput().equalsIgnoreCase(command) && splitMessage.length == 1) {
                    handleAllUsernamesGet();
                } else if (Actions.ALL_CHANNELS.getInput().equalsIgnoreCase(command) && splitMessage.length == 1) {
                    handleAllChannelsGet();
                } else if (Actions.ALL_FILES.getInput().equalsIgnoreCase(command) && splitMessage.length == 1) {
                    handleServerFiles();
                } else if (Actions.CHAT_HISTORY.getInput().equalsIgnoreCase(command) && splitMessage.length == 1) {
                    handleChannelHistory();
                } else if (Actions.CHANNEL.getInput().equalsIgnoreCase(command) && splitMessage.length >= 1) {
                    final String commandOption = splitMessage[1];
                    if (Actions.ALL_CLIENTS.getInput().equalsIgnoreCase(commandOption) && splitMessage.length == 2) {
                        handleChannelUsernamesGet();
                    } else if (Actions.CHANNEL_JOIN.getInput().equalsIgnoreCase(commandOption) && splitMessage.length == 3) {
                        final String channelName = splitMessage[2];
                        handleChannelCreateOrJoin(channelName);
                    } else {
                        handleInvalidChannelAction();
                    }
                } else if (Actions.UPLOAD_FILE.getInput().equalsIgnoreCase(command) && splitMessage.length == 2) {
                    // TODO:
                    final String filePath = splitMessage[1];
                    handleFileUpload(filePath);
                } else if (Actions.DOWNLOAD_FILE.getInput().equalsIgnoreCase(command) && splitMessage.length == 2) {
                    // TODO:
                    handleFileDownload(message);
                } else {
                    handleInvalidAction();
                }
            } else {
                handleMessageSend(message);
            }
        }
    }
    // TODO: zaimplementować cache

    private void handleMessageSend(String message) throws JMSException {
        messageSender.sendMessage(chatClient.getName(), chatClient.getActiveChannel(), message);
    }

    private void handleInvalidAction() {
        printer.println("Invalid action");
    }

    private void handleFileDownload(String message) {

    }

    private void handleFileUpload(String filePath) {

    }

    private void handleInvalidChannelAction() {
        printer.println("Invalid channel action");
    }

    private void handleChannelCreateOrJoin(String channelName) {
        if (channelName.isEmpty() || channelName.isBlank()) {
            printer.println("Channel name you have entered is empty, please enter not empty channel name");
        } else {
            final ResponseDto response = channelClient.createChannel(channelName);
            channelClient.setChannelClient(channelName, chatClient.getName());
            clientClient.setClientActiveChannel(chatClient.getName(), channelName);
            chatClient.setActiveChannel(channelName);
            if (response instanceof ChannelDto) {
                printer.printf("Channel: %s has been created. You have joined channel: %s%n", channelName, chatClient.getActiveChannel());
            } else {
                printer.printf("Channel: %s exists. You have joined channel: %s%n", channelName, chatClient.getActiveChannel());
            }
        }
    }

    private void handleChannelUsernamesGet() {
        final String clients = String.join(" ", ((ChannelDto) channelClient.getChannelByName(chatClient.getActiveChannel())).getClients());
        printer.printf("Channel: %s clients: %s%n", chatClient.getActiveChannel(), clients);
    }

    private void handleChannelHistory() {
        final List<String> chatHistory = messageClient.getMessagesByChannelName(chatClient.getActiveChannel()).stream()
                .map(messageDto -> messageDto.getTimestamp() + ": " + messageDto.getClientSentBy() + " -> " + messageDto.getChannelSentTo() + ": " + messageDto.getContent())
                .toList();
        if (chatHistory.isEmpty()) {
            printer.printf("No such history for channel: %s%n", chatClient.getActiveChannel());
        } else {
            chatHistory.forEach(printer::println);
        }
    }

    private void handleServerFiles() {

    }

    private void handleAllChannelsGet() {
        final String channels = channelClient.getAllChannels().stream()
                .map(ChannelDto::getName)
                .collect(Collectors.joining(" "));
        printer.printf("All channels: %s%n", channels);
    }

    private void handleAllUsernamesGet() {
        final String clients = clientClient.getAllClients().stream()
                .map(ClientDto::getName)
                .collect(Collectors.joining(" "));
        printer.printf("All clients: %s%n", clients);
    }

    private void handleAboutMe() {
        final ClientDto client = (ClientDto) clientClient.getClientByName(chatClient.getName());
        final String messageFormat = String.format("Username: %s | Active channel: %s", client.getName(), client.getActiveChannel());
        printer.println(messageFormat);
    }

    private void handleHelp() {
        final String messageFormat = String.format("Available commands:%n" +
                        "%s -> about me (username, subscribed channels, active channel)%n" +
                        "%s -> get all users%n" +
                        "%s -> get all channels%n" +
                        "%s -> get my channel history%n" +
                        "%s -> get all server files%n" +
                        "%s %s channel_name -> create and join new channels or join channel if exists (still subscribed)%n" +
                        "%s %s -> leave channel (unsubscribe) and delete if there is no clients%n" +
                        "%s %s -> get all channel users (subscribing channel)%n" +
                        "%s file_name -> upload file to server%n" +
                        "/downlo@d file_name -> download file to server%n" + // bug with word "download"
                        "%s -> disconnect server and close client",
                Actions.ME.getInput(), Actions.ALL_CLIENTS.getInput(), Actions.ALL_CHANNELS.getInput(),
                Actions.CHAT_HISTORY.getInput(), Actions.ALL_FILES.getInput(), Actions.CHANNEL.getInput(),
                Actions.CHANNEL_JOIN.getInput(), Actions.CHANNEL.getInput(), Actions.CHANNEL_LEAVE.getInput(),
                Actions.CHANNEL.getInput(), Actions.ALL_CLIENTS.getInput(), Actions.UPLOAD_FILE.getInput(),
//                Actions.DOWNLOAD_FILE.getInput(),
                Actions.CLOSE_CONNECTION.getInput());
        printer.println(messageFormat);
    }

    private void handleLogout() {
        printer.println("handleLogout");
//        messageListener.get
    }

}

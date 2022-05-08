package pl.kl.chat_client.ui;

import pl.kl.chat_client.ChatClient;
import pl.kl.chat_client.common.Actions;
import pl.kl.chat_client.handlers.service.ClientService;
import pl.kl.chat_client.jmsmessageservice.JmsMessageListener;
import pl.kl.chat_client.jmsmessageservice.JmsMessageSender;

import javax.jms.JMSException;
import javax.naming.NamingException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class UserInterface {

    private static final String CMD_PREFIX = "/";
    private static final String SPLIT_REGEX = " ";
    private static final int MAX_SPLIT_LIMIT = 3;
    private final ChatClient chatClient;
    private final UserInterfaceFactory userInterfaceFactory;
    private final ClientService clientService;
    private final BufferedReader reader;
    private final PrintWriter printer;
    private final JmsMessageSender messageSender;

    public UserInterface(ChatClient chatClient) throws NamingException {
        this.chatClient = chatClient;
        this.userInterfaceFactory = new MainUserInterfaceFactory();
        this.reader = userInterfaceFactory.createBufferedReader();
        this.printer = userInterfaceFactory.createPrintWriter();
        this.clientService = userInterfaceFactory.createClientService(chatClient, reader, printer);
        this.messageSender = new JmsMessageSender();
    }

    public void loginUser() throws IOException {
        clientService.loginClient();
    }

    public void handleUser() throws NamingException, IOException, JMSException {
        // TODO: pomyśleć co z tym
        startJmsListenerThread();

        String message;
        while ((message = reader.readLine()) != null) {
            if (message.startsWith(CMD_PREFIX)) {
                final String[] splitMessage = message.split(SPLIT_REGEX, MAX_SPLIT_LIMIT);
                final String command = splitMessage[0];
                if (Actions.CLOSE_CONNECTION.getInput().equalsIgnoreCase(command) && splitMessage.length == 1) {
                    handleLogout();
                } else if (Actions.HELP.getInput().equalsIgnoreCase(command) && splitMessage.length == 1) {
                    handleHelp();
                } else if (Actions.ME.getInput().equalsIgnoreCase(command) && splitMessage.length == 1) {
                    handleAboutMe();
                } else if (Actions.ALL_CLIENTS.getInput().equalsIgnoreCase(command) && splitMessage.length == 1) {
                    handleAllUsernamesGet();
                } else if (Actions.ALL_CHANNELS.getInput().equalsIgnoreCase(command) && splitMessage.length == 1) {
                    handleAllChannelsGet();
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
                } else if (Actions.SEND_FILE.getInput().equalsIgnoreCase(command) && splitMessage.length == 2) {
                    final String filePath = splitMessage[1];
                    handleFileSend(filePath);
                } else {
                    handleInvalidAction();
                }
            } else {
                handleMessageSend(message);
            }
        }
    }

    private void startJmsListenerThread() throws NamingException {
        final Thread jmsListenerThread = new Thread(new JmsMessageListener(chatClient, printer));
        jmsListenerThread.start();
    }

    private void handleMessageSend(String message) throws JMSException {
        messageSender.sendMessage(chatClient.getName(), chatClient.getActiveChannel(), message);
    }

    private void handleFileSend(String filePath) {
        // TODO: zmienić
        printer.println("To implement: handleFileUpload");
        messageSender.sendFile(chatClient.getName(), chatClient.getActiveChannel(), filePath);
    }

    private void handleInvalidAction() {
        clientService.handleInvalidAction();
    }

    private void handleInvalidChannelAction() {
        clientService.handleInvalidChannelAction();
    }

    private void handleChannelCreateOrJoin(String channelName) {
        clientService.handleChannelCreateOrJoin(channelName);
    }

    private void handleChannelUsernamesGet() {
        clientService.handleChannelUsernamesGet();
    }

    private void handleChannelHistory() {
        clientService.handleChannelHistory();
    }

    private void handleAllChannelsGet() {
        clientService.handleAllChannelsGet();
    }

    private void handleAllUsernamesGet() {
        clientService.handleAllUsernamesGet();
    }

    private void handleAboutMe() {
        clientService.handleAboutMe();
    }

    private void handleHelp() {
        clientService.handleHelp();
    }

    private void handleLogout() {
        clientService.handleLogout();
    }

}

package pl.kl.chat.messagingservice;

import lombok.SneakyThrows;
import pl.kl.chat.ChatClient;
import pl.kl.chat.common.ChatMessage;
import pl.kl.chat.common.ChatMessageType;
import pl.kl.chat.common.JndiNamesProvider;

import javax.jms.*;
import javax.naming.NamingException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class JmsMessageListener implements MessageListener, Runnable {

    private static final String CLIENT_BASE_DIRECTORY = ".\\ClientsDownloads\\";
    private final JmsMessagingFactory jmsMessagingFactory = new MainJmsMessagingFactory();
    private final ProxyFactory proxyFactory = jmsMessagingFactory.createProxyFactory();
    private final ConnectionFactory connectionFactory;
    private final Topic topic;
    private final ChatClient chatClient;
    private final PrintWriter printer;

    @SneakyThrows
    public JmsMessageListener(ChatClient chatClient, PrintWriter printer) throws NamingException {
        this.connectionFactory = proxyFactory.createProxy(JndiNamesProvider.CONNECTION_FACTORY_JNDI_NAME);
        this.topic = proxyFactory.createProxy(JndiNamesProvider.MESSAGES_TOPIC_JNDI_NAME);
        this.chatClient = chatClient;
        this.printer = printer;
    }

    @Override
    public void run() {
        listenMessage();
    }

    public void listenMessage() {
        try (JMSContext jmsContext = connectionFactory.createContext();
             JMSConsumer consumer = jmsContext.createConsumer(topic)) {
            while (true) {
                consumer.setMessageListener(this::onMessage);
            }
        }
    }

    @Override
    public void onMessage(Message message) {
        try {
            final ChatMessage chatMessage = message.getBody(ChatMessage.class);
            if (ChatMessageType.TEXT == chatMessage.getType()) {
                if (chatClient.getActiveChannel().equals(chatMessage.getChannelSentTo())) {
                    printer.println(chatMessage.getClientSentBy() + " -> " + chatMessage.getChannelSentTo() + ": " + chatMessage.getContent());
                } else {
                    chatClient.getMessageCache().cacheMessageFromChannel(chatMessage.getClientSentBy(), chatMessage.getChannelSentTo(), chatMessage.getContent());
                }
            } else if (ChatMessageType.FILE == chatMessage.getType()) {
                if (chatClient.getActiveChannel().equals(chatMessage.getChannelSentTo()) && !chatMessage.getClientSentBy().equals(chatClient.getName())) {
                    final String pathDelimiter = "\\";
                    String filePath = CLIENT_BASE_DIRECTORY + chatClient.getName() + pathDelimiter + chatMessage.getFileName();
                    saveFile(chatMessage, filePath);
                }
            }
        } catch (JMSException e) {
            printer.println("Unable to listen messages...");
        }
    }

    private void saveFile(ChatMessage chatMessage, String filePath) {
        final byte[] fileContentBytes = chatMessage.getFileContentBytes();
        final File file = new File(filePath + chatMessage.getFileName());
        file.getParentFile().mkdirs();
        try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
            outputStream.write(fileContentBytes);
            printer.printf("%s has sent file to %s channel%nFile: %s has been downloaded%n", chatMessage.getClientSentBy(), chatMessage.getChannelSentTo(), chatMessage.getFileName());
        } catch (IOException e) {
            printer.println("Cannot read file: " + e.getMessage());
        }
    }
}

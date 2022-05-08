package pl.kl.chat.messagingservice;

import pl.kl.chat.common.ChatMessage;
import pl.kl.chat.common.ChatMessageType;
import pl.kl.chat.common.JndiNamesProvider;

import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Message;
import javax.jms.Topic;
import javax.naming.NamingException;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;

public class JmsMessageSender {

    private final JmsMessagingFactory jmsMessagingFactory = new MainJmsMessagingFactory();
    private final ProxyFactory proxyFactory = jmsMessagingFactory.createProxyFactory();
    private final ConnectionFactory connectionFactory;
    private final Topic topic;
    private final PrintWriter printer;

    public JmsMessageSender(PrintWriter printer) throws NamingException {
        this.connectionFactory = proxyFactory.createProxy(JndiNamesProvider.CONNECTION_FACTORY_JNDI_NAME);
        this.topic = proxyFactory.createProxy(JndiNamesProvider.MESSAGES_TOPIC_JNDI_NAME);
        this.printer = printer;
    }

    public void sendMessage(String name, String activeChannel, String message) {
        final ChatMessage chatMessage = ChatMessage.builder()
                .clientSentBy(name)
                .channelSentTo(activeChannel)
                .type(ChatMessageType.TEXT)
                .content(message)
                .build();
        try (JMSContext jmsContext = connectionFactory.createContext()) {
            final Message messageToSend = jmsContext.createObjectMessage(chatMessage);
            jmsContext.createProducer().send(topic, messageToSend);
        }
    }

    public void sendFile(String name, String activeChannel, String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            final ChatMessage chatMessage = ChatMessage.builder()
                    .clientSentBy(name)
                    .channelSentTo(activeChannel)
                    .type(ChatMessageType.FILE)
                    .fileName(file.getName())
                    .fileContentBytes(encodeFileToBytes(file))
                    .build();
            try (JMSContext jmsContext = connectionFactory.createContext()) {
                final Message messageToSend = jmsContext.createObjectMessage(chatMessage);
                jmsContext.createProducer().send(topic, messageToSend);
            }
            printer.printf("File: %s has been sent to %s channel%n", file.getName(), activeChannel);
        } else {
            printer.printf("File: %s does not exists!%n", file.getName());
        }
    }

    private byte[] encodeFileToBytes(File file) {
        byte[] fileBytes = new byte[0];
        try {
            fileBytes = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            printer.println("Unable to convert file to bytes");
        }
        return fileBytes;
    }

}

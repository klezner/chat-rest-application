package pl.kl.chat_client.jmsmessageservice;

import lombok.SneakyThrows;
import pl.kl.chat_client.ChatClient;

import javax.jms.*;
import javax.naming.NamingException;
import java.io.PrintWriter;

public class JmsMessageListener implements MessageListener, Runnable {

    private static final String CONNECTION_FACTORY_JNDI_NAME = "jms/RemoteConnectionFactory";
    private static final String MESSAGES_TOPIC_JNDI_NAME = "jms/topic/Messages";
    private final JmsMessagingFactory jmsMessagingFactory = new MainJmsMessagingFactory();
    private final ProxyFactory proxyFactory = jmsMessagingFactory.createProxyFactory();
    private final ConnectionFactory connectionFactory;
    private final Topic topic;
    private final ChatClient chatClient;
    private final PrintWriter printer;

    @SneakyThrows
    public JmsMessageListener(ChatClient chatClient, PrintWriter printer) throws NamingException {
        this.connectionFactory = proxyFactory.createProxy(CONNECTION_FACTORY_JNDI_NAME);
        this.topic = proxyFactory.createProxy(MESSAGES_TOPIC_JNDI_NAME);
        this.chatClient = chatClient;
        this.printer = printer;
    }

    @Override
    public void run() {
        listenMessage();
    }

    public void listenMessage() {
        try (JMSContext jmsContext = connectionFactory.createContext();
             JMSConsumer consumer = jmsContext.createConsumer(topic);) {
            while (true) {
                consumer.setMessageListener(this::onMessage);
            }
        }
    }

//    private static MessageListener onMessage = message -> {
//        try {
////            final MessageTransferred messageBody = message.getBody(MessageTransferred.class);
////            log.info(messageBody.getContent() + " to " + messageBody.channelSentTo + ": " + messageBody.getContent());
////            final String body = message.getBody(String.class);
////            log.info(body);
//            final String client = message.getStringProperty("client");
//            final String channel = message.getStringProperty("channel");
//            final String content = message.getStringProperty("content");
//            System.out.println(client + " -> " + channel + ": " + content);
//        } catch (JMSException e) {
//            e.printStackTrace();
//        }
//    };

    @Override
    public void onMessage(Message message) {
        try {
//            final MessageTransferred messageBody = message.getBody(MessageTransferred.class);
//            log.info(messageBody.getContent() + " to " + messageBody.channelSentTo + ": " + messageBody.getContent());
//            final String body = message.getBody(String.class);
//            log.info(body);
            final String client = message.getStringProperty("client");
            final String channel = message.getStringProperty("channel");
            final String content = message.getStringProperty("content");
            if (chatClient.getActiveChannel().equals(channel)) {
                printer.println(client + " -> " + channel + ": " + content);
            } else {
                chatClient.getMessageCache().cacheMessageFromChannel(client, channel, content);
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}

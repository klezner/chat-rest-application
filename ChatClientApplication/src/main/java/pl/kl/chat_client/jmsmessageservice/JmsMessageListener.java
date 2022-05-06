package pl.kl.chat_client.jmsmessageservice;

import lombok.SneakyThrows;
import lombok.extern.java.Log;

import javax.jms.*;

@Log // TODO: usunąć
public class JmsMessageListener implements Runnable {

    private static final String CONNECTION_FACTORY_JNDI_NAME = "jms/RemoteConnectionFactory";
    private static final String MESSAGES_TOPIC_JNDI_NAME = "jms/topic/Messages";
    private static MessageListener onMessage = message -> {
        try {
//            final MessageTransferred messageBody = message.getBody(MessageTransferred.class);
//            log.info(messageBody.getContent() + " to " + messageBody.channelSentTo + ": " + messageBody.getContent());
//            final String body = message.getBody(String.class);
//            log.info(body);
            final String client = message.getStringProperty("client");
            final String channel = message.getStringProperty("channel");
            final String content = message.getStringProperty("content");
            System.out.println(client + " -> " + channel + ": " + content);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    };
    private final ProxyFactory proxyFactory;
    private final ConnectionFactory connectionFactory;
    private final Topic topic;

    @SneakyThrows
    public JmsMessageListener() {
        this.proxyFactory = new ProxyFactory();
        this.connectionFactory = proxyFactory.createProxy(CONNECTION_FACTORY_JNDI_NAME);
        this.topic = proxyFactory.createProxy(MESSAGES_TOPIC_JNDI_NAME);
    }

    public void listenMessage() {
        JMSContext jmsContext = connectionFactory.createContext();
        JMSConsumer consumer = jmsContext.createConsumer(topic);
        while (true) {
//            System.out.println("SŁUCHAM");
            consumer.setMessageListener(onMessage);
        }
    }

    @Override
    public void run() {
        listenMessage();
    }
}

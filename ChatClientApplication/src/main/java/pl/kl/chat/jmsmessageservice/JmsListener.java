package pl.kl.chat.jmsmessageservice;

import lombok.extern.java.Log;

import javax.jms.*;
import javax.naming.NamingException;
import java.util.Scanner;

@Log
public class JmsListener {

    private static final String CONNECTION_FACTORY_JNDI_NAME = "jms/RemoteConnectionFactory";
    private static final String MESSAGES_TOPIC_JNDI_NAME = "jms/topic/Messages";

    private static MessageListener onMessage = message -> {
        try {
            final String client = message.getStringProperty("client");
            final String channel = message.getStringProperty("channel");
            final String content = message.getStringProperty("content");
            System.out.println(client + " -> " + channel + ": " + content);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    };

    public static void main(String[] args) throws NamingException {
        var proxyFactory = new ProxyFactory();
        ConnectionFactory connectionFactory = proxyFactory.createProxy(CONNECTION_FACTORY_JNDI_NAME);
        Topic topic = proxyFactory.createProxy(MESSAGES_TOPIC_JNDI_NAME);
        try (JMSContext jmsContext = connectionFactory.createContext(); JMSConsumer consumer = jmsContext.createConsumer(topic)) {
            consumer.setMessageListener(onMessage);
            new Scanner(System.in).next();
        }
    }

}

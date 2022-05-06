package pl.kl.chat_client.jmsmessageservice;

import lombok.SneakyThrows;

import javax.jms.*;

public class JmsMessageSender {

    private static final String CONNECTION_FACTORY_JNDI_NAME = "jms/RemoteConnectionFactory";
    private static final String MESSAGES_TOPIC_JNDI_NAME = "jms/topic/Messages";
    private final ProxyFactory proxyFactory;
    private final ConnectionFactory connectionFactory;
    private final Topic topic;

    @SneakyThrows
    public JmsMessageSender() {
        this.proxyFactory = new ProxyFactory();
        this.connectionFactory = proxyFactory.createProxy(CONNECTION_FACTORY_JNDI_NAME);
        this.topic = proxyFactory.createProxy(MESSAGES_TOPIC_JNDI_NAME);
    }

    public void sendMessage(String name, String activeChannel, String message) throws JMSException {
        try (JMSContext jmsContext = connectionFactory.createContext()) {
            final Message messageToSend = jmsContext.createObjectMessage();
            messageToSend.setStringProperty("client", name);
            messageToSend.setStringProperty("channel", activeChannel);
            messageToSend.setStringProperty("content", message);
            jmsContext.createProducer().send(topic, messageToSend);
        }
        System.out.println("WYSLANO");
    }

    public void sendFile(String name, String activeChannel, String file) {
        // TODO: zaimplementować
    }

}

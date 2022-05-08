package pl.kl.chat_client.jmsmessageservice;

import pl.kl.chat_client.common.JndiNamesProvider;

import javax.jms.*;
import javax.naming.NamingException;
import java.io.File;

public class JmsMessageSender {

    private final JmsMessagingFactory jmsMessagingFactory = new MainJmsMessagingFactory();
    private final ProxyFactory proxyFactory = jmsMessagingFactory.createProxyFactory();
    private final ConnectionFactory connectionFactory;
    private final Topic topic;

    public JmsMessageSender() throws NamingException {
        this.connectionFactory = proxyFactory.createProxy(JndiNamesProvider.CONNECTION_FACTORY_JNDI_NAME);
        this.topic = proxyFactory.createProxy(JndiNamesProvider.MESSAGES_TOPIC_JNDI_NAME);
    }

    public void sendMessage(String name, String activeChannel, String message) throws JMSException {
        try (JMSContext jmsContext = connectionFactory.createContext()) {
            final Message messageToSend = jmsContext.createObjectMessage();
            messageToSend.setStringProperty("client", name);
            messageToSend.setStringProperty("channel", activeChannel);
            messageToSend.setStringProperty("content", message);
            jmsContext.createProducer().send(topic, messageToSend);
        }
    }

    public void sendFile(String name, String activeChannel, String filePath) {
        if (ifFileExists(filePath)) {
            // TODO: zaimplementować
        } else {
            // TODO: zaimplementować
        }
    }

    private boolean ifFileExists(String filePath) {
        final File fileToSend = new File(filePath);
        return fileToSend.exists();
    }

}

package pl.kl.chat.messagingservice;

import javax.naming.NamingException;

public interface JmsMessagingFactory {

    ProxyFactory createProxyFactory() throws NamingException;

}

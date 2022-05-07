package pl.kl.chat_client.jmsmessageservice;

import javax.naming.NamingException;

public interface JmsMessagingFactory {

    ProxyFactory createProxyFactory() throws NamingException;

}

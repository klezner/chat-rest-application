package pl.kl.chat.jmsmessageservice;

import javax.naming.NamingException;

public interface JmsMessagingFactory {

    ProxyFactory createProxyFactory() throws NamingException;

}

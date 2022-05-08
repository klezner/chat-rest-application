package pl.kl.chat.jmsmessageservice;

import javax.naming.NamingException;

public class MainJmsMessagingFactory implements JmsMessagingFactory {

    @Override
    public ProxyFactory createProxyFactory() throws NamingException {
        return new ProxyFactory();
    }

}

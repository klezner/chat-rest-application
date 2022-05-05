package pl.kl.chat_rest.messages.adapters.messaging;

import lombok.SneakyThrows;
import lombok.extern.java.Log;
import pl.kl.chat_rest.messages.ports.MessageService;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.Message;
import javax.jms.MessageListener;

@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "Messages")
})
@Log
public class MessagingService implements MessageListener {

    @Inject
    private MessageService messageService;
    @Inject
    private TransferredMessageMapper messageMapper;

    @SneakyThrows
    @Override
    public void onMessage(Message message) {
        final String client = message.getStringProperty("client");
        final String channel = message.getStringProperty("channel");
        final String content = message.getStringProperty("content");
        log.info(client + " to " + channel + " : " + content);

//        final MessageTransferred messageTransferred = (MessageTransferred) ((ObjectMessage) message).getObject();

//        log.info("OTRZYMANO WIADOMOŚĆ");
//        final String body = message.getBody(String.class);
//        log.info("WIADOMOSC: " + body);

//        final MessageTransferred messageBody = message.getBody(MessageTransferred.class);
//        log.info("Odebrana wiadomość: " + messageBody.toString());
//        log.info(messageTransferred.toString());
//        messageService.create(messageTransferred.clientSentBy, messageTransferred.channelSentTo, messageTransferred.content);

        messageService.create(client, channel, content);
    }

}

package pl.kl.chat.messages.adapters.messaging;

import lombok.SneakyThrows;
import lombok.extern.java.Log;
import pl.kl.chat.common.ChatMessage;
import pl.kl.chat.messages.ports.MessageService;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.Message;
import javax.jms.MessageListener;

@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "Messages")
})
// TODO: usunąć
@Log
public class MessagingService implements MessageListener {

    @Inject
    private MessageService messageService;
    @Inject
    private TransferredMessageMapper messageMapper;

    @SneakyThrows
    @Override
    public void onMessage(Message message) {
        final ChatMessage chatMessage = message.getBody(ChatMessage.class);
        // TODO: usunąć
        log.info(chatMessage.getClientSentBy() + " -> " + chatMessage.getChannelSentTo() + ": " + chatMessage.getContent());
        messageService.create(chatMessage.getClientSentBy(), chatMessage.getChannelSentTo(), chatMessage.getContent());
    }

}

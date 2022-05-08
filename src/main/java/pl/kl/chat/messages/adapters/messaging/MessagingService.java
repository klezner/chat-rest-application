package pl.kl.chat.messages.adapters.messaging;

import lombok.SneakyThrows;
import pl.kl.chat.common.ChatMessage;
import pl.kl.chat.common.ChatMessageType;
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
public class MessagingService implements MessageListener {

    @Inject
    private MessageService messageService;
    @Inject
    private TransferredMessageMapper messageMapper;

    @SneakyThrows
    @Override
    public void onMessage(Message message) {
        final ChatMessage chatMessage = message.getBody(ChatMessage.class);
        if (ChatMessageType.TEXT == chatMessage.getType()) {
            messageService.create(chatMessage.getClientSentBy(), chatMessage.getChannelSentTo(), chatMessage.getContent());
        } else if (ChatMessageType.FILE == chatMessage.getType()) {
            final String messageFormat = String.format("File: %s has been sent", chatMessage.getFileName());
            messageService.create(chatMessage.getClientSentBy(), chatMessage.getChannelSentTo(), messageFormat);
        }
    }

}

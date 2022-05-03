package pl.kl.chat_rest.messages.adapters.persistence;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.time.Instant;

@NamedQuery(name = MessageEntity.GET_ALL_BY_CLIENT_NAME, query = "SELECT m FROM Message m WHERE m.clientSentBy = :name")
@NamedQuery(name = MessageEntity.GET_ALL_BY_CHANNEL_NAME, query = "SELECT m FROM Message m WHERE m.channelSentTo = :name")
@Table(name = "Messages")
@EqualsAndHashCode
@Setter
@Getter
@Entity(name = "Message")
public class MessageEntity {

    public static final String GET_ALL_BY_CLIENT_NAME = "messagesGetByClientName";
    public static final String GET_ALL_BY_CHANNEL_NAME = "messagesGetByChannelName";

    @Id
    String id;
    String clientSentBy;
    String channelSentTo;
    String content;
    Instant timestamp;

}

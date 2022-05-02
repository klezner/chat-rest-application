package pl.kl.chat_rest.channels.adapters.persistence;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@NamedQuery(name = ChannelEntity.GET_ALL, query = "SELECT c FROM Channel c")
@NamedQuery(name = ChannelEntity.GET_BY_NAME, query = "SELECT c FROM Channel c WHERE  c.name = :name")
@Table(name = "Channels")
@EqualsAndHashCode(of = "name")
@Setter
@Getter
@Entity(name = "Channel")
public class ChannelEntity {

    public static final String GET_ALL = "channelsGetAll";
    public static final String GET_BY_NAME = "channelGetByName";

    @Id
    String id;
    @Column(unique = true)
    String name;
//    Set<Client> clients;
//    List<Message> archivedMessages;

}
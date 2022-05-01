package pl.kl.chat_app.channels.adapters.persistence;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@NamedQuery(name = ChannelEntity.GET_ALL, query = "SELECT c FROM Channel c")
@NamedQuery(name = ChannelEntity.GET_BY_NAME, query = "SELECT c FROM Channel c WHERE  c.name = :name")
@NamedQuery(name = ChannelEntity.GET_BY_ID, query = "SELECT c FROM Channel c WHERE c.id = :id")
@Table(name = "Channels")
@EqualsAndHashCode(of = "id")
@Data
@Entity(name = "Channel")
public class ChannelEntity {

    public static final String GET_ALL = "channelsGetAll";
    public static final String GET_BY_NAME = "channelsGetByName";
    public static final String GET_BY_ID = "channelsGetById";

    @Id
    String id;
    // TODO: unikalnosc nazwy
    String name;
//    Set<Client> clients;
//    List<Message> archivedMessages;

}

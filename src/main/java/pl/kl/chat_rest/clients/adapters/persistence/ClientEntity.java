package pl.kl.chat_rest.clients.adapters.persistence;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@NamedQuery(name = ClientEntity.GET_ALL, query = "SELECT c FROM Client c")
@NamedQuery(name = ClientEntity.GET_BY_NAME, query = "SELECT c FROM Client c WHERE c.name = :name")
@Table(name = "Clients")
@EqualsAndHashCode(of = "name")
@Setter
@Getter
@Entity(name = "Client")
public class ClientEntity {

    public static final String GET_ALL = "clientsGetAll";
    public static final String GET_BY_NAME = "clientGetByName";

    @Id
    String id;
    @Column(unique = true)
    String name;
//    Channel activeChannel;

}

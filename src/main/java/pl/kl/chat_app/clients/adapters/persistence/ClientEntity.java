package pl.kl.chat_app.clients.adapters.persistence;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.io.Serializable;

@NamedQuery(name = ClientEntity.GET_ALL, query = "SELECT c FROM Client c")
@NamedQuery(name = ClientEntity.GET_BY_NAME, query = "SELECT c FROM Client c WHERE c.name = :name")
@Table(name = "Clients")
@EqualsAndHashCode(of = "id")
@Data
@Entity(name = "Client")
public class ClientEntity implements Serializable {

    public static final String GET_ALL = "clientsGetAll";
    public static final String GET_BY_NAME = "clientsGetByName";

    @Id
    String id;
    String name; // TODO: unikalnosc loginu
//    Channel activeChannel;

}

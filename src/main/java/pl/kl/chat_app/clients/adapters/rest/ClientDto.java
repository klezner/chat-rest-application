package pl.kl.chat_app.clients.adapters.rest;

import lombok.Data;

import java.io.Serializable;

@Data
public class ClientDto implements Serializable {

    String id;
    String name;
//    String activeChannel;

}

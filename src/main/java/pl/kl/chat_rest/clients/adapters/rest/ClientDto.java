package pl.kl.chat_rest.clients.adapters.rest;

import lombok.Data;

@Data
public class ClientDto {

    String id;
    String name;
    String activeChannel;

}

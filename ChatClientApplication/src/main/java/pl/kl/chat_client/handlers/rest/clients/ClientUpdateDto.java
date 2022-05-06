package pl.kl.chat_client.handlers.rest.clients;

import lombok.Builder;

import java.io.Serializable;

@Builder
public class ClientUpdateDto implements Serializable {

    public String name;
    public String activeChannel;

}

package pl.kl.chat_client.handlers.rest.clients;

import lombok.Builder;

import java.io.Serializable;

@Builder
public class ClientCreateDto implements Serializable {

    public String name;

}

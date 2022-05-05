package pl.kl.chat_client.clients;

import lombok.Builder;

import java.io.Serializable;

@Builder
public class ClientCreateDto implements Serializable {

    public String name;

}

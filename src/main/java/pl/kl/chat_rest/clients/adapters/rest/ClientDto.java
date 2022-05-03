package pl.kl.chat_rest.clients.adapters.rest;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ClientDto {

    String id;
    @NotNull
    String name;
    String activeChannel;

}

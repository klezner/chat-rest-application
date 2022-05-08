package pl.kl.chat.clients.adapters.rest;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ClientCreateDto {

    @NotNull
    String name;

}

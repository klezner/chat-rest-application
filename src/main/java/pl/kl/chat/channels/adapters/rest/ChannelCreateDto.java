package pl.kl.chat.channels.adapters.rest;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ChannelCreateDto {

    @NotNull
    String name;

}

package pl.kl.chat.channels.adapters.rest;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ChannelUpdateDto {

    @NotNull
    String name;
    @NotNull
    String clientName;

}

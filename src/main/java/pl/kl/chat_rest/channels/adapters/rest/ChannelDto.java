package pl.kl.chat_rest.channels.adapters.rest;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
public class ChannelDto {

    String id;
    @NotNull
    String name;
    Set<String> clients;
}

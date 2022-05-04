package pl.kl.chat_rest.channels.adapters.rest;

import lombok.Data;

import java.util.Set;

@Data
public class ChannelDto {

    String id;
    String name;
    Set<String> clients;
}

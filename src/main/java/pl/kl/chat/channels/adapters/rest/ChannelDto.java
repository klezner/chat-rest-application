package pl.kl.chat.channels.adapters.rest;

import lombok.Data;

import java.util.Set;

@Data
public class ChannelDto {

    String id;
    String name;
    Set<String> clients;
}

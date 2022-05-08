package pl.kl.chat.handlers.rest.channels;

import lombok.Builder;

import java.io.Serializable;

@Builder
public class ChannelCreateDto implements Serializable {

    public String name;

}

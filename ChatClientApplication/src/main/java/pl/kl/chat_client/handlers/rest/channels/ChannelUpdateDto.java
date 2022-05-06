package pl.kl.chat_client.handlers.rest.channels;

import lombok.Builder;

import java.io.Serializable;

@Builder
public class ChannelUpdateDto implements Serializable {

    public String name;
    public String clientName;

}

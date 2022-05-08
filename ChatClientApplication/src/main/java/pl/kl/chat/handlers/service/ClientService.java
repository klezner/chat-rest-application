package pl.kl.chat.handlers.service;

import java.io.IOException;

public interface ClientService {

    void loginClient() throws IOException;

    void handleLogout();

    void handleHelp();

    void handleAboutMe();

    void handleAllUsernamesGet();

    void handleAllChannelsGet();

    void handleChannelHistory();

    void handleChannelUsernamesGet();

    void handleChannelCreateOrJoin(String channelName);

    void handleInvalidChannelAction();

    void handleInvalidAction();

}

package pl.kl.chat_client;

import lombok.SneakyThrows;
import pl.kl.chat_client.ui.UserInterface;

public class ChatClientApplication {

    @SneakyThrows
    public static void main(String[] args) {

        final ChatClient chatClient = new ChatClient();

        final UserInterface userInterface = new UserInterface(chatClient);

        userInterface.loginUser();

        userInterface.handleUser();
    }

}

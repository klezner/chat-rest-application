package pl.kl.chat;

import lombok.SneakyThrows;
import pl.kl.chat.ui.UserInterface;

public class ChatClientApplication {

    @SneakyThrows
    public static void main(String[] args) {

        final ChatClient chatClient = new ChatClient();

        final UserInterface userInterface = new UserInterface(chatClient);

        userInterface.loginUser();

        userInterface.handleUser();
    }

}

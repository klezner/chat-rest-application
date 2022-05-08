package pl.kl.chat.ui;

import pl.kl.chat.ChatClient;
import pl.kl.chat.handlers.service.ChatClientService;
import pl.kl.chat.handlers.service.ClientService;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class MainUserInterfaceFactory implements UserInterfaceFactory {

    @Override
    public BufferedReader createBufferedReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public PrintWriter createPrintWriter() {
        return new PrintWriter(System.out, true);
    }

    @Override
    public ClientService createClientService(ChatClient chatClient, BufferedReader reader, PrintWriter printer) {
        return new ChatClientService(chatClient, reader, printer);
    }

}

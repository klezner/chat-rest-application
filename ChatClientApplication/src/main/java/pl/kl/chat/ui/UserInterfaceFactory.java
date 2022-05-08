package pl.kl.chat.ui;

import pl.kl.chat.ChatClient;
import pl.kl.chat.handlers.service.ClientService;

import java.io.BufferedReader;
import java.io.PrintWriter;

public interface UserInterfaceFactory {

    BufferedReader createBufferedReader();

    PrintWriter createPrintWriter();

    ClientService createClientService(ChatClient chatClient, BufferedReader reader, PrintWriter printer);

}

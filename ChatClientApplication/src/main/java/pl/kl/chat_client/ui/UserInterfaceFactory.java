package pl.kl.chat_client.ui;

import pl.kl.chat_client.ChatClient;
import pl.kl.chat_client.handlers.service.ClientService;

import java.io.BufferedReader;
import java.io.PrintWriter;

public interface UserInterfaceFactory {

    BufferedReader createBufferedReader();

    PrintWriter createPrintWriter();

    ClientService createClientService(ChatClient chatClient, BufferedReader reader, PrintWriter printer);

}

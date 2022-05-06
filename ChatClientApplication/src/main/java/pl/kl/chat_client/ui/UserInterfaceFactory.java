package pl.kl.chat_client.ui;

import java.io.BufferedReader;
import java.io.PrintWriter;

public interface UserInterfaceFactory {

    BufferedReader createBufferedReader();

    PrintWriter createPrintWriter();

}

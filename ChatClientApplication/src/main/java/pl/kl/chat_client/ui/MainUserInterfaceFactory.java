package pl.kl.chat_client.ui;

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

}

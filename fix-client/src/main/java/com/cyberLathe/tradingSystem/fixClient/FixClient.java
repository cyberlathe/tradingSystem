package com.cyberLathe.tradingSystem.fixClient;

import quickfix.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FixClient {
    private Initiator initiator;
    private String sessionConfigFile;

    public FixClient(String fileName) {
        sessionConfigFile = fileName;
    }

    public void startClients() throws FileNotFoundException, ConfigError {
        boolean logHeartbeats = Boolean.valueOf(System.getProperty("logHeartbeats", "true"));
        SessionSettings settings = new SessionSettings(new FileInputStream(sessionConfigFile));
        FIXClientApplication fixClientApplication = new FIXClientApplication();
        MessageStoreFactory messageStoreFactory = new FileStoreFactory(settings);
        LogFactory logFactory = new ScreenLogFactory(true, true, true, logHeartbeats);
        MessageFactory messageFactory = new DefaultMessageFactory();

        Initiator initiator = new SocketInitiator(fixClientApplication, messageStoreFactory, settings, logFactory,
                messageFactory);

        initiator.start();

        System.out.println("a. Type QUIT to exit\nb. BUY to send sample buy msg\n" +
                "c. SELL to send sample sell msg\n" +
                "d. Paste sample FIX message and press ENTER to send :");

        while (true) {
            Scanner scanner = new Scanner(System.in);
            String inputString = scanner.nextLine();
            if (inputString.equals("QUIT")) {
                java.lang.System.exit(0);
            }

            fixClientApplication.ProcessMessage(inputString);
        }
    }
}

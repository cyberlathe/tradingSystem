package com.cyberLathe.tradingSystem.fixClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import quickfix.*;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        if (args.length != 1) return;
        String fileName = args[0];

        SessionSettings settings = new SessionSettings(new FileInputStream(fileName));

        boolean logHeartbeats = Boolean.valueOf(System.getProperty("logHeartbeats", "true"));

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

        while(true) {
            Scanner scanner = new Scanner( System. in);
            String inputString = scanner. nextLine();
            if(inputString.equals("QUIT")) {
                java.lang.System.exit(0);
            }

            fixClientApplication.ProcessMessage(inputString);
        }


        //shutdownLatch.await();
    }

}

package com.cyberLathe.tradingSystem.fixClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        final Logger logger = LoggerFactory.getLogger(FIXClient.class);
        if (args.length != 1) {
            logger.error("Invalid arguments. Requires path of Session config file");
            return;
        }

        FIXClient fixClient = new FIXClient(args[0]);
        fixClient.logon();

        while(true) {
            System.out.println("a. Type QUIT to exit\nb. BUY to send sample buy msg\n " +
                    "c. SELL to send sample sell msg\n" +
                    "d. Paste sample FIX message and press ENTER to send :");

            Scanner scanner = new Scanner( System. in);
            String inputString = scanner. nextLine();
            fixClient.ProcessMessage(inputString);
        }


        //shutdownLatch.await();
    }

}

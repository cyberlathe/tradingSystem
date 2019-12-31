package com.cyberLathe.tradingSystem.fixServer;
import quickfix.*;
import java.io.FileInputStream;

public class Main {
    public static void main(String args[]) throws Exception {
        if (args.length != 1) return;
        String fileName = args[0];

        FixServer fixServer = new FixServer(fileName);
        fixServer.startServer();
    }
}

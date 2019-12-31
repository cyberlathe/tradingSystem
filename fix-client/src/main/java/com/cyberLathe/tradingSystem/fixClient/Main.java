package com.cyberLathe.tradingSystem.fixClient;

public class Main {
    public static void main(String[] args) throws Exception {
        if (args.length != 1) return;
        String fileName = args[0];

        FixClient fixClient = new FixClient(fileName);
        fixClient.startClients();
    }
}

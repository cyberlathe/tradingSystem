package com.cyberLathe.tradingSystem.fixServer;
import quickfix.*;
import java.io.FileInputStream;

public class FIXServer {
    public static void main(String args[]) throws Exception {
        if (args.length != 1) return;
        String fileName = args[0];

        SessionSettings settings = new SessionSettings(new FileInputStream(fileName));
        MessageStoreFactory storeFactory = new FileStoreFactory(settings);
        LogFactory logFactory = new FileLogFactory(settings);
        MessageFactory messageFactory = new DefaultMessageFactory();

        quickfix.Application application = new FIXApplication();

        Acceptor acceptor = new SocketAcceptor
                (application, storeFactory, settings, logFactory, messageFactory);
        acceptor.start();

        //acceptor.stop();
    }
}

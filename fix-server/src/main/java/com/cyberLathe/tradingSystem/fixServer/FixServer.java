package com.cyberLathe.tradingSystem.fixServer;

import quickfix.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class FixServer {
    private String sessionConfigFile;
    private Acceptor acceptor;

    public FixServer(String fileName) {
        sessionConfigFile = fileName;
    }

    public void startServer() throws FileNotFoundException, ConfigError {
        SessionSettings settings = new SessionSettings(new FileInputStream(sessionConfigFile));
        MessageStoreFactory storeFactory = new FileStoreFactory(settings);
        LogFactory logFactory = new FileLogFactory(settings);
        MessageFactory messageFactory = new DefaultMessageFactory();

        quickfix.Application application = new FIXServerApplication();

        Acceptor acceptor = new SocketAcceptor
                (application, storeFactory, settings, logFactory, messageFactory);
        acceptor.start();
    }

    public void stopServer() {
        acceptor.stop();
    }
}

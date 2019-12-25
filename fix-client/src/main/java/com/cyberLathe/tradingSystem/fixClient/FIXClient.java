package com.cyberLathe.tradingSystem.fixClient;

import java.io.FileInputStream;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import quickfix.DefaultMessageFactory;
import quickfix.FileStoreFactory;
import quickfix.Initiator;
import quickfix.LogFactory;
import quickfix.MessageFactory;
import quickfix.MessageStoreFactory;
import quickfix.ScreenLogFactory;
import quickfix.Session;
import quickfix.SessionID;
import quickfix.SessionSettings;
import quickfix.SocketInitiator;
import quickfix.fix44.NewOrderSingle;

public class FIXClient {
//    private static final CountDownLatch shutdownLatch = new CountDownLatch(1);

    private static final Logger logger = LoggerFactory.getLogger(FIXClient.class);
    private Initiator initiator = null;

    public FIXClient(String configFile) throws Exception {
        InputStream inputStream = new FileInputStream(configFile);

        SessionSettings settings = new SessionSettings(inputStream);
        inputStream.close();

        boolean logHeartbeats = Boolean.valueOf(System.getProperty("logHeartbeats", "true"));

        FIXClientApplication application = new FIXClientApplication();
        MessageStoreFactory messageStoreFactory = new FileStoreFactory(settings);
        LogFactory logFactory = new ScreenLogFactory(true, true, true, logHeartbeats);
        MessageFactory messageFactory = new DefaultMessageFactory();

        initiator = new SocketInitiator(application, messageStoreFactory, settings, logFactory,
                messageFactory);
    }

    public synchronized void logon() {
        try {
            initiator.start();
        } catch (Exception e) {
            logger.error("Logon failed", e);
        }
    }

    public void logout() {
        for (SessionID sessionId : initiator.getSessions()) {
            Session.lookupSession(sessionId).logout("user requested");
        }
    }

//    public void stop() {
//        shutdownLatch.countDown();
//    }


    public void ProcessMessage(String inputString) {
        switch(inputString) {
            case "BUY":
                NewOrderSingle newOrderSingle = new NewOrderSingle();
                break;
            case "SELL":
                break;
            case "QUIT":
                break;
            default:

        }

    }
}

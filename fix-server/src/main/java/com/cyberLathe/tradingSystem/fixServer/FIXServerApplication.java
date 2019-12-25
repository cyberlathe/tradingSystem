package com.cyberLathe.tradingSystem.fixServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import quickfix.*;

public class FIXServerApplication implements quickfix.Application {
    public static final Logger logger = LoggerFactory.getLogger(Thread.currentThread().getClass().getName());

    public void onCreate(SessionID sessionID) {
        logger.info("Session Created with sessionId: {}", sessionID);
    }

    public void onLogon(SessionID sessionID) {
        logger.info("Logged into session with sessionId: {}", sessionID);
    }

    public void onLogout(SessionID sessionID) {
        logger.info("Logged out of sessionId: {}", sessionID);
    }

    public void toAdmin(Message message, SessionID sessionID) {

    }

    public void fromAdmin(Message message, SessionID sessionID) throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue, RejectLogon {

    }

    public void toApp(Message message, SessionID sessionID) throws DoNotSend {

    }

    public void fromApp(Message message, SessionID sessionID) throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue, UnsupportedMessageType {
        logger.info("Message Received from SessionID: {}, Message: {}", sessionID, message);
    }
}

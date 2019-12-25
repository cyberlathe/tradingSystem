package com.cyberLathe.tradingSystem.fixClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import quickfix.*;
import quickfix.field.*;

public class FIXClientApplication implements quickfix.Application {
    private SessionID sessionId;
    private int messageId = 1;

    public static final Logger logger = LoggerFactory.getLogger(Thread.currentThread().getClass().getName());

    public void onCreate(SessionID sessionID) {
        logger.info("Session Created with sessionId: {}", sessionID);
    }

    public void onLogon(SessionID sessionID) {
        this.sessionId = sessionID;
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

    }

    private void send(quickfix.Message message, SessionID sessionID) {
        try {
            Session.sendToTarget(message, sessionID);
        } catch (SessionNotFound e) {
            logger.error("Unable to send message", e);
        }
    }

    public void sendSampleBuyOrder() {
        if (sessionId.getBeginString().equals(FixVersions.BEGINSTRING_FIX44)) {
            quickfix.fix44.NewOrderSingle newOrderSingle = new quickfix.fix44.NewOrderSingle(
                    new ClOrdID(Integer.toString(messageId++)), new Side(Side.BUY),
                    new TransactTime(), new OrdType(OrdType.MARKET));
            newOrderSingle.set(new OrderQty(100));
            newOrderSingle.set(new Symbol("CYBLAT"));
            newOrderSingle.set(new HandlInst('1'));
            send(newOrderSingle, sessionId);
        }
    }

    public void sendSampleSellOrder() {
        if (sessionId.getBeginString().equals(FixVersions.BEGINSTRING_FIX44)) {
            quickfix.fix44.NewOrderSingle message = new quickfix.fix44.NewOrderSingle(
                    new ClOrdID(Integer.toString(messageId++)), new Side(Side.SELL),
                    new TransactTime(), new OrdType(OrdType.MARKET));
            message.set(new OrderQty(100));
            message.set(new Symbol("CYBLAT"));
            message.set(new HandlInst('1'));
            send(message, sessionId);
        }
    }

    public void AmendQuantity() {
        //amend quantity for previous order
        if (sessionId.getBeginString().equals(FixVersions.BEGINSTRING_FIX44)) {
            quickfix.fix44.OrderCancelReplaceRequest message = new quickfix.fix44.OrderCancelReplaceRequest();
            message.set(new OrigClOrdID(Integer.toString(messageId)));
            message.set(new ClOrdID(Integer.toString(messageId++)));
            message.set(new Side(Side.BUY));
            message.set(new OrderQty(200));
            message.set(new TransactTime());
            message.set(new Symbol("CYBLAT"));
            message.set(new OrdType(OrdType.MARKET));
            send(message, sessionId);
        }
    }

    public void CancelOrder() {
        //Cancel previous order
        if (sessionId.getBeginString().equals(FixVersions.BEGINSTRING_FIX44)) {
            quickfix.fix44.OrderCancelRequest message = new quickfix.fix44.OrderCancelRequest();
            message.set(new OrigClOrdID(Integer.toString(messageId)));
            message.set(new ClOrdID(Integer.toString(messageId++)));
            message.set(new TransactTime());
            message.set(new Side(Side.BUY));
            message.set(new Symbol("CYBLAT"));
            send(message, sessionId);
        }
    }

    public void ProcessMessage(String inputString) {
        switch(inputString) {
            case "BUY":
                sendSampleBuyOrder();
                break;
            case "SELL":
                sendSampleSellOrder();
                break;
            case "AMENDQTY":
                AmendQuantity();
                break;
            case "CANCEL":
                CancelOrder();
                break;
            default:
                break;
        }

    }
}

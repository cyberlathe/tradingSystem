package com.cyberlathe.transactions;

public class OrderSingle extends Transaction {

    public enum Side {
        BUY,
        SELL
    }
    public enum Type {
        MARKET,
        LIMIT
    }

    public enum TIF {
        DAY,
        GTC
    }

    public enum Status {
        NEW,
        REPLACED,
        CANCELLED,
        REJECTED
    }

    private String symbol = null;
    private int quantity = 0;
    private int executed = 0;
    private Side side = Side.BUY;
    private Type type = Type.MARKET;
    private TIF tif = TIF.DAY;
    private double avgPrice = 0.0;
    private double price = 0.0;
    private Status status = Status.NEW;
    private String message = null;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getExecuted() {
        return executed;
    }

    public void setExecuted(int executed) {
        this.executed = executed;
    }

    public Side getSide() {
        return side;
    }

    public void setSide(Side side) {
        this.side = side;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public TIF getTif() {
        return tif;
    }

    public void setTif(TIF tif) {
        this.tif = tif;
    }

    public double getAvgPrice() {
        return avgPrice;
    }

    public void setAvgPrice(double avgPrice) {
        this.avgPrice = avgPrice;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

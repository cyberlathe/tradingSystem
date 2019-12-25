package com.cyberlathe.transactions;

public class Transaction {
    protected String _id;
    private static int counter = 1;

    public Transaction() {
        _id = generateId();
    }

    private String generateId() {
        return Long.toString(System.currentTimeMillis() + (counter++));
    }
}

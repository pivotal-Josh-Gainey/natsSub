package com.jgainey.natssub.demo;

import io.nats.client.AsyncSubscription;
import io.nats.client.Connection;
import io.nats.client.Nats;
import io.nats.client.Options;

import java.io.IOException;


public class NatsSub {

    Connection natsConnection;
    AsyncSubscription subscription;

    public NatsSub() {
        try {
            natsConnection = initConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Connection initConnection() throws IOException {
        Options options = new Options.Builder()
                .errorCb(ex -> Utils.logError("Connection Exception: " + ex))
                .disconnectedCb(event -> Utils.logError("Channel disconnected: {}" + event.getConnection()))
                .reconnectedCb(event -> Utils.logError("Reconnected to server: {}" + event.getConnection()))
                .build();

        return Nats.connect(System.getenv("NATSAPI"), options);
    }

    public void subscribe(String subject){
        subscription = natsConnection
                .subscribe( subject, msg -> Utils.logInfo("Received message - Message Content: " + msg.toString()));

    }

    public void unSubscribe() {
        try {
            subscription.unsubscribe();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

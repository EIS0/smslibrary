package com.eis.smslibrary;

import com.eis.communication.Message;

/**
 * Generic interface of a listener for incoming messages as per the
 * <a href='https://refactoring.guru/design-patterns/observer'>Observer Design Pattern</a>
 * @param <T> The type of message to receive
 * @author Proposed by Marco Cognolato, agreed by all groups
 */
public interface ReceivedMessageListener<T extends Message> {

    /**
     * Called by SMSHandler whenever a message is received.
     * @param message The message received
     */
    void onMessageReceived(T message);
}
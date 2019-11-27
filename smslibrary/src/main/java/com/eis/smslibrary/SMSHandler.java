package com.eis.smslibrary;

import com.eis.communication.CommunicationHandler;
import com.eis.communication.Message;


public class SMSHandler implements CommunicationHandler<SMSMessage> {

    private SMSHandler instance;
    private ReceivedMessageListener<SMSMessage> listener;

    /**
     * Private constructor as per <a href='https://refactoring.guru/design-patterns/singleton'>Singleton Design Pattern</a>
     */
    private SMSHandler(){

    }

    /**
     * Implementation of a <a href='https://refactoring.guru/design-patterns/singleton'>Singleton Design Pattern</a>
     */
    public SMSHandler getInstance(){
        if(instance == null) instance = new SMSHandler();
        return instance;
    }

    /**
     * Sends a single message in the channel, message content must be shorter than the maximum channel message size
     * @param message message to be sent in the channel to a peer
     */
    @Override
    public void sendMessage(SMSMessage message) {
        //Test pull request
    }

    /**
     * Sets the listener observing for incoming SMSMessages
     * @param listener The listener of type SMSMessage
     */
    public void addReceiveListener(ReceivedMessageListener<SMSMessage> listener){

    }
}

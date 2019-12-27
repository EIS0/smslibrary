package com.eis.smslibrary;

import java.util.Comparator;

/**
 * Object associating to each part of a multi-part SMS text message:
 * - the corresponding Intent's action to be received by SMSDeliveredBroadcastReceiver or
 * SMSSentBroadcastReceiver;
 * - whether the Intent was received;
 *
 * @author Giovanni Velludo
 */
class SMSPart implements Comparable<SMSPart> {

    private final String message;
    private final String intentAction;
    private boolean received = false;

    /**
     * Creates a new triple, with field `received` set to false by default.
     *
     * @param message      the SMS part for which sent or delivered confirmation is needed.
     * @param intentAction name of the corresponding Intent's action.
     */
    SMSPart(String message, String intentAction) {
        this.message = message;
        this.intentAction = intentAction;
    }

    /**
     * @return a part of a multi-part SMS text message.
     */
    String getMessage() {
        return message;
    }

    /**
     * @return whether the Intent associated to this part was received.
     */
    boolean wasReceived() {
        return received;
    }

    /**
     * Method to be called when the Intent associated to this part is received.
     */
    void setReceived() {
        received = true;
    }

    @Override
    public int compareTo(SMSPart part) {
        return intentAction.compareTo(part.intentAction);
    }
}

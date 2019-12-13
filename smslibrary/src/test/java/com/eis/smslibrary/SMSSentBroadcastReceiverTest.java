package com.eis.smslibrary;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;

import com.eis.smslibrary.listeners.SMSDeliveredListener;
import com.eis.smslibrary.listeners.SMSSentListener;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class SMSSentBroadcastReceiverTest {

    @Mock SMSSentListener listener;
    private final SMSPeer VALID_PEER = new SMSPeer("+395554");
    private final String TEXT_MESSAGE = "Sentence to test";
    private final SMSMessage VALID_MESSAGE = new SMSMessage(VALID_PEER, TEXT_MESSAGE);
    private Context contextMock = mock(Context.class);

    @Test
    public void testConstructor_noErrors(){
        SMSSentBroadcastReceiver receiver = new SMSSentBroadcastReceiver(VALID_MESSAGE, listener);
    }

    @Test
    public void receiveMessage_okResult(){
        SMSSentBroadcastReceiver rec = spy(new SMSSentBroadcastReceiver(VALID_MESSAGE, listener));

        doReturn(Activity.RESULT_OK).when((BroadcastReceiver)rec).getResultCode();
        rec.onReceive(contextMock, null);
    }

    @Test
    public void receiveMessage_CanceledResult(){
        SMSSentBroadcastReceiver rec = spy(new SMSSentBroadcastReceiver(VALID_MESSAGE, listener));

        doReturn(Activity.RESULT_CANCELED).when((BroadcastReceiver)rec).getResultCode();
        rec.onReceive(contextMock, null);
    }

    @Test
    public void receiveMessage_verifyResultOkParameters(){
        SMSSentBroadcastReceiver rec = spy(new SMSSentBroadcastReceiver(VALID_MESSAGE, listener));
        doReturn(Activity.RESULT_OK).when((BroadcastReceiver)rec).getResultCode();
        rec.onReceive(contextMock, null);
        verify(listener, times(1))
                .onSMSSent(VALID_MESSAGE, SMSMessage.SentState.MESSAGE_SENT);
    }
}

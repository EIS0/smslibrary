package com.example.smslibproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.eis.smslibrary.SMSManager;
import com.eis.smslibrary.SMSMessage;
import com.eis.smslibrary.SMSPeer;

public class MainActivity extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback {

    public static final int RECEIVE_SMS_PERMISSION = 0;
    public static final int SEND_SMS_PERMISSION = 1;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == RECEIVE_SMS_PERMISSION && permissions[0].equals(Manifest.permission.RECEIVE_SMS))
            if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                //if permission to receive SMS was not granted, ask to grant it
                Toast.makeText(this, "You must grant permission to receive SMS", Toast.LENGTH_LONG).show();
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECEIVE_SMS}, RECEIVE_SMS_PERMISSION);
            }
        if (requestCode == SEND_SMS_PERMISSION && permissions[0].equals(Manifest.permission.SEND_SMS))
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
                //if permission to send SMS was granted, send the message
                this.sendMessage();
            else
                Toast.makeText(this, "You must grant permission to send SMS", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // check if RECEIVE_SMS permission was granted
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECEIVE_SMS}, RECEIVE_SMS_PERMISSION);
        }
    }

    public void sendButtonPressed(View view) {
        // check if SEND_SMS permission was granted
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, SEND_SMS_PERMISSION);
        }
        else
            this.sendMessage();
    }

    private void sendMessage() {
        EditText editText2 = findViewById(R.id.editText2);
        String text = editText2.getText().toString();
        EditText editText = findViewById(R.id.editText);
        String number = editText.getText().toString();
        SMSMessage message = new SMSMessage(new SMSPeer(number), text);
        SMSManager.getInstance().sendMessage(message);
    }
}

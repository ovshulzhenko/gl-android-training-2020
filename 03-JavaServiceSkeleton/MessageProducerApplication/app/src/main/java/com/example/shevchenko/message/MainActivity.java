package com.example.shevchenko.message;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.shevchenko.message.service.IMessageAidlInterface;

public class MainActivity extends AppCompatActivity {
    private final static String TAG = "MessageProducerApplication";
    private IMessageAidlInterface messageService;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            if (service != null) {
                messageService = IMessageAidlInterface.Stub.asInterface(service);
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button btnSend = (Button) findViewById(R.id.btnSend);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    EditText edtMessage = (EditText) findViewById(R.id.edtMessage);
                    String message = edtMessage.getText().toString();
                    messageService.produceMessage(message);
                } catch (RemoteException | NullPointerException e) {
                    Log.e(TAG, "Send is failed");
                }
            }
        });

        Intent intent = new Intent();
        intent.setClassName("com.example.shevchenko.message.service", "com.example.shevchenko.message.service.MessageService");
        try {
            bindService(intent, serviceConnection, BIND_AUTO_CREATE);
        } catch (SecurityException e) {
            Log.e(TAG, "bind to Message service failed by security reason");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(serviceConnection);
    }
}

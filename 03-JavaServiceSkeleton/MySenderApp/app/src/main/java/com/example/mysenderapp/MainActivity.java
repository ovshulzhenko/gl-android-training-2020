package com.example.mysenderapp;

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
import android.widget.Toast;

import com.example.mytestservice.IMyTestInterface;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    final static String TAG = "MySenderApp";
    private IMyTestInterface mService;

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            if (null != service) {
                mService = IMyTestInterface.Stub.asInterface(service);
            } else {
                Log.e(TAG, "Null Pointer Exception!");
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

        final EditText messageEdit = (EditText)findViewById(R.id.messageEdit);
        final Button sendButton = (Button)findViewById(R.id.sendButton);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = messageEdit.getText().toString();

                try {
                    mService.sendString(message);
                    Log.d(TAG, "Send message \"" + message + "\" to service. SUCCESS!");
                } catch (RemoteException e) {
                    Log.e(TAG, "Remote Exception! " + e.getMessage());
                } catch (NullPointerException e) {
                    Log.e(TAG, "Null Pointer Exception! " + e.getMessage());
                }
            }
        });

        Intent intent = new Intent();
        intent.setClassName("com.example.mytestservice", "com.example.mytestservice.MyService");
        try {
            bindService(intent, mConnection, BIND_AUTO_CREATE);
            Log.d(TAG, "Bind to service success!");
        } catch (SecurityException e) {
            Log.e(TAG, "Bind to service failed: " + e.getMessage());
        }
    }

    @Override
    protected void onDestroy() {
        unbindService(mConnection);
        Log.d(TAG, "Unbind from service success!");
        super.onDestroy();
    }
}

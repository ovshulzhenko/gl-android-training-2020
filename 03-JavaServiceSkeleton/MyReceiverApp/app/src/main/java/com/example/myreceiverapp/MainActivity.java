package com.example.myreceiverapp;

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

import com.example.mytestservice.IMyTestInterface;

public class MainActivity extends AppCompatActivity {

    final static String TAG = "MyReceiverApp";
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

        final EditText messageView = (EditText)findViewById(R.id.messageView);
        final Button receiveButton = (Button)findViewById(R.id.receiveButton);

        receiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String message = mService.receiveString();
                    Log.d(TAG, "Received message \"" + message + "\" from service. SUCCESS!");
                    messageView.setText(message);
                } catch (RemoteException e) {
                    Log.e(TAG, "Remote Exception! " + e.getMessage());
                    messageView.setText("Error");
                } catch (NullPointerException e) {
                    Log.e(TAG, "Null Pointer Exception! " + e.getMessage());
                    messageView.setText("Error");
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

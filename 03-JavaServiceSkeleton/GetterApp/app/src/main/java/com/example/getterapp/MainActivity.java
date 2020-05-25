package com.example.getterapp;

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

import com.example.myservice.IMyAidlInterface;

public class MainActivity extends AppCompatActivity {

    private IMyAidlInterface myService;
    private final String TAG = "GetterApp";

    ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            if (service != null) {
                myService = IMyAidlInterface.Stub.asInterface(service);
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

        final EditText edtX = (EditText) findViewById(R.id.edtX);
        final Button btnGET = (Button) findViewById(R.id.btnGet);
        btnGET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    edtX.setText(String.valueOf(myService.get()));
                } catch (RemoteException e) {
                    Log.e(TAG, "call get failed");
                }
            }
        });
        Intent intent = new Intent();
        intent.setClassName("com.example.myservice", "com.example.myservice.MyService");
        bindService(intent, mConnection, BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        unbindService(mConnection);
        super.onDestroy();
    }

}

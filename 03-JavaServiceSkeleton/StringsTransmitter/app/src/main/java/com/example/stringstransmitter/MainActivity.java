package com.example.stringstransmitter;

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

import com.example.stringservice.IStringAidlInterface;

public class MainActivity extends AppCompatActivity {

    private IStringAidlInterface mService;

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            if (service != null) {
                mService = IStringAidlInterface.Stub.asInterface(service);
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

        final EditText strField = (EditText)findViewById(R.id.strField);
        final Button transmitStr = (Button)findViewById(R.id.transmitStr);

        transmitStr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = strField.getText().toString();

                try {
                    mService.stringTransmit(input);
                } catch (RemoteException | NullPointerException e) {
                    Log.e("StringsTransmitter", "string transmit exception!\n");
                }
            }
        });

        Intent mIntent = new Intent();
        mIntent.setClassName("com.example.stringservice", "com.example.stringservice.StringService");
        bindService(mIntent, mConnection, BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy () {
        super.onDestroy();
        unbindService(mConnection);
    }
}

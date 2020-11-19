package com.example.writerapp;

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


import com.example.serviceapp.ISeviceApplInterface;

public class MainActivity extends AppCompatActivity {
    final String TAG  = "WriterApp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent service_internt = new Intent();
        service_internt.setClassName( "com.example.serviceapp", "com.example.serviceapp.ServiceImpl");

        if(false == bindService(service_internt, mConnection, BIND_AUTO_CREATE))
            Log.e(TAG, "failed to bind service!");


        final EditText edt = (EditText) findViewById(R.id.edtInput);
        final Button  btn = (Button) findViewById(R.id.btnSend);
        btn.setOnClickListener((View view)->{
            String data = null;

            try {
                data = edt.getText().toString();
                mService.write(data);
            } catch (RemoteException | NullPointerException e ) {
                Log.e(TAG, "invalidData");
            }
            Log.w(TAG, "data '" + data + "' sent to service");
        } );

    }

    private ISeviceApplInterface mService;

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            if(null != service)
                mService = ISeviceApplInterface.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
}
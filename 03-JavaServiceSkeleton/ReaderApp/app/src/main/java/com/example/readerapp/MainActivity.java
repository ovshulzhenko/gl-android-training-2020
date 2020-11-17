package com.example.readerapp;

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
import android.widget.TextView;

import com.example.serviceapp.ISeviceApplInterface;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    final String TAG = "ReaderApp";
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent service_intent = new Intent();
        service_intent.setClassName("com.example.serviceapp", "com.example.serviceapp.ServiceImpl");
        if(false == bindService(service_intent, mConnection, BIND_AUTO_CREATE ))
        {
            Log.e(TAG,"failed bind to service!" );
        }

        final TextView lbl = (TextView)findViewById(R.id.lblData);
        final Button btn = (Button) findViewById(R.id.btnReceive);

        btn.setOnClickListener((View view)->{
            String data;

            try {
                data = mService.read();
            }catch (RemoteException | NullPointerException ex)
            {
                Log.e(TAG, "failed to receive data" + ex);
                data = "INVALID DATA";
            }
            lbl.setText(data);
        });
    }


}
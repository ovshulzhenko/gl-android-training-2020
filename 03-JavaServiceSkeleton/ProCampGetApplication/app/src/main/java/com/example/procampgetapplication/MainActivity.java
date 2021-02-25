package com.example.procampgetapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.procampservice.IMyInterface;

public class MainActivity extends AppCompatActivity {
    final static String TAG = "";
    private IMyInterface mService;
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mService = IMyInterface.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText resOut = (EditText)findViewById(R.id.editTextResult);
        final Button btnGet = (Button)findViewById(R.id.btnGet);

        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String r = mService.getData();

                    resOut.setText("" + r);
                } catch (RemoteException | NullPointerException e)
                {
                    Log.e(TAG, "getData() call failed!");
                    resOut.setText("INV");
                }
            }
        });

        Intent intent = new Intent();
        intent.setClassName("com.example.procampservice", "com.example.procampservice.MyService");
        try {
            bindService(intent, mConnection, BIND_AUTO_CREATE);
        } catch (SecurityException e)
        {
            Log.e(TAG, "bindService() failed for security reasons");
        }
    }
}
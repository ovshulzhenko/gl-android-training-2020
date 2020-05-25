package com.example.floatviewer;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.floatexchangeservice.IFloatExchangeService;

public class MainActivity extends AppCompatActivity {
    private IFloatExchangeService mService;

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            if (service != null)
            {
                mService = IFloatExchangeService.Stub.asInterface(service);
            }

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        final TextView txt = (TextView) findViewById(R.id.eShow);

        float num = 0;
        try {
            if (mService != null)
            {
                num = mService.getFloat();
            }

        } catch (RemoteException e) {
            e.printStackTrace();
        }
        txt.setText(Float.toString(num));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent();
        intent.setClassName("com.example.floatexchangeservice","com.example.floatexchangeservice.FloatExchangeService");

        try {
            if (bindService(intent, mConnection, BIND_AUTO_CREATE) == false)
            {
                AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
                dlgAlert.setMessage("Unable to bind service");
                dlgAlert.setTitle("Error");
                dlgAlert.setCancelable(true);
                dlgAlert.setPositiveButton("Ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                //dismiss the dialog
                            }
                        });
                dlgAlert.create().show();
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onDestroy () {
        super.onDestroy();
        unbindService(mConnection);
    }
}

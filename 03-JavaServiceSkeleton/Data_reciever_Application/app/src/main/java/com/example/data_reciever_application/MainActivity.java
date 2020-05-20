package com.example.data_reciever_application;

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
import android.widget.TextView;

import com.example.data_service.IDataAidlInterface;

public class MainActivity extends AppCompatActivity {
    final static String TAG = "Reciever_Application";

    private IDataAidlInterface mService;
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            if(service != null){
                mService = IDataAidlInterface.Stub.asInterface(service);
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

        final TextView data = (TextView) findViewById(R.id.out_txt);
        final Button read = (Button) findViewById(R.id.btn_read);

        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String out_data = "";
                try{
                    out_data = mService.get_data();
                    data.setText(out_data);
                }catch (RemoteException | NullPointerException e){
                    data.setText("call failed!!!");
                    Log.e(TAG,"call failed!!!");
                }
            }
        });

        Intent intent = new Intent();
        intent.setClassName("com.example.data_service","com.example.data_service.Data_Service");
        try{
            bindService(intent,mConnection,BIND_AUTO_CREATE);
        }catch (SecurityException e){
            Log.e(TAG,"bind to service failed by security");
        }

    }
}

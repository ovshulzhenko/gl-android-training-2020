package kh.glprocamp.getvalueandroidservice;

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

import kh.glprocamp.androidservice.IAndroidServiceInterface;

public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG = "GetServiceActivity:";
    private static final String SERVICE_PKG = "kh.glprocamp.androidservice";
    private static final String SERVICE = "kh.glprocamp.androidservice.AndroidService";
    private IAndroidServiceInterface mService;

    private ServiceConnection mConnention = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            if(service != null)
            {
                mService = IAndroidServiceInterface.Stub.asInterface(service);
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

        final Button btnGet = (Button)findViewById(R.id.btn_get);
        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView text = (TextView)findViewById(R.id.viewText);
                String str = "";
                try {
                    str  = mService.getValue();
                    Log.d(LOG_TAG, "Input string:"+str);
                } catch (Exception e) {

                }
                text.setText(str);
            }
        });


        Intent inten = new Intent();
        inten.setClassName(SERVICE_PKG,SERVICE);
        bindService(inten, mConnention, BIND_AUTO_CREATE);
    }

    protected void onDestroy() {
        super.onDestroy();
        unbindService(mConnention);
    }
}

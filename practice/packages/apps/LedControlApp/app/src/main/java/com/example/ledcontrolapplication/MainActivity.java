package com.example.ledcontrolapplication;

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

import com.example.ledapplication.IMyAidlInterface;

public class MainActivity extends AppCompatActivity {
    final static String TAG = "MyLed";
    final static String WIFI_LED_PATH = "/sys/class/leds/wifi_active/brightness";
    final static String BT_LED_PATH = "/sys/class/leds/bt_active/brightness";
    final static String ULED1_LED_PATH = "/sys/class/leds/user_led1/brightness";
    final static String ULED2_LED_PATH = "/sys/class/leds/user_led2/brightness";
    final static String RESULT_SUCCESS_STR = "SUCCESS";
    final static String RESULT_FAIL_STR = "FAIL";

    final static int LED_OFF = 0;
    final static int LED_ON = 1;

    private IMyAidlInterface mService;
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mService = IMyAidlInterface.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText edtR = (EditText)findViewById(R.id.edtResult);
        final Button btnWifi = (Button)findViewById(R.id.btnWifiLed);
        btnWifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try
                {
                    int curr  = mService.getLedBrightness(WIFI_LED_PATH);

                    if (curr == LED_OFF)
                    {
                        mService.setLedBrightness(WIFI_LED_PATH, LED_ON);
                    }
                    else
                    {
                        mService.setLedBrightness(WIFI_LED_PATH, LED_OFF);
                    }

                    edtR.setText(RESULT_SUCCESS_STR);
                } catch (NumberFormatException | RemoteException | NullPointerException e)
                {
                    edtR.setText(RESULT_FAIL_STR);
                    Log.e(TAG, "calling add failed");
                }
            }
        });

        final Button btnBt = (Button)findViewById(R.id.btnBtLed);
        btnBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try
                {
                    int curr  = mService.getLedBrightness(BT_LED_PATH);

                    if (curr == LED_OFF)
                    {
                        mService.setLedBrightness(BT_LED_PATH, LED_ON);
                    }
                    else
                    {
                        mService.setLedBrightness(BT_LED_PATH, LED_OFF);
                    }

                    edtR.setText(RESULT_SUCCESS_STR);
                } catch (NumberFormatException | RemoteException | NullPointerException e)
                {
                    edtR.setText(RESULT_FAIL_STR);
                    Log.e(TAG, "calling add failed");
                }
            }
        });

        final Button btnU1LED = (Button)findViewById(R.id.btnU1Led);
        btnU1LED.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try
                {
                    int curr  = mService.getLedBrightness(ULED1_LED_PATH);

                    if (curr == LED_OFF)
                    {
                        mService.setLedBrightness(ULED1_LED_PATH, LED_ON);
                    }
                    else
                    {
                        mService.setLedBrightness(ULED1_LED_PATH, LED_OFF);
                    }

                    edtR.setText(RESULT_SUCCESS_STR);
                } catch (NumberFormatException | RemoteException | NullPointerException e)
                {
                    edtR.setText(RESULT_FAIL_STR);
                    Log.e(TAG, "calling add failed");
                }
            }
        });

        final Button btnU2LED = (Button)findViewById(R.id.btnULed2);
        btnU2LED.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try
                {
                    int curr  = mService.getLedBrightness(ULED2_LED_PATH);

                    if (curr == LED_OFF)
                    {
                        mService.setLedBrightness(ULED2_LED_PATH, LED_ON);
                    }
                    else
                    {
                        mService.setLedBrightness(ULED2_LED_PATH, LED_OFF);
                    }

                    edtR.setText(RESULT_SUCCESS_STR);
                } catch (NumberFormatException | RemoteException | NullPointerException e)
                {
                    edtR.setText(RESULT_FAIL_STR);
                    Log.e(TAG, "calling add failed");
                }
            }
        });

        Intent intent = new Intent();
        intent.setClassName("com.example.ledapplication", "com.example.ledapplication.LedService");
        try
        {
            bindService(intent, mConnection, BIND_AUTO_CREATE);
        } catch (SecurityException e)
        {
            Log.e(TAG, "bind to service failed by security");
        }

    }
}
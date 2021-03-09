package com.example.ledapplication;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import vendor.my.hardware.ledcontrol.V1_0.ILedControl;

public class LedService extends Service {
    ILedControl ledctrl;
    final static String TAG = "MyLed";

    public LedService() {
        Log.e(TAG, "creating Led service ");
    }

    IMyAidlInterface.Stub mService = new IMyAidlInterface.Stub() {
        @Override
        public int getLedBrightness(String led_id) throws RemoteException {
            try {

                return ledctrl.getLedBrightness(led_id);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return -1;
        }

        @Override
        public int setLedBrightness(String led_id, int val) throws RemoteException {
            try {
                return ledctrl.setLedBrightness(led_id, val);
            } catch (Exception e) {
                Log.e(TAG, "failed calling set led led_id =" + led_id);
                e.printStackTrace();
            }
            return -1;
        }


    };

    @Override
    public IBinder onBind(Intent intent) {
        return mService;
    }

    @Override
    public void onCreate() {
	try {
		ledctrl = ILedControl.getService(true);
	} catch (Exception e) {
		e.printStackTrace();
	}
    }
}

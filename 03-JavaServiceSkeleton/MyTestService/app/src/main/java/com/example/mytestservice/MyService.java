package com.example.mytestservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class MyService extends Service {
    public MyService() {
    }

    final static String TAG = "MyTestService";
    private String mLocalString = "";

    private IMyTestInterface.Stub mService = new IMyTestInterface.Stub() {
        @Override
        public void sendString(String someString) throws RemoteException {
            Log.d(TAG, "call sendString. Got message: " + someString);
            mLocalString = someString;
        }

        @Override
        public String receiveString() throws RemoteException {
            Log.d(TAG, "call receiveString. Try to send message \"" + mLocalString + "\"");
            return mLocalString;
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "Bind My Test Service.");
        return mService;
    }
}

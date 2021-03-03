package com.example.procampservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class MyService extends Service {

    private String mData = "Empty";
    public MyService() {
    }

    IMyInterface.Stub mService = new IMyInterface.Stub() {
        @Override
        public void setData(String inp) throws RemoteException {
            mData = inp;
        }

        @Override
        public String getData() throws RemoteException {
            return mData;
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        return mService;
    }
}
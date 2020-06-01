package com.example.myservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v4.os.IResultReceiver;

public class MyService extends Service {
    public MyService() {
    }

    private IMyAidlInterface.Stub myService = new IMyAidlInterface.Stub() {

        int tmp;

        @Override
        public void add(int x) throws RemoteException {
            tmp = x;
        }

        @Override
        public int get() throws RemoteException {
            return tmp;
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        return myService;
    }
}

package com.example.procampservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class MyService extends Service {
    public MyService() {
    }

    IMyInterface.Stub mService = new IMyInterface.Stub() {
        @Override
        public String getData() throws RemoteException {
            return "Hello from My Service";
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        return mService;
    }
}
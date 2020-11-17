package com.example.serviceapp;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class ServiceImpl extends Service {
    public ServiceImpl() {
    }
    private  ISeviceApplInterface.Stub mService = new ISeviceApplInterface.Stub(){
        private String data = null;

        @Override
        public String read() throws RemoteException {
            return data;
        }

        @Override
        public void write(String data) throws RemoteException {
            this.data = data;
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return mService;
    }
}
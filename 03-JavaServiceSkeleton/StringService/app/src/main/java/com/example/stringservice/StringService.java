package com.example.stringservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class StringService extends Service {
    public StringService() {
    }

    String str = "Empty\n";

    private IStringAidlInterface.Stub mService = new IStringAidlInterface.Stub() {
        @Override
        public String stringReceive() throws RemoteException {
            return str;
        }

        @Override
        public void stringTransmit(String aString) throws RemoteException {
            str = aString;
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        return mService;
    }
}

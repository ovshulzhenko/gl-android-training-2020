package com.example.floatexchangeservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class FloatExchangeService extends Service {
    public FloatExchangeService() {
    }

    private IFloatExchangeService.Stub mService = new IFloatExchangeService.Stub() {

        private float floatStorage;

        @Override
        public void setFloat(float aFloat) {
            floatStorage = aFloat;
        }

        @Override
        public float getFloat() {
            return floatStorage;
        }

    };

    @Override
    public IBinder onBind(Intent intent) {
        return mService;
    }
}

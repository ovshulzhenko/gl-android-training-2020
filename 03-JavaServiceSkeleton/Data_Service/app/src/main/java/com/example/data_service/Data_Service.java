package com.example.data_service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class Data_Service extends Service {
    String data_buf;

    public Data_Service() {
    }

    private IDataAidlInterface.Stub dataService = new IDataAidlInterface.Stub() {
        @Override
        public void set_data(String data) throws RemoteException {
            data_buf = "";
            data_buf = data;
        }

        @Override
        public String get_data() throws RemoteException {
            return data_buf;
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        return dataService;
    }
}

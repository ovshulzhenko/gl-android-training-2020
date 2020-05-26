package com.example.shevchenko.message.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class MessageService extends Service {
    private String message;
    private final static String TAG = "MessageService";

    public MessageService() {
    }

    private IMessageAidlInterface.Stub messageService = new IMessageAidlInterface.Stub() {
        @Override
        public void produceMessage(String text) throws RemoteException {
            Log.e(TAG, "Produce message = " + text);
            message = text;
            Log.e(TAG, "New message value = " + text);
        }

        @Override
        public String consumeMessage() throws RemoteException {
            Log.e(TAG, "Consume message = " + message);
            return message;
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        return messageService;
    }
}

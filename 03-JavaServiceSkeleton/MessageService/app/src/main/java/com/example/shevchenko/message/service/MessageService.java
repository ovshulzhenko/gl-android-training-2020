package com.example.shevchenko.message.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class MessageService extends Service {
    private String message;

    public MessageService() {
    }

    private IMessageAidlInterface.Stub messageService = new IMessageAidlInterface.Stub() {
        @Override
        public String produceMessage() throws RemoteException {
            return message;
        }

        @Override
        public void consumeMessage(String text) throws RemoteException {
            message = text;
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        return messageService;
    }
}

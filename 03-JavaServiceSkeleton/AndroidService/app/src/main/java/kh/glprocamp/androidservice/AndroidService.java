package kh.glprocamp.androidservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class AndroidService extends Service {

    private String mStorageStr = "";
    private static final String LOG_TAG = "ANDROID_SERVICE:";

    public AndroidService() {
    }

    IAndroidServiceInterface.Stub mService = new IAndroidServiceInterface.Stub() {
        @Override
        public void setValue(String value) throws RemoteException {
            Log.d(LOG_TAG, "--->setValue<---"+value);
            mStorageStr = value;
        }

        @Override
        public String getValue() throws RemoteException {
            Log.d(LOG_TAG, "--->getValue<---");
            return mStorageStr;
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
          return mService;
    }
}

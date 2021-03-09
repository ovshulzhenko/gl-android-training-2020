// IMyAidlInterface.aidl
package com.example.ledapplication;

// Declare any non-default types here with import statements

interface IMyAidlInterface {

    const int RESULT_ERROR = -1;
    const int RESULT_OK     = 0;
    int getLedBrightness(String led_id);
    int setLedBrightness(String led_id, int val);
}

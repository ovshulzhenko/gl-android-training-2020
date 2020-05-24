// IStringAidlInterface.aidl
package com.example.stringservice;

// Declare any non-default types here with import statements

interface IStringAidlInterface {

    String stringReceive();
    void stringTransmit(String aString);
}

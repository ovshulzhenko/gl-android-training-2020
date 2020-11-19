// ISeviceApplInterface.aidl
package com.example.serviceapp;

// Declare any non-default types here with import statements

interface ISeviceApplInterface {
    void write(in String data);
    String read();
}
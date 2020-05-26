// IDataAidlInterface.aidl
package com.example.data_service;

// Declare any non-default types here with import statements

interface IDataAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void set_data(String data);
    String get_data();
}

// IMessageAidlInterface.aidl
package com.example.shevchenko.message.service;

// Declare any non-default types here with import statements

interface IMessageAidlInterface {
    String produceMessage();
    void consumeMessage(String text);
}

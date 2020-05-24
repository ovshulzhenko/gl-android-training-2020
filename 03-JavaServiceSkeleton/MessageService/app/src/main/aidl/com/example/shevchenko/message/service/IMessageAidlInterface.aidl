// IMessageAidlInterface.aidl
package com.example.shevchenko.message.service;

// Declare any non-default types here with import statements

interface IMessageAidlInterface {
    void produceMessage(String text);
    String consumeMessage();
}

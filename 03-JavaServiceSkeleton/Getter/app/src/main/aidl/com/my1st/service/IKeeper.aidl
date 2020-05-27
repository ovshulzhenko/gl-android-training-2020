// IKeeper.aidl
package com.my1st.service;

// Declare any non-default types here with import statements

interface IKeeper {
    //void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,double aDouble, String aString);
    void    set(String data);   //saves string
    String  get();              //returns the saved string
}

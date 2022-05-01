// IMyAidlInterface.aidl
package com.bigimage.aidl;

// Declare any non-default types here with import statements

interface IBigImageInterface {
    void sendSall(in byte[]data);
    void sendLarge(in ParcelFileDescriptor pfd);
}
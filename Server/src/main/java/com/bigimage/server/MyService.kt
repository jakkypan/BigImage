package com.bigimage.server

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.os.ParcelFileDescriptor
import android.util.Log
import com.bigimage.aidl.IBig
import com.bigimage.aidl.IBigImageInterface
import java.io.FileInputStream

class MyService : Service() {
    private val mStub: IBigImageInterface.Stub = object : IBigImageInterface.Stub() {
        override fun sendSall(data: ByteArray?) {
            Log.e("111", "received sendSall")
            App.observable.notifyObservers(data)
        }

        override fun sendLarge(pfd: ParcelFileDescriptor?) {
            Log.e("111", "received sendLarge")
            val fileDescriptor = pfd?.fileDescriptor
            val fis = FileInputStream(fileDescriptor)
            App.observable.notifyObservers(fis.readBytes())
        }

    }

    override fun onCreate() {
        super.onCreate()

    }

    override fun onBind(intent: Intent): IBinder {
        Log.e("111", "onBind")
        val binder = intent.extras?.getBinder("test_bitmap")
        val stub = IBig.Stub.asInterface(binder)
        App.observable.notifyObservers(stub.bitmap)
        return mStub
    }
}
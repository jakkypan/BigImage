package com.bigimage.client

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.View
import android.widget.Toast
import com.bigimage.aidl.IBig
import com.bigimage.aidl.IBigImageInterface
import com.bigimage.client.MemoryFileHelper.buildMemoryFile

class MainActivity : AppCompatActivity() {
    private var mBinder: IBigImageInterface? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName, binder: IBinder) {
            mBinder = IBigImageInterface.Stub.asInterface(binder)
        }

        override fun onServiceDisconnected(name: ComponentName) {
            mBinder = null
        }
    }

    private fun bindService() {
        if (mBinder != null) {
            return
        }
        val i = Intent().apply {
            action = "android.intent.action.BIG_IMAGE"
            component = ComponentName("com.bigimage.server", "com.bigimage.server.MyService")
        }
        val b = Bundle()
        val image = BitmapFactory.decodeResource(resources, R.drawable.large)
//        b.putBinder("test_bitmap", ImageBinder(image)) TODO 不行，server端收不到ImageBinder数据
        b.putBinder("test_bitmap", object : IBig.Stub() {
            override fun getBitmap(): Bitmap {
                return image
            }
        })
        i.putExtras(b)
        val bindSucc = bindService(i, serviceConnection, Context.BIND_AUTO_CREATE)
        Log.e("111", "bindSucc: $bindSucc")
        Toast.makeText(this, "bindSucc: $bindSucc", Toast.LENGTH_LONG).show()
    }

    fun sendLarge(view: View) {
        val fd = buildMemoryFile(this, "large.png")
        mBinder?.sendLarge(fd)
    }

    fun sendSmall(view: View) {
        
        val byteArray = AssetUtils.openAssets(this, "small.jpg")
        mBinder?.sendSall(byteArray)
    }

    fun bindService(view: View) {
        bindService()
    }
}
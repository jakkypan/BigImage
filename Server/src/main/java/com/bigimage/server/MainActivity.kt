package com.bigimage.server

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.ImageView
import java.util.*

class MainActivity : AppCompatActivity(), Observer {
    lateinit var pic: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        pic = findViewById(R.id.pic)
        App.observable.addObserver(this)
    }

    override fun update(o: Observable?, bytes: Any?) {
        Log.e("111", "observer update")
        if (bytes is ByteArray) {
            if (bytes.isNotEmpty()) {
                val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
                if (bitmap != null) {
                    Handler(Looper.getMainLooper()).post {
                        pic.setImageBitmap(bitmap)
                    }
                }
            }
        } else if (bytes is Bitmap) {
            Handler(Looper.getMainLooper()).post {
                pic.setImageBitmap(bytes)
            }
        }
    }

    override fun onDestroy() {
        App.observable.deleteObserver(this)
        super.onDestroy()
    }
}
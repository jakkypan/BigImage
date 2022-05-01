package com.bigimage.server

import android.app.Application

/**
 *
 * @author hongchao.pan
 * created at 2022/4/23 10:03 下午
 */
class App : Application() {
    companion object {
        val observable = MyObservable()
    }
}
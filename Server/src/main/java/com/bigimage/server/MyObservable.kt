package com.bigimage.server

import java.util.*

/**
 *
 * @author hongchao.pan
 * created at 2022/4/23 11:36 下午
 */
class MyObservable : Observable() {
    override fun notifyObservers(arg: Any?) {
        setChanged()
        super.notifyObservers(arg)
    }

    override fun notifyObservers() {
        setChanged()
        super.notifyObservers()
    }
}
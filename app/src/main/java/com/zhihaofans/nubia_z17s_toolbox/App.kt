package com.zhihaofans.nubia_z17s_toolbox

import android.app.Application
import com.orhanobut.logger.*


/**
 *
 * @author zhihaofans
 * @date 2018/1/12
 */
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Logger.addLogAdapter(AndroidLogAdapter())
    }
}
package com.dudarenkavitali.jetsearchhelper.app

import android.app.Application
import com.dudarenkavitali.jetsearchhelper.di.AppComponent
import com.dudarenkavitali.jetsearchhelper.di.AppModule
import com.dudarenkavitali.jetsearchhelper.di.DaggerAppComponent

class App : Application() {

    companion object {
        lateinit var instance: App

        @JvmStatic
        lateinit var appComponent: AppComponent
    }

    init {
        instance = this
    }

    override fun onCreate() {

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
        super.onCreate()

    }

}
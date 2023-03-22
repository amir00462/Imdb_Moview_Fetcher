package ir.dunijet.learnpaging3.di

import android.app.Application

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()

//        startKoin {
//            androidContext(this@MyApp)
//            modules(myModules)
//        }

    }

}
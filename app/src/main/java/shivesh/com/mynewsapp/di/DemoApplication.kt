package shivesh.com.mynewsapp.di

import android.app.Application

/**
 * @author
 * Created by Shivesh K. Mehta on 16/09/18.
 */

class DemoApplication : Application(){
    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        initializeDagger()
    }

    fun initializeDagger() {

        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .roomModule(RoomModule())
                .remoteModule(RemoteModule()).build()
    }
}
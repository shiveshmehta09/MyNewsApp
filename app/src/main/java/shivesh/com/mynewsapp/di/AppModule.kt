package shivesh.com.mynewsapp.di

import android.content.Context
import dagger.Module
import dagger.Provides
import shivesh.com.mynewsapp.data.repository.DemoApiRepository
import shivesh.com.mynewsapp.ui.MainViewModelFactory
import javax.inject.Singleton

/**
 * @author
 * Created by Shivesh K. Mehta on 16/09/18.
 */

@Module
class AppModule(private val demoApplication: DemoApplication) {

    @Provides
    @Singleton
    fun provideContext(): Context = demoApplication

    @Provides
    @Singleton
    fun provideViewModelFactory(demoApiRepository: DemoApiRepository): MainViewModelFactory {
        return MainViewModelFactory(demoApiRepository)
    }

}

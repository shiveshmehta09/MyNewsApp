package shivesh.com.mynewsapp.di

import dagger.Component
import shivesh.com.mynewsapp.ui.DetailsActivity
import shivesh.com.mynewsapp.ui.MainActivity
import javax.inject.Singleton

/**
 * @author
 * Created by Shivesh K. Mehta on 16/09/18.
 */

@Component(modules = arrayOf(AppModule::class, RemoteModule::class, RoomModule::class, DemoRepositoryModule::class))
@Singleton
interface AppComponent {

    fun inject(mainActivity: MainActivity)

    fun inject(detailsActivity: DetailsActivity)
}
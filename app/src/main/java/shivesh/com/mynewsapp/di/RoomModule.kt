package shivesh.com.mynewsapp.di

import android.content.Context
import dagger.Module
import dagger.Provides
import shivesh.com.mynewsapp.data.room.RoomDataSource
import javax.inject.Singleton

/**
 * @author
 * Created by Shivesh K. Mehta on 16/09/18.
 */

@Module
class RoomModule {

    @Provides
    @Singleton
    fun provideRoomCurrencyDataSource(context: Context) =
            RoomDataSource.getInstance(context)
}
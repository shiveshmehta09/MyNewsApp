package shivesh.com.mynewsapp.data.room


import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

/**
 * @author
 * Created by Shivesh K. Mehta on 16/09/18.
 */

@Database(entities = arrayOf(CollectionsEntity::class), version = 1)
abstract class RoomDataSource : RoomDatabase() {

    abstract fun demoDao(): DemoCitiesDaos

    companion object {

        @Volatile
        private var INSTANCE: RoomDataSource? = null

        fun getInstance(context: Context): RoomDataSource =
                INSTANCE ?: synchronized(this) {
                    INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
                }

        private fun buildDatabase(context: Context) =
                Room.databaseBuilder(context.applicationContext,
                        RoomDataSource::class.java, RoomConfig.DATABASE_DEMO)
                        .build()
    }
}
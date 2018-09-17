package shivesh.com.mynewsapp.data.room

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import io.reactivex.Flowable

/**
 * @author
 * Created by Shivesh K. Mehta on 16/09/18.
 */

@Dao
interface DemoCitiesDaos {

    @Insert
    fun insertAll(collections: CollectionsEntity)

    @Query(RoomConfig.SELECT_COLLECTIONS)
    fun getAllCollections(): Flowable<List<CollectionsEntity>>
}
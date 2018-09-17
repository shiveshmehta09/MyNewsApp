package shivesh.com.mynewsapp.data.room

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * @author
 * Created by Shivesh K. Mehta on 16/09/18.
 */

@Entity(tableName = RoomConfig.TABLE_COLLECTIONS)
data class CollectionsEntity(
        @PrimaryKey(autoGenerate = true) val id: Int = 0,
        var type: String
        )

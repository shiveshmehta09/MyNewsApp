package shivesh.com.mynewsapp.data.room

/**
 * @author
 * Created by Shivesh K. Mehta on 16/09/18.
 */

class RoomConfig {
    companion object {

        const val DATABASE_DEMO = "collection.db"
        const val TABLE_COLLECTIONS= "collections"
        private const val SELECT_FROM = "SELECT * FROM "
        const val SELECT_COLLECTIONS = SELECT_FROM + TABLE_COLLECTIONS
    }
}
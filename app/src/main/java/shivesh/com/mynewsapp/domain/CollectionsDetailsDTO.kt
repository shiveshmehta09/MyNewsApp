package shivesh.com.mynewsapp.domain

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import org.parceler.Parcel
import org.parceler.ParcelConstructor
import shivesh.com.mynewsapp.data.room.RoomConfig

/**
 * @author
 * Created by Shivesh K. Mehta on 16/09/18.
 */

@Parcel(Parcel.Serialization.BEAN)
data class CollectionsDetailsDTO @ParcelConstructor constructor(var slug: String?,
                                                                @PrimaryKey
                                                                var name: String?,
                                                                var summary: String?,
                                                                var items: List<ItemDTO>?)

@Parcel(Parcel.Serialization.BEAN)
data class ItemDTO @ParcelConstructor constructor(var id: String?,
                                                  var type: String?,
                                                  var name: String?,
                                                  var url: String?,
                                                  var authorName: String?,
                                                  var headline: String?,
                                                  var slug: String?,
                                                  var summary: String?,
                                                  var heroImage: String?)
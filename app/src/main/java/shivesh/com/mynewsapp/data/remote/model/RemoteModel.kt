package shivesh.com.mynewsapp.data.remote.model

import com.google.gson.annotations.SerializedName

/**
 * @author
 * Created by Shivesh K. Mehta on 16/09/18.
 */

data class RemoteModel(
        @SerializedName("slug") var slug: String?,
        @SerializedName("name") var name: String?,
        @SerializedName("summary") var summary: String?,
        @SerializedName("items") var items: List<Item>?
)

data class Item(
        @SerializedName("id") var id: String?,
        @SerializedName("type") var type: String?,
        @SerializedName("name") var name: String?,
        @SerializedName("url") var url: String?,
        @SerializedName("story") var story: Story?
)

data class Story(
        @SerializedName("author-name") var authorName: String?,
        @SerializedName("headline") var headline: String?,
        @SerializedName("slug") var slug: String?,
        @SerializedName("summary") var summary: String?,
        @SerializedName("hero-image") var heroImage: String?
)
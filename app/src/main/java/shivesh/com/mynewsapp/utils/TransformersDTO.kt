package shivesh.com.mynewsapp.utils


import shivesh.com.mynewsapp.domain.CollectionsDetailsDTO
import shivesh.com.mynewsapp.domain.ItemDTO
import shivesh.com.mynewsapp.data.remote.model.RemoteModel
import java.util.*

/**
 * @author
 * Created by Shivesh K. Mehta on 16/09/18.
 */
object TransformersDTO {
    fun transformToCollectionsDetailsDTO(remoteModel: RemoteModel?): CollectionsDetailsDTO {

        val slug: String? = remoteModel?.slug
        val name: String? = remoteModel?.name
        val summary: String? = remoteModel?.summary
        val itemsList = ArrayList<ItemDTO>()
        remoteModel?.items?.forEach {
            itemsList.add(ItemDTO(it.id,
                    it.type,
                    it.name,
                    it.url, it.story?.authorName, it.story?.headline, it.story?.slug,
                    it.story?.summary, it.story?.heroImage))
        }
        return CollectionsDetailsDTO(
                slug = slug,
                name = name,
                summary = summary,
                items = itemsList
        )
    }
}
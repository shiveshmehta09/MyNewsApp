package shivesh.com.mynewsapp.data.repository

import io.reactivex.Single
import shivesh.com.mynewsapp.domain.CollectionsDetailsDTO

/**
 * @author
 * Created by Shivesh K. Mehta on 16/09/18.
 */

interface DemoApiRepository {

    fun getCollections(): Single<CollectionsDetailsDTO>

    fun getStory(slug:String): Single<CollectionsDetailsDTO>

}
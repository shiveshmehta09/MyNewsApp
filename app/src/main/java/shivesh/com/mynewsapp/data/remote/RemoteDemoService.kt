package shivesh.com.mynewsapp.data.remote

import io.reactivex.Single
import retrofit2.http.GET
import shivesh.com.mynewsapp.data.remote.model.RemoteModel

/**
 * @author
 * Created by Shivesh K. Mehta on 16/09/18.
 */

interface RemoteDemoService {

    @GET("collection")
    fun requestDemoCollections(): Single<RemoteModel>

    @GET("top-stories")
    fun requestDemoStory(): Single<RemoteModel>

    @GET("trending_now")
    fun requestDemoTrendingNow(): Single<RemoteModel>

    @GET("technology")
    fun requestDemoTechnology(): Single<RemoteModel>
}
package shivesh.com.mynewsapp.data.remote

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import shivesh.com.mynewsapp.data.remote.model.RemoteModel

/**
 * @author
 * Created by Shivesh K. Mehta on 16/09/18.
 */

interface RemoteDemoService {

    @GET("collection")
    fun requestDemoCollections(): Single<RemoteModel>

    @GET("{slug}")
    fun requestDemoStory(@Path("slug") slug: String): Single<RemoteModel>

}
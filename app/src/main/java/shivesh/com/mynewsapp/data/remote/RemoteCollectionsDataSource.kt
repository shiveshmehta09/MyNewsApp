package shivesh.com.mynewsapp.data.remote

import io.reactivex.Single
import shivesh.com.mynewsapp.data.remote.model.RemoteModel
import javax.inject.Inject

/**
 * @author
 * Created by Shivesh K. Mehta on 16/09/18.
 */

class RemoteCollectionsDataSource @Inject constructor(private val remoteDemoService: RemoteDemoService) {

    fun requestCollections(): Single<RemoteModel> =
            remoteDemoService.requestDemoCollections()

    fun getStory(): Single<RemoteModel> =
            remoteDemoService.requestDemoStory()

    fun getTechnology(): Single<RemoteModel> =
            remoteDemoService.requestDemoTechnology()

    fun getTrendingNow(): Single<RemoteModel> =
            remoteDemoService.requestDemoTrendingNow()
}
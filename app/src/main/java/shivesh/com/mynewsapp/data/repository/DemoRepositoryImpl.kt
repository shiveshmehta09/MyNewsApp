package shivesh.com.mynewsapp.data.repository

import io.reactivex.Single
import shivesh.com.mynewsapp.domain.CollectionsDetailsDTO
import shivesh.com.mynewsapp.data.remote.RemoteCollectionsDataSource
import shivesh.com.mynewsapp.data.remote.model.RemoteModel
import shivesh.com.mynewsapp.data.room.RoomDataSource
import shivesh.com.mynewsapp.utils.TransformersDTO
import java.security.PrivateKey
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author
 * Created by Shivesh K. Mehta on 16/09/18.
 */

@Singleton
class DemoRepositoryImpl @Inject constructor(
        private val remoteCollectionsDataSource: RemoteCollectionsDataSource,
        private val roomDataSource: RoomDataSource) : DemoApiRepository {

    override fun getStory(slug: String): Single<CollectionsDetailsDTO> {
        return remoteCollectionsDataSource.getStory(slug)
                .map { remoteModel: RemoteModel ->
                    TransformersDTO.transformToCollectionsDetailsDTO(
                            remoteModel
                    )
                }
                .retry(1)
    }

    override fun getCollections(): Single<CollectionsDetailsDTO> {
        return remoteCollectionsDataSource.requestCollections()
                .map { remoteModel: RemoteModel ->
                    TransformersDTO.transformToCollectionsDetailsDTO(
                            remoteModel
                    )

                }
                .retry(1)
    }

}


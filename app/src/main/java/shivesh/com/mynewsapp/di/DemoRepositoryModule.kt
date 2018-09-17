package shivesh.com.mynewsapp.di


import dagger.Module
import dagger.Binds
import shivesh.com.mynewsapp.data.repository.DemoApiRepository
import shivesh.com.mynewsapp.data.repository.DemoRepositoryImpl

/**
 * @author
 * Created by Shivesh K. Mehta on 16/09/18.
 */

@Module
abstract class DemoRepositoryModule {
    @Binds
    abstract fun bindsCollectionRepository(demoRepositoryImpl: DemoRepositoryImpl): DemoApiRepository
}
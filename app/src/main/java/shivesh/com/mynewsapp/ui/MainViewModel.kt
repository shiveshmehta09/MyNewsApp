package shivesh.com.mynewsapp.ui

import android.arch.lifecycle.ViewModel
import shivesh.com.mynewsapp.data.repository.DemoApiRepository

import javax.inject.Inject


/**
 * @author
 * Created by Shivesh K. Mehta on 16/09/18.
 */
class MainViewModel @Inject constructor(val demoCollectionRepository: DemoApiRepository) : ViewModel() {

    fun getCollection() = demoCollectionRepository.getCollections()

    fun getStory(slug:String) = demoCollectionRepository.getStory(slug)

}
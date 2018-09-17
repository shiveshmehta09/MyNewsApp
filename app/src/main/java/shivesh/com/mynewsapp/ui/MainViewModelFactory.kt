package shivesh.com.mynewsapp.ui

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import shivesh.com.mynewsapp.data.repository.DemoApiRepository
import javax.inject.Inject

/**
 * @author
 * Created by Shivesh K. Mehta on 16/09/18.
 */

class MainViewModelFactory @Inject constructor(val demoCollectionRepository: DemoApiRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(demoCollectionRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
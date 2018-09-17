package shivesh.com.mynewsapp.ui

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.Toast
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.jetbrains.anko.startActivity
import shivesh.com.mynewsapp.R
import shivesh.com.mynewsapp.di.DemoApplication
import shivesh.com.mynewsapp.domain.CollectionsDetailsDTO
import shivesh.com.mynewsapp.domain.ItemDTO
import shivesh.com.mynewsapp.ui.adapter.DemoCollectionAdapter
import shivesh.com.mynewsapp.utils.events.TypeSelectedEvent
import javax.inject.Inject

/**
 * @author
 * Created by Shivesh K. Mehta on 16/09/18.
 */

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: MainViewModelFactory
    private lateinit var viewModel: MainViewModel
    private var isConnectedToInternet: Boolean = false
    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DemoApplication.appComponent.inject(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)

        processRequestStartUI()
        setupCollectionsObserver()
    }

    private fun processRequestStartUI() {

        progressBar.visibility = View.VISIBLE
        recyclerViewCollections.visibility = View.GONE

    }

    private fun resolveRequestEndUI() {
        progressBar.visibility = View.GONE
        recyclerViewCollections.visibility = View.VISIBLE

    }


    private fun setupCollectionsObserver(): Disposable? {
        return viewModel.getCollection()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { collectionResponse: CollectionsDetailsDTO? ->
                            resolveRequestEndUI()
                            setupRecyclerView(collectionResponse?.items!!)
                        },
                        { throwable: Throwable? ->
                            resolveRequestEndUI()
                            if (!isConnectedToInternet) {
                                Toast.makeText(this, "No Internet", Toast.LENGTH_SHORT).show()
                            } else {
                                throwable?.printStackTrace()
                                Toast.makeText(this, "error in fetching", Toast.LENGTH_SHORT).show()
                            }
                        }
                )
    }


    private fun setupRecyclerView(collectionDetails: List<ItemDTO>) {
        val adapter: DemoCollectionAdapter? = DemoCollectionAdapter(collectionDetails)
        val mLayoutManager = LinearLayoutManager(applicationContext)
        recyclerViewCollections.setLayoutManager(mLayoutManager)
        recyclerViewCollections.setAdapter(adapter)
    }

    override fun onResume() {
        super.onResume()
        EventBus.getDefault().register(this)
    }

    override fun onPause() {
        EventBus.getDefault().unregister(this)
        super.onPause()
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    fun CollectionItemSelected(event: TypeSelectedEvent) {
        startActivity<DetailsActivity>("type" to event.type,
                "name" to event.name)

    }

    private fun setupInternetConnectionObserver(): Disposable {
        return ReactiveNetwork.observeInternetConnectivity()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { isConnected: Boolean? ->
                            isConnected?.let {
                                if (!isConnected)
                                    Toast.makeText(this, getString(R.string.user_has_lost_internet_connection_message), Toast.LENGTH_SHORT).show()
                                isConnectedToInternet = isConnected
                            }
                        },
                        { t: Throwable? ->
                            Log.v("ReactiveNetwork", t?.message)
                        }
                )
    }

    override fun onStart() {
        super.onStart()
        compositeDisposable.add(setupInternetConnectionObserver())

    }

}

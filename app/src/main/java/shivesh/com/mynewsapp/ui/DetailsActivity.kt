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
import kotlinx.android.synthetic.main.activity_details.*
import shivesh.com.mynewsapp.R
import shivesh.com.mynewsapp.di.DemoApplication
import shivesh.com.mynewsapp.domain.CollectionsDetailsDTO
import shivesh.com.mynewsapp.domain.ItemDTO
import shivesh.com.mynewsapp.ui.adapter.DetailsCollectionAdapter
import javax.inject.Inject

/**
 * @author
 * Created by Shivesh K. Mehta on 16/09/18.
 */

class DetailsActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: MainViewModelFactory
    private lateinit var viewModel: MainViewModel
    private var isConnectedToInternet: Boolean = false
    private var type: String? = null
    private var name: String? = null
    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        DemoApplication.appComponent.inject(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)

        if (intent.extras != null) {
            when {
                intent.extras.get("type") != null -> type = intent.extras.get("type") as String
            }
            when {
                intent.extras.get("name") != null -> name = intent.extras.get("name") as String
            }
        }

        if (type.equals("story") or name.equals("Top Stories") or name.equals("story")) {
            processRequestStartUI()
            setupStoryObserver()
        } else if (name.equals("Trending Now")) {
            processRequestStartUI()
            setupTrendingNowObserver()
        } else if (name.equals("Technology")) {
            processRequestStartUI()
            setupTechnologyObserver()
        }


    }


    private fun processRequestStartUI() {

        progressBar.visibility = View.VISIBLE
        recyclerViewDetails.visibility = View.GONE
    }

    private fun resolveRequestEndUI() {
        progressBar.visibility = View.GONE
        recyclerViewDetails.visibility = View.VISIBLE

    }


    private fun setupStoryObserver(): Disposable? {
        return viewModel.getStory()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { collectionResponse: CollectionsDetailsDTO? ->
                            resolveRequestEndUI()
                            txvToolbarTitle.text = collectionResponse?.name
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


    private fun setupTechnologyObserver(): Disposable? {
        return viewModel.getTechnology()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { collectionResponse: CollectionsDetailsDTO? ->
                            resolveRequestEndUI()
                            txvToolbarTitle.text = collectionResponse?.name
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


    private fun setupTrendingNowObserver(): Disposable? {
        return viewModel.getTrendingNow()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { collectionResponse: CollectionsDetailsDTO? ->
                            resolveRequestEndUI()
                            txvToolbarTitle.text = collectionResponse?.name
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
        val adapter: DetailsCollectionAdapter? = DetailsCollectionAdapter(collectionDetails)
        val mLayoutManager = LinearLayoutManager(applicationContext)
        recyclerViewDetails.setLayoutManager(mLayoutManager)
        recyclerViewDetails.setAdapter(adapter)
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
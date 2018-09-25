package shivesh.com.mynewsapp.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import org.greenrobot.eventbus.EventBus
import org.jetbrains.anko.sdk25.coroutines.onClick
import shivesh.com.mynewsapp.R
import shivesh.com.mynewsapp.domain.ItemDTO
import shivesh.com.mynewsapp.utils.events.TypeSelectedEvent
import shivesh.com.mynewsapp.utils.loadImageUrl

/**
 * @author
 * Created by Shivesh K. Mehta on 16/09/18.
 */

class DemoCollectionAdapter(val list: ArrayList<ItemDTO>) : RecyclerView.Adapter<DemoCollectionAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent?.context)
                .inflate(R.layout.item_home, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {

        return list.count()
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val item = list.get(position)

        if (item.name != null) {
           // EventBus.getDefault().post(TypeSelectedEvent(item.slug!!, position))
        }

        if (item.name != null) {
            holder?.imageCollection?.visibility = View.INVISIBLE
            holder?.textViewCollectionHeading?.visibility = View.VISIBLE
            holder?.textViewAuthorName?.visibility = View.GONE
            holder?.textViewHeadlines?.visibility = View.GONE
            holder?.textViewSummary?.visibility = View.GONE
            holder?.textViewCollection?.visibility = View.GONE
            holder?.textViewCollectionHeading?.text = item.name

        } else {
            holder?.textViewCollectionHeading?.visibility = View.GONE
            holder?.textViewCollection?.visibility = View.VISIBLE
            holder?.textViewAuthorName?.visibility = View.VISIBLE
            holder?.textViewHeadlines?.visibility = View.VISIBLE
            holder?.textViewSummary?.visibility = View.VISIBLE
            holder?.imageCollection?.visibility = View.VISIBLE
            holder?.imageCollection?.loadImageUrl(item.heroImage!!)
            holder?.textViewAuthorName?.text = item.authorName
            holder?.textViewHeadlines?.text = item.headline
            holder?.textViewSummary?.text = item.summary
            holder?.textViewCollection?.text = item.type

        }

    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var textViewAuthorName: TextView
        var textViewSummary: TextView
        var textViewCollection: TextView
        var textViewHeadlines: TextView
        var textViewCollectionHeading: TextView
        var imageCollection: ImageView


        init {
            textViewAuthorName = view.findViewById(R.id.txv_author_name)
            textViewSummary = view.findViewById(R.id.txv_summary)
            textViewCollection = view.findViewById(R.id.txv_type)
            textViewHeadlines = view.findViewById(R.id.txv_headline)
            textViewCollectionHeading = view.findViewById(R.id.txv_collection_heading)
            imageCollection = view.findViewById(R.id.imv_hero_image)
        }
    }
}
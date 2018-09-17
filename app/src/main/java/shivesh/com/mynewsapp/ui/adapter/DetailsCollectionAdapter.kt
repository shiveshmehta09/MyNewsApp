package shivesh.com.mynewsapp.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import org.greenrobot.eventbus.EventBus
import shivesh.com.mynewsapp.R
import shivesh.com.mynewsapp.domain.ItemDTO
import shivesh.com.mynewsapp.utils.events.TypeSelectedEvent
import shivesh.com.mynewsapp.utils.loadImageUrl
import java.util.*


/**
 * @author
 * Created by Shivesh K. Mehta on 16/09/18.
 */

class DetailsCollectionAdapter(val list: List<ItemDTO>) : RecyclerView.Adapter<DetailsCollectionAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent?.context)
                .inflate(R.layout.item_collection, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return list.count()
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val item = list.get(position)
        holder?.textViewCollection?.text = item.type
        holder?.textViewAuthorName?.text = item.authorName
        holder?.textViewHeadlines?.text = item.headline
        holder?.textViewSummary?.text = item.summary
        holder?.imageCollection?.loadImageUrl(item.heroImage!!)


    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var textViewAuthorName: TextView
        var textViewSummary: TextView
        var textViewCollection: TextView
        var textViewHeadlines: TextView
        var imageCollection: ImageView


        init {
            textViewAuthorName = view.findViewById(R.id.txv_author_name)
            textViewSummary = view.findViewById(R.id.txv_summary)
            textViewCollection = view.findViewById(R.id.txv_type)
            textViewHeadlines = view.findViewById(R.id.txv_headline)
            imageCollection = view.findViewById(R.id.imv_hero_image)
        }
    }
}
package shivesh.com.mynewsapp.utils

import android.text.TextUtils
import android.widget.ImageView
import com.squareup.picasso.Picasso
import shivesh.com.mynewsapp.R

fun ImageView.loadImageUrl(imageUrl: String, placeHolderResId: Int = 0, errorResId: Int = 0) {
    if (!TextUtils.isEmpty(imageUrl))
        Picasso.with(context)
                .load(imageUrl)
                .error(if (errorResId == 0) R.drawable.error_image else errorResId)
                .placeholder(if (placeHolderResId == 0) R.drawable.default_placeholder else placeHolderResId)
                .into(this)
    else
        Picasso.with(context)
                .load(R.drawable.default_placeholder)
                .into(this)
}
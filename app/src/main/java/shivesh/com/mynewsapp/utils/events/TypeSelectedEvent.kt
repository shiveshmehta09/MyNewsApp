package shivesh.com.mynewsapp.utils.events

import java.text.FieldPosition

/**
 * @author
 * Created by Shivesh K. Mehta on 16/09/18.
 */

data class TypeSelectedEvent(
        val slug: String,
        val position: Int
)
package winkhanh.com.todo

import android.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class ItemAdapter(val listOfItems: List<String>, val onLongClickListener: OnLongClickListener): RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    interface  OnLongClickListener{
        fun onItemLongClicked(position: Int)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView : TextView
        init {
            textView = itemView.findViewById(android.R.id.text1)

            textView.setOnLongClickListener {
                onLongClickListener.onItemLongClicked(adapterPosition)
                true
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val contactView = inflater.inflate(android.R.layout.simple_list_item_1, parent, false)
        // Return a new holder instance
        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val text = listOfItems[position]
        holder.textView.text=text
    }

    override fun getItemCount(): Int {
        return  listOfItems.size
    }
}
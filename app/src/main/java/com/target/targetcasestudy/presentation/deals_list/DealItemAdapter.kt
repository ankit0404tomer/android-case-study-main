package com.target.targetcasestudy.presentation.deals_list

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.target.targetcasestudy.R
import com.target.targetcasestudy.data.remote.dto.Deal


class DealItemAdapter(  private val itemClickListener: (Int) -> Unit) : RecyclerView.Adapter<DealItemViewHolder>() {

  private var dealsList: List<Deal> = ArrayList()

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DealItemViewHolder {
    val inflater = LayoutInflater.from(parent.context)
    val view = inflater.inflate(R.layout.deal_list_item, parent, false)
    return DealItemViewHolder(view)
  }


  override fun getItemCount(): Int {
    return dealsList.size
  }

  override fun onBindViewHolder(viewHolder: DealItemViewHolder, position: Int) {
    val item = dealsList[position]
    viewHolder.itemView.findViewById<TextView>(R.id.deal_list_item_title).text = item.title
    Glide
      .with(viewHolder.itemView.context)
      .load(item.imageUrl)
      .centerCrop()
      .into(viewHolder.itemView.findViewById<ImageView>(R.id.deal_list_item_image_view))

    viewHolder.itemView.findViewById<TextView>(R.id.deal_list_item_sale_price).text = item.salePrice?.displayString
    viewHolder.itemView.findViewById<TextView>(R.id.deal_list_item_price).text = " reg. "+item.regularPrice?.displayString
    viewHolder.itemView.findViewById<TextView>(R.id.deal_list_item_title).text = item.title

    viewHolder.itemView.setOnClickListener {
      item.id?.let { it1 -> itemClickListener(it1) }
    }
  }


  @SuppressLint("NotifyDataSetChanged")
  fun updateAdapterData(dealList: List<Deal>) {
    dealsList = dealList
    notifyDataSetChanged()
  }
}

class DealItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

}

interface OnItemClickListener {
  fun onItemClick(item: Deal) // Pass the clicked item data if needed
}
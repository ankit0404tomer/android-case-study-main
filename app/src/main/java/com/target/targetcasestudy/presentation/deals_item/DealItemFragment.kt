package com.target.targetcasestudy.presentation.deals_item

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide

import com.target.targetcasestudy.R


class DealItemFragment : Fragment() {

  private lateinit var viewModel: SharedViewModel

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_deal_item, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    // Initialize the ViewModel
    viewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

    // Observe the item data
    viewModel.selectedItem.observe(viewLifecycleOwner, Observer { item ->
      // Update UI with item data

      view.findViewById<TextView>(R.id.deal_list_item_title).text = item.title
      Glide
        .with(view.context)
        .load(item.imageUrl)
        .centerCrop()
        .into(view.findViewById<ImageView>(R.id.deal_list_item_image_view))

      view.findViewById<TextView>(R.id.deal_list_item_sale_price).text = item.salePrice?.displayString
      view.findViewById<TextView>(R.id.deal_list_item_price).text = " reg. "+item.regularPrice?.displayString
      view.findViewById<TextView>(R.id.deal_list_item_title).text = item.title
    })
  }
}

package com.target.targetcasestudy.presentation.deals_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.target.targetcasestudy.R
import com.target.targetcasestudy.data.remote.dto.Deal
import com.target.targetcasestudy.presentation.deals_item.DealItemFragment
import com.target.targetcasestudy.presentation.deals_item.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DealListFragment : Fragment() {

    private val viewModel: DealsListViewModel by viewModels()
    private lateinit var sharedViewModel: SharedViewModel

    private lateinit var adapter: DealItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_deal_list, container, false)

        adapter = DealItemAdapter(){ item ->
            viewModel.onItemClicked(item)
            viewModel.onItemClicked(null)
        }
        view.findViewById<RecyclerView>(R.id.recycler_view).layoutManager =
            LinearLayoutManager(requireContext())
        view.findViewById<RecyclerView>(R.id.recycler_view).adapter = adapter

        // Initialize the ViewModel
        sharedViewModel = ViewModelProvider(requireActivity())[SharedViewModel::class.java]

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObserver()

    }

    private fun initObserver() {
        viewModel.dealsListData.observe(viewLifecycleOwner) { result ->
            when {
                result.isLoading -> {
                    // Handle loading state
                }
                !result.deals.isNullOrEmpty() -> {
                // Handle case with deals
                    val adapter = view?.findViewById<RecyclerView>(R.id.recycler_view)?.adapter
                    if (adapter is DealItemAdapter) {
                        adapter.updateAdapterData(result.deals)
                    }
                }
                else -> {
                    // Handle case error
                }
            }

        }

        viewModel.dealListData.observe(viewLifecycleOwner) { result ->
            when {
                result.isLoading -> {
                    // Handle loading state
                }
                result.deals!=null -> {
                    // Handle case with deal
                    sharedViewModel.selectItem(result.deals)

                    // Manually handle fragment transactions
                    val detailFragment = DealItemFragment()
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.container, detailFragment)
                        .addToBackStack(null)
                        .commit()
                    viewModel.dealListData.removeObservers(getViewLifecycleOwner())
                }
                else -> {
                    // Handle case error
                }
            }

        }
    }


}

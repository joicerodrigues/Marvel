package com.example.marvel.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.VisibleForTesting
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.marvel.R
import com.example.marvel.databinding.FragmentMarvelListBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class MarvelListFragment : Fragment() {

    //@VisibleForTesting
   private val viewModel: MarvelViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMarvelListBinding.inflate(inflater)
        viewModel.getMarvelList()
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.recyclerView.adapter = MarvelListAdapter(MarvelListener { marvel ->
            viewModel.marvelClicked(marvel)
            findNavController()
                .navigate(R.id.action_FragmentMarvelListBinding_to_marvelDetailFragment)
        })
        val refreshLayout: SwipeRefreshLayout = binding.root.findViewById(
            R.id.SwipeRefresh
        )
        refreshLayout.setOnRefreshListener {
            refreshLayout.isRefreshing = false
            viewModel.getMarvelList()
            // SwipeRefreshLayout.isRefreshing = false   // reset the SwipeRefreshLayout
        }

        // Inflate the layout for this fragment
        return binding.root
    }

}
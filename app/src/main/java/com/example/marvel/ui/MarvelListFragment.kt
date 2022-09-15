package com.example.marvel.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.marvel.R
import com.example.marvel.databinding.FragmentMarvelListBinding


class MarvelListFragment : Fragment() {
    private val viewModel: MarvelViewModel by activityViewModels()

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

        // Inflate the layout for this fragment
        return binding.root
    }
}
package com.example.marvel.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.marvel.R
import com.example.marvel.databinding.FragmentMarvelDetailBinding

class MarvelDetailFragment(private val marvelDetailFragmentFactory: (() -> ViewModelProvider.Factory)? = null) : Fragment() {

   private val viewModel: MarvelViewModel by activityViewModels(factoryProducer =  marvelDetailFragmentFactory)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentMarvelDetailBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel


        var sendMarvel: Button = binding.root.findViewById(
            R.id.sendButton
        )
        sendMarvel.setOnClickListener {
            shareHero()
        }

        return binding.root

    }

    private fun shareHero(): Boolean {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, viewModel.marvel.value?.description)
            putExtra(Intent.EXTRA_TITLE, viewModel.marvel.value?.name)
            type = "text/plain"

        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
        return true
    }

}
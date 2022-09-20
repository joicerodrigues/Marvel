package com.example.marvel.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels

import com.example.marvel.databinding.FragmentMarvelDetailBinding
import com.example.marvel.network.Marvel

class MarvelDetailFragment : Fragment(){

    private val viewModel: MarvelViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

//        val imageView = findViewById<ImageView>(R.id.imageView)

//        imageView.load("https://www.example.com/image.jpg") {
//            crossfade(true)
//            placeholder(R.drawable.image)
//            transformations(CircleCropTransformation())
//        }


        val binding = FragmentMarvelDetailBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        return binding.root
    }

}

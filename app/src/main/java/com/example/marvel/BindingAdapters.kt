package com.example.marvel


import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.marvel.network.Marvel
import com.example.marvel.ui.MarvelApiStatus
import com.example.marvel.ui.MarvelListAdapter

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Marvel>?) {
    val adapter = recyclerView.adapter as MarvelListAdapter
    adapter.submitList(data)
}

@BindingAdapter("apiStatus")
fun bindStatus(statusImageView: ImageView, status: MarvelApiStatus?) {
    when(status) {
        MarvelApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        MarvelApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
        MarvelApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
    }
}

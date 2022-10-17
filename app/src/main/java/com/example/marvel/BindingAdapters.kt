package com.example.marvel


import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.marvel.network.MarvelCharacters
import com.example.marvel.ui.MarvelApiStatus
import com.example.marvel.ui.MarvelListAdapter

@BindingAdapter("imageUrl") // instrui a vinculação de dados a executar esse adaptador de vinculação quando um item da visualização tiver o atributo imageUrl
fun bindImage(
    imgView: ImageView,
    imgUrl: String?
) { // recebendo imageview e string como paramentros
    imgUrl?.let { //usando operador de chamada segura
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        imgView.load(imgUri){
            placeholder(R.drawable.loading_animation)
            error(R.drawable.ic_broken_image)
        }
    }
}

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<MarvelCharacters>?) {
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
        MarvelApiStatus.NETWORK_ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        MarvelApiStatus.API_ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_baseline_error_24)
        }
        else ->{
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
    }
}

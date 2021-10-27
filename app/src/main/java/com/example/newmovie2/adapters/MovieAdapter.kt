package com.example.newmovie2.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newmovie2.databinding.ItemViewModelBinding

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieAdapterViewHolder>() {
    class MovieAdapterViewHolder( itemBinding: ItemViewModelBinding){
        RecyclerView.ViewHolder(itemBinding.root) {
            val view = itemBinding.
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieAdapterViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: MovieAdapterViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

}


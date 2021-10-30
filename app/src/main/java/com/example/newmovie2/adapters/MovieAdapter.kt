package com.example.newmovie2.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.newmovie2.databinding.ItemViewModelBinding
import com.example.newmovie2.fragments.SearchFragment
import com.example.newmovie2.fragments.SearchFragmentDirections
import com.example.newmovie2.models.Result

//class MovieAdapter(private val context: Context): RecyclerView.Adapter<MovieAdapter.MovieAdapterViewHolder>(){
//
///**item-> One  data item of the list to display . represents one  Affiemation object in ur app
// * Adapter -> takes data and prepares it for recycler view to display
// * ViewHolders -> A pool of views for RecyclerView to use and reuse to display affirmation
// * RecyclerView -> Views on screen
// *
// * RecyclerView asks the adapter to create a new list item view for the first data item in ur list*/
//    private var data = mutableListOf<Result>()
//
//    class MovieAdapterViewHolder(private val itemBinding : ItemViewModelBinding): RecyclerView.ViewHolder(itemBinding.root){
//        /**RecyclerView does not interact directly with them views , but deals with ViewHolders instead . A viewHolder represents
//         *  a single list item view in RecyclerView,
//         *  */
//
//        val imageView: ImageView = itemBinding.imageView
//        val title: TextView = itemBinding.title
//
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieAdapterViewHolder {
//        val itemBinding = ItemViewModelBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        return MovieAdapterViewHolder(itemBinding)
//    }
//
//    override fun onBindViewHolder(holder: MovieAdapterViewHolder, position: Int) {
////        val movie = data[position]
//        if (!data[position].image?.url.isNullOrEmpty()) {
//            Glide.with(context).load(data[position].image.url)
//                .into(holder.imageView)
//        }
//        holder.title.text = data[position].title
//
//    }
//
//    override fun getItemCount(): Int {
//        TODO("Not yet implemented")
//    }
//}

class MovieAdapter(private val context: Context) : RecyclerView.Adapter<MovieAdapter.MovieAdapterViewHolder>(){
    private val data = mutableListOf<Result>()


    class MovieAdapterViewHolder(private val itemBinding : ItemViewModelBinding) : RecyclerView.ViewHolder(itemBinding.root){
        val movieImage: ImageView = itemBinding.imageView
        val view = itemBinding.root
        val title: TextView = itemBinding.title
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieAdapterViewHolder {
        val itemBinding = ItemViewModelBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MovieAdapterViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: MovieAdapterViewHolder, position: Int) {
        if(!data[position].image.url?.isNullOrEmpty()) {
            Glide.with(context).load(data[position].image.url)
                .into(holder.movieImage)
        }
        holder.title.text = data[position].title

        holder.view.setOnClickListener {
            val id = data[position].id.split("/")[2]
            val action = SearchFragmentDirections.actionSearchFragmentToDetailFragment(id)
            holder.view.findNavController().navigate(action)
        }
    }

    fun setData(list: List<Result>) {
        data.clear()
        data.addAll(list)
        this.notifyDataSetChanged()
    }

    override fun getItemCount() = data.size
}


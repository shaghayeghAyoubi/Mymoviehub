package com.example.newmovie2.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newmovie2.R
import com.example.newmovie2.databinding.FragmentDetailBinding
import com.example.newmovie2.viewmodels.DetailFragmentVM
import com.example.newmovie2.viewmodels.MovieApiStatus
import com.example.newmovie2.viewmodels.SearchFragmentVM


class DetailFragment : Fragment() {

    private var _binding : FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var imageMovie : ImageView
    private lateinit var title : TextView
    private lateinit var titleType : TextView
    private lateinit var rating : TextView
    private lateinit var releaseDate : TextView
    private lateinit var year : TextView
    private lateinit var summary : TextView
    private lateinit var connectionError : ImageView
    private lateinit var loadingError : ImageView
    private lateinit var id :String

    private val detailViewModel : DetailFragmentVM by viewModels()
    val args: DetailFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        id = args.id
        detailViewModel.getMovieDetail(id)
        detailViewModel.movieDetail.observe(viewLifecycleOwner, {
            if(it.title?.image != null && it.title.image.url.isNotBlank()) {
                Glide.with(requireContext()).load(it.title.image.url)
                    .into(imageMovie)
            }
            title.text = it.title?.title
            titleType.text = it.title?.titleType
            rating.text = it.ratings.toString()
            releaseDate.text = it.releaseDate
            year.text = it.title?.year.toString()
            summary.text = it.plotSummary?.text

        })
        detailViewModel.status.observe(viewLifecycleOwner, {
            when (it!!) {
                MovieApiStatus.LOADING -> {
                    loadingError.visibility = View.VISIBLE
                    connectionError.visibility = View.GONE
                }
                MovieApiStatus.DONE -> {
                    loadingError.visibility = View.GONE
                    connectionError.visibility = View.GONE
                }
                MovieApiStatus.ERROR -> {
                    connectionError.visibility = View.VISIBLE
                    loadingError.visibility = View.GONE
                }
            }
        })

    }
    private fun initView() {
        imageMovie= binding.movieimage
        title = binding.title
        titleType = binding.titleype
        rating = binding.ratings
        releaseDate = binding.releaseDate
        year = binding.year
        summary = binding.summary
        connectionError = binding.connectionError
        loadingError = binding.loadingError

    }

}
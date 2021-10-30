package com.example.newmovie2.fragments

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newmovie2.databinding.FragmentSearchBinding
import com.example.newmovie2.R
import com.example.newmovie2.adapters.MovieAdapter
import com.example.newmovie2.viewmodels.MovieApiStatus
import com.example.newmovie2.viewmodels.SearchFragmentVM


class SearchFragment : Fragment() {

    private var _binding : FragmentSearchBinding?=null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView
    private lateinit var progressBar: ProgressBar
    private lateinit var errorConnection : ImageView
    private val searchViewModel: SearchFragmentVM by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) : View {
        _binding = FragmentSearchBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.movieRecycler
        searchView = binding.searchView
        progressBar=binding.progressBar
        errorConnection = binding.connectionError

//        searchView.setOnClickListener {
//
//        }
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                if(searchView.query.isNullOrBlank()) {
                    val text = "there is no match"
                    val duration = Toast.LENGTH_SHORT

                    val toast = Toast.makeText(context, text, duration)
                    toast.show()
                } else {
                    searchViewModel.getMovieTitle(searchView.query.toString())
                }
                return true
            }
        })
        val mAdapter = MovieAdapter(requireContext())
        recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = mAdapter
        }
        searchViewModel.movieTitle.observe(viewLifecycleOwner, {
            mAdapter.setData(it)
        })

        searchViewModel.status.observe(viewLifecycleOwner, {
            if(it == MovieApiStatus.LOADING) {
                progressBar.visibility = View.VISIBLE
                errorConnection.visibility = View.GONE
            }else if(it == MovieApiStatus.DONE) {
                progressBar.visibility = View.GONE
                errorConnection.visibility = View.GONE
            } else if(it == MovieApiStatus.ERROR) {
                errorConnection.visibility = View.VISIBLE
            }
        })
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
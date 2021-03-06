package com.example.newmovie2.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newmovie2.adapters.MovieAdapter
import com.example.newmovie2.databinding.FragmentSearchBinding
import com.example.newmovie2.viewmodels.MainActivityVM
import com.example.newmovie2.viewmodels.MovieApiStatus
import com.example.newmovie2.viewmodels.SearchFragmentVM


class SearchFragment : Fragment() {


    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView
    private lateinit var progressBar: ProgressBar
    private lateinit var errorConnection: ImageView
    private val searchViewModel: SearchFragmentVM by viewModels()
    private val sharedVieModel: MainActivityVM by activityViewModels()
    private val gridLayout : GridLayoutManager by lazy {
        GridLayoutManager(requireContext(), 2)
    }
    private val linearLayout : LinearLayoutManager by lazy {
        LinearLayoutManager(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.movieRecycler
        searchView = binding.searchView
        progressBar = binding.progressBar
        errorConnection = binding.connectionError

//        searchView.setOnClickListener {
//
//        }
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                if (searchView.query.isNullOrBlank()) {
                    val text = "there is no match"
                    val duration = Toast.LENGTH_SHORT

                    val toast = Toast.makeText(context, text, duration)
                    toast.show()
                } else {
                    searchViewModel.getMovieTitle(searchView.query.toString())
                }
                val `in` =
                    activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                `in`.hideSoftInputFromWindow(searchView.getWindowToken(), 0)
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }
        })
        val mAdapter = MovieAdapter(requireContext())
        recyclerView.layoutManager = linearLayout
        recyclerView.adapter = mAdapter
//        recyclerView.apply {
//            if (sharedVieModel.isLinearLayoutManager.value){
//                layoutManager = LinearLayoutManager(requireContext())
//            }else {
//                recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
//            }
//            adapter = mAdapter
//        }
        sharedVieModel.isLinearLayoutManager.observe(viewLifecycleOwner, {
            recyclerView.apply {
                layoutManager = if (it) {
                    linearLayout
                } else {
                    gridLayout
                }
                adapter = mAdapter
            }
        })



        searchViewModel.movieTitle.observe(viewLifecycleOwner, {
            mAdapter.setData(it.filter { result -> !result.title.isNullOrBlank() })
        })

        searchViewModel.status.observe(viewLifecycleOwner, {
            when (it!!) {
                MovieApiStatus.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                    errorConnection.visibility = View.GONE
                }
                MovieApiStatus.DONE -> {
                    progressBar.visibility = View.GONE
                    errorConnection.visibility = View.GONE
                    binding.resultMovie.visibility = View.VISIBLE
                }
                MovieApiStatus.ERROR -> {
                    errorConnection.visibility = View.VISIBLE
                    progressBar.visibility = View.GONE
                }
            }
        })
    }

}
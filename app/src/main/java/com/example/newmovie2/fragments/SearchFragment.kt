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
import androidx.recyclerview.widget.RecyclerView
import com.example.newmovie2.databinding.FragmentSearchBinding
import com.example.newmovie2.R


class SearchFragment : Fragment() {

    private var _binding : FragmentSearchBinding?=null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView
    private lateinit var progressBar: ProgressBar
    private lateinit var errorConnection : ImageView

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

    }


}
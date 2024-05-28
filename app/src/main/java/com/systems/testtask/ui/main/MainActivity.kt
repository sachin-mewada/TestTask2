package com.systems.testtask.ui.main

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.systems.testtask.R
import com.systems.testtask.databinding.ActivityMainBinding
import com.systems.testtask.recyclerAdapter.MovieAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var binder: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        binder = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setSearchView()

        initObservers()
        viewModel.getMovieDetails("tt0406816")
        super.onCreate(savedInstanceState)
    }

    private fun initObservers() {
        viewModel.movieDetails.observe(this) {

        }
        viewModel.movieList.observe(this) {
            Log.d("results", "${it.Search}")
            val movieAdapter = MovieAdapter(it.Search, this)
            binder.rvMovieList.layoutManager = LinearLayoutManager(this)
            binder.rvMovieList.adapter = movieAdapter
        }
    }

    fun setSearchView() {
        binder.svList.setOnQueryTextListener(object :
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                if (p0 != null && p0.length>2) {
                    viewModel.getMovieList(p0)
                }
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                if (p0 != null && p0.length>2) {
                    viewModel.getMovieList(p0)
                }
                return false
            }

        }
        )
    }
}
package com.example.moviesapp.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviesapp.R
import com.example.moviesapp.adapter.PopularProductAdapter
import com.example.moviesapp.databinding.ActivityMoviesListBinding
import com.example.moviesapp.model.MoviesList
import com.example.moviesapp.utils.ErrorUtil
import com.example.moviesapp.utils.ProgressDialogUtil
import com.example.moviesapp.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesListActivity : AppCompatActivity() , View.OnClickListener{
    private  lateinit var binding: ActivityMoviesListBinding
   // private val viewModel: MovieViewModel by viewModels()

    private val viewModel: MovieViewModel by viewModels()
    private lateinit var adapter: PopularProductAdapter
    private lateinit var moviesList: ArrayList<MoviesList.Product>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize ViewBinding
        binding = ActivityMoviesListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize RecyclerView & Adapter
        initRecyclerView()

        // Initialize ViewModel
      //  viewModel = ViewModelProvider(this)[MovieViewModel::class.java]

        // Set up observers
        viewObserver()

        // Fetch API Data
        hitGetApi()
        inItView()
    }
    private fun inItView(){
       // binding. etSearch.setOnClickListener(this)
        binding.etTextSearch.setOnClickListener {
            startActivity(Intent(this, SearchActivity::class.java))
        }
    }

    private fun initRecyclerView() {
        moviesList = ArrayList()
        adapter = PopularProductAdapter(this, moviesList)

        binding.rvList.layoutManager = LinearLayoutManager(this)
        binding.rvList.adapter = adapter
    }

    private fun hitGetApi() {
        viewModel.hitMoviesList()
    }

    private fun viewObserver() {
        // Observe movie list response
        viewModel.moviesResponse.observe(this, Observer { moviesdata ->
            if (moviesdata != null) {
                moviesList.clear()
                moviesList.addAll(moviesdata.data!!.products)
                adapter.notifyDataSetChanged()
            }
        })

        // Observe errors
        viewModel.errorMovies.observe(this, Observer { throwable ->
            ErrorUtil.handlerGeneralError(this, binding.rvList, throwable)
        })

        // Observe progress loader
        viewModel.progressMovies.observe(this, Observer { isLoading ->
            if (isLoading) {
                ProgressDialogUtil.getInstance().showProgress(this, true)
            } else {
                ProgressDialogUtil.getInstance().hideProgress()
            }
        })
    }

    override fun onClick(v: View?) {
        when(v!!.id) {
            R.id.et_Search -> {
                //startActivity(Intent(this, SearchActivity::class.java))
            }
        }
    }
}

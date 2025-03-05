package com.example.moviesapp.view

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviesapp.adapter.SearchProductAdapter
import com.example.moviesapp.databinding.ActivitySearchBinding
import com.example.moviesapp.utils.ErrorUtil
import com.example.moviesapp.utils.ProgressDialogUtil
import com.example.moviesapp.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchBinding
    private  val viewModel: SearchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
        observeViewModel()
    }

    private fun initViews() {
      //  searchMovies("")

        binding.ivBackSearch.setOnClickListener {
            onBackPressed()
        }

        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                binding.rvSearchList.visibility = View.VISIBLE
                searchMovies(s.toString())
            }
        })
    }

    private fun searchMovies(searchKey: String) {
        viewModel.hitSearchApi(searchKey)
    }

    private fun observeViewModel() {
        viewModel.SearchResponse.observe(this, Observer { response ->
            ProgressDialogUtil.getInstance().hideProgress()
            binding.rvSearchList.layoutManager = LinearLayoutManager(this)

            if (response?.products?.isNotEmpty() == true) {
                val searchAdapter = SearchProductAdapter(response.products, object :
                    SearchProductAdapter.CallbackItemSearch {
                    override fun clickItem(position: Int) {}

                    override fun viewProductSearch(searchId: Int, searchName: String) {
                        startActivity(
                            Intent(this@SearchActivity, MoviesListActivity::class.java)
                                .putExtra("search_Name", searchName)
                        )
                    }

                    override fun viewProductItem(searchId: Int, searchName: String) {

                    }
                })

                binding.rvSearchList.adapter = searchAdapter
                binding.rvSearchList.visibility = View.VISIBLE
            } else {
                binding.rvSearchList.visibility = View.GONE
            }
        })

        viewModel.errorSearch.observe(this, Observer { error ->
            ProgressDialogUtil.getInstance().hideProgress()
            ErrorUtil.handlerGeneralError(this, binding.rvSearchList, error)
        })
    }
}

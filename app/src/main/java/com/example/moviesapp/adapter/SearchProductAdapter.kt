package com.example.moviesapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviesapp.R
import com.example.moviesapp.databinding.LayoutSearchProductListBinding
import com.example.moviesapp.model.SearchModel

class SearchProductAdapter(
    private var productList: List<SearchModel.Product>,
    private var callback: CallbackItemSearch
) : RecyclerView.Adapter<SearchProductAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: LayoutSearchProductListBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = LayoutSearchProductListBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = productList[position]

        with(holder.binding) {
            tvSerchProduct.text = product.title

            // Load product image with Glide
        /*    Glide.with(root.context)
                .load(product.image)
                .placeholder(R.drawable.ic_placeholder) // Replace with actual placeholder
                .into(ivIconSearch)*/

            constSearch.setOnClickListener {
                callback.clickItem(position)
            //    if (product.isProduct == true) {
                    callback.viewProductSearch(product.id, product.title)
               // } else {
                  //  callback.viewProductItem(product.id, product.title)
              //  }
            }
        }
    }

    override fun getItemCount(): Int = productList.size

    interface CallbackItemSearch {
        fun clickItem(position: Int)
        fun viewProductSearch(searchId: Int, searchName: String)
        fun viewProductItem(searchId: Int, searchName: String)
    }
}

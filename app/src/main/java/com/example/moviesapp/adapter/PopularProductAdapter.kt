package com.example.moviesapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviesapp.R
import com.example.moviesapp.databinding.ListItemPopularProductBinding
import com.example.moviesapp.model.MoviesList

class PopularProductAdapter(
    private val context: Context,
    private val popularProductList: ArrayList<MoviesList.Product>,

    ) : RecyclerView.Adapter<PopularProductAdapter.MyViewHolder>() {

    inner class MyViewHolder(val binding: ListItemPopularProductBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ListItemPopularProductBinding.inflate(LayoutInflater.from(context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int = popularProductList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val product = popularProductList[position]
        holder.binding.apply {
            tvProductName.text = product.description

            Glide.with(context).load(product.thumbnail)
                .placeholder(R.drawable.ic_launcher_background)
                .into(ivProductPic)

        }
    }

    interface PopularProductListener {
        fun showProductPic(position: Int, url: ArrayList<String>)
        fun productAddClick(position: Int)
    }
}

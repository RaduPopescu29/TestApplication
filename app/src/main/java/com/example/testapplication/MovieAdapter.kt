package com.example.testapplication

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.testapplication.databinding.ItemHorrorMovieBinding
import com.example.testapplication.databinding.ItemMovieBinding
import com.example.testapplication.model.Movies

class MovieAdapter(val context: Context, val movieList: ArrayList<Movies>, val listener: OnMovieClickListener) :
    RecyclerView.Adapter<ViewHolder>() {

    interface OnMovieClickListener {
        fun onItemDelete(position: Int)
    }


    companion object {
        private const val TYPE_THRILLER = 1
        private const val TYPE_HORROR = 2
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.d("test_adapter", "onCreateViewHolder")
        return when (viewType) {
            TYPE_THRILLER -> {
                val binding = ItemMovieBinding.inflate(LayoutInflater.from(context), parent, false)
                MovieViewHolder(binding)
            }
            TYPE_HORROR -> {
                val binding =
                    ItemHorrorMovieBinding.inflate(LayoutInflater.from(context), parent, false)
                HorrorMovieViewHolder(binding)
            }
            else -> {
                throw  Exception("Unknown view type $viewType")
            }
        }

    }

    override fun getItemViewType(position: Int): Int {
        val movie = movieList[position]
        return if (movie.type == "horror") return TYPE_HORROR
        else TYPE_THRILLER
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d("test_adapter", "onBindViewHolder at position $position")
        val type = getItemViewType(position)
        val movie = movieList[position]
        when (type) {
            TYPE_THRILLER -> {
                holder as MovieViewHolder
                holder.binding.title.text = movie.title
                holder.binding.description.text = movie.description
                holder.binding.delete.setOnClickListener {
                    movieList.remove(movie)
                    notifyItemRemoved(position)
                    notifyItemRangeChanged(position, movieList.size)
                    listener.onItemDelete(position)
                }
            }
            TYPE_HORROR -> {
                holder as HorrorMovieViewHolder
                holder.binding.title.text = movie.title
                holder.binding.description.text = movie.description
            }
        }

    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    fun addMovie(movie: Movies) {
        movieList.add(1, movie)
        notifyItemInserted(1)
    }


    class MovieViewHolder(val binding: ItemMovieBinding) : ViewHolder(binding.root)

    class HorrorMovieViewHolder(val binding: ItemHorrorMovieBinding) : ViewHolder(binding.root)
}
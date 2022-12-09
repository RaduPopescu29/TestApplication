package com.example.testapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testapplication.databinding.ActivityListBinding
import com.example.testapplication.model.Movies
import java.util.*


class ListActivity : AppCompatActivity(),MovieAdapter.OnMovieClickListener {

    private lateinit var binding: ActivityListBinding
    private lateinit var adapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.addMovie.setOnClickListener {
            adapter.addMovie(
                Movies(
                    "Random title ${Random().nextInt()}",
                    "description ${Random().nextInt()}",
                    "horror"
                )
            )
        }
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        adapter = MovieAdapter(this, createMovies(),this)
        binding.movieList.layoutManager = LinearLayoutManager(this)
        binding.movieList.addItemDecoration(
            DividerItemDecoration(
                binding.movieList.context,
                DividerItemDecoration.VERTICAL
            )
        )
        binding.movieList.adapter = adapter
    }

    private fun createMovies(): ArrayList<Movies> {
        val list = ArrayList<Movies>()
        for (index in 0..100) {
            val movie = Movies(
                title = "Title $index",
                description = "This is the description for movie $index",
                "thriller"
                //if (index % 3 == 0) "horror" else "thriller"
            )
            list.add(movie)
        }
        return list
    }

    override fun onItemDelete(position: Int) {

    }
}
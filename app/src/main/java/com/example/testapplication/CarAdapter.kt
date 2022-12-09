package com.example.testapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testapplication.databinding.ItemMovieBinding
import com.example.testapplication.model.Car

class CarAdapter(val context: Context, val carList: ArrayList<Car>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(context), parent, false)
        return MovieViewHolder(binding)
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val car = carList[position]
        holder as MovieViewHolder
        holder.binding.title.text = car.marca
        holder.binding.description.text = car.pret.toString()
    }

    override fun getItemCount(): Int {
        return carList.size
    }

    fun addCars(cars: List<Car>){
        carList.clear()
        carList.addAll(cars)
        notifyDataSetChanged()
    }


    class MovieViewHolder(val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root)

}
package com.example.testapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var carAdapter: CarAdapter
    private lateinit var viewModel: CarsViewModel

    companion object {
        private const val UPDATE_TEXT = 1000
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.submit.setOnClickListener(this)
        binding.listActivity.setOnClickListener(this)
        viewModel = ViewModelProvider(this).get(CarsViewModel::class.java)
        setupRecyclerView()
        setupObservers()
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.loadUrl("https://www.emag.ro")
    }

    override fun onResume() {
        super.onResume()
        viewModel.getCars()
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.submit -> {
                val intent = Intent(this, SecondActivity::class.java)
                startActivityForResult(intent, UPDATE_TEXT)
            }
            R.id.listActivity -> {
                val intent = Intent(this, ListActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            when (requestCode) {
                UPDATE_TEXT -> {
                    val text = data?.extras?.getString(SecondActivity.TEXT_UPDATED)
                    binding.editTextTextPersonName.setText(text)
                }
            }
        }
    }

    private fun setupObservers() {
        viewModel.carListLiveData.observe(this) {
            carAdapter.addCars(it)
        }
    }

    private fun setupRecyclerView() {
        carAdapter = CarAdapter(this, arrayListOf())
        binding.carList.layoutManager = LinearLayoutManager(this)
        binding.carList.addItemDecoration(
            DividerItemDecoration(
                binding.carList.context, DividerItemDecoration.VERTICAL
            )
        )
        binding.carList.adapter = carAdapter
    }
}
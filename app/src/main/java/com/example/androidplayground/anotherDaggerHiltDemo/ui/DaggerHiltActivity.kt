package com.example.androidplayground.anotherDaggerHiltDemo.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidplayground.databinding.ActivityDaggerHiltBinding
import com.example.androidplayground.anotherDaggerHiltDemo.adapter.OnItemClickListener
import com.example.androidplayground.anotherDaggerHiltDemo.adapter.RecyclerViewAdapter
import com.example.androidplayground.anotherDaggerHiltDemo.model.DisplayData
import com.example.androidplayground.anotherDaggerHiltDemo.repository.ResultOf
import com.example.androidplayground.anotherDaggerHiltDemo.viewmodel.DaggerHiltActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DaggerHiltActivity : AppCompatActivity(), OnItemClickListener {

    private val viewModel: DaggerHiltActivityViewModel by viewModels()
    private lateinit var binding: ActivityDaggerHiltBinding
    private lateinit var adapter: RecyclerViewAdapter;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDaggerHiltBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()
        viewModel.liveDataList.observe(this, { result ->
            when (result) {
                is ResultOf.Success -> {
                    binding.loader.visibility = View.GONE
                    result.value?.items?.let { adapter.setUpdatedData(it) }
                }

                // here as well
                is ResultOf.Failure -> {
                    Toast.makeText(
                        this,
                        "Please try again later, error: ${result.message}",
                        Toast.LENGTH_LONG
                    ).show();
                }
            }
        })

        binding.loader.visibility = View.VISIBLE
        //viewModel.loadListOfData() --> calling directly in viewmodel init instead
    }

    private fun initRecyclerView() {
        adapter = RecyclerViewAdapter(this)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }

    override fun onDestroy() {
        Log.e("destroyed","activity_true")
        super.onDestroy()
    }

    override fun onItemClicked(view: View, displayData: DisplayData) {
        Toast.makeText(this, "Clicked on ${displayData.description}", Toast.LENGTH_LONG).show()
    }


}
package com.test.framework.mvvm.ui.main.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.framework.mvvm.data.model.Photo
import com.test.framework.mvvm.databinding.ActivityMainBinding
import com.test.framework.mvvm.ui.main.adapter.MainAdapter
import com.test.framework.mvvm.ui.main.viewmodel.MainViewModel
import com.test.framework.mvvm.utils.RecylerViewOnClick
import com.test.framework.mvvm.utils.Status
import org.koin.androidx.viewmodel.ext.android.viewModel

const val OBJECT = "object"

class MainActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModel()
    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding.root)
        setupUI()
        setupObserver()

    }

    private fun setupUI() {
        with(_binding) {
            recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = MainAdapter(object : RecylerViewOnClick {
                override fun onClick(data: Photo) {
                    startActivity(
                        Intent(this@MainActivity, DetailActivity::class.java).putExtra(
                            OBJECT, data
                        )
                    )
                }
            })
            recyclerView.addItemDecoration(
                DividerItemDecoration(
                    recyclerView.context,
                    (recyclerView.layoutManager as LinearLayoutManager).orientation
                )
            )
            recyclerView.adapter = adapter
        }
    }

    private fun setupObserver() {
        mainViewModel.users.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    _binding.progressBar.visibility = View.GONE
                    it.data?.let { users -> renderList(users) }
                    _binding.recyclerView.visibility = View.VISIBLE
                }

                Status.LOADING -> {
                    _binding.progressBar.visibility = View.VISIBLE
                    _binding.recyclerView.visibility = View.GONE
                }

                Status.ERROR -> {
                    //Handle Error
                    _binding.progressBar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun renderList(photos: List<Photo>) {
        adapter.differ.submitList(photos)
    }
}

package com.test.framework.mvvm.ui.main.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import com.test.framework.mvvm.data.model.Photo
import com.test.framework.mvvm.databinding.DetailActivityBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var _binding: DetailActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DetailActivityBinding.inflate(layoutInflater)
        setContentView(_binding.root)
        setUpUI()
    }

    private fun setUpUI() {
        val dataPhoto = intent.getParcelableExtra(OBJECT) as? Photo
        dataPhoto?.let {
            with(_binding) {
                title.text = dataPhoto.title
                description.text = dataPhoto.url
                print("===========${dataPhoto.url}")
                Picasso.get().load(dataPhoto.url).noPlaceholder().into(image)
            }
        }
    }
}

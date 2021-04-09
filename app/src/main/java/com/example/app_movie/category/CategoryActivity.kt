package com.example.app_movie.category

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.app_movie.R
import com.example.app_movie.RecyclerItemClickListener
import com.example.app_movie.connect.Connecter
import com.example.app_movie.databinding.ActivityCategoryBinding
import com.example.app_movie.info.InfoActivity
import com.example.app_movie.main.model.ExampleModel
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class CategoryActivity : AppCompatActivity() {

    private val binding : ActivityCategoryBinding by lazy {
        ActivityCategoryBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val intent = intent
        val category = intent.getIntExtra("position",0)
        getMovieCategory(category + 1)
    }

    private fun getMovieCategory(category: Int) {
        val retrofit = Connecter.api
        val call = retrofit.getMovieCategory("a", category)
        call.enqueue(object : Callback<ExampleModel> {
            override fun onResponse(call: Call<ExampleModel>, response: Response<ExampleModel>) {
                val value = arrayListOf<CategoryModel>()
                response.body()?.items?.forEach {
                    value.add(CategoryModel(it.title ?: "",it.image ?: ""))
                }
                binding.recyclerCategory.adapter = CategoryAdapter(value)
            }

            override fun onFailure(call: Call<ExampleModel>, t: Throwable) {
                Log.e("asdfTest",t.toString())
            }
        })
    }

}

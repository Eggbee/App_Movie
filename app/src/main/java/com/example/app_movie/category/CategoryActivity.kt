package com.example.app_movie.category

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import com.example.app_movie.connect.Connecter
import com.example.app_movie.databinding.ActivityCategoryBinding
import com.example.app_movie.info.InfoActivity
import com.example.app_movie.main.model.ExampleModel
import com.example.app_movie.util.ClickEvent
import com.google.gson.Gson
import kotlinx.android.synthetic.main.item_movie.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.core.util.Pair as UtilPair

class CategoryActivity : AppCompatActivity(), ClickEvent<Pair<CategoryModel, View>> {

    private val binding: ActivityCategoryBinding by lazy {
        ActivityCategoryBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val intent = intent
        val category = intent.getIntExtra("position", 0)
        getMovieCategory(category + 1)
    }

    private fun getMovieCategory(category: Int) {
        val retrofit = Connecter.api
        val call = retrofit.getMovieCategory("a", category)
        call.enqueue(object : Callback<ExampleModel> {
            override fun onResponse(call: Call<ExampleModel>, response: Response<ExampleModel>) {
                val value = arrayListOf<CategoryModel>()
                response.body()?.items?.forEach {
                    value.add(CategoryModel(it.title ?: "", it.image ?: ""))
                }
                binding.recyclerCategory.adapter = CategoryAdapter(value, this@CategoryActivity)
            }

            override fun onFailure(call: Call<ExampleModel>, t: Throwable) {
            }
        })
    }

    override fun onClick(value: Pair<CategoryModel, View>) {
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
            this,
            UtilPair.create(value.second.ic_movie as View, value.second.ic_movie.transitionName),
            UtilPair.create(
                value.second.text_movie as View,
                value.second.text_movie.transitionName
            )
        )

        val intent =
            Intent(this, InfoActivity::class.java).putExtra("movieInfo", Gson().toJson(value.first))
        startActivity(intent, options.toBundle())
    }

}

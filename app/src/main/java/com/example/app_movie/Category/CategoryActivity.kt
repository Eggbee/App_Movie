package com.example.app_movie.Category

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.app_movie.Connect.Connecter
import com.example.app_movie.Main.Model.ExampleModel
import com.example.app_movie.R
import com.example.app_movie.Search.SearchAdapter
import com.example.app_movie.Search.SearchModel2
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class CategoryActivity : AppCompatActivity() {
    lateinit var exampleModellist: ExampleModel
    var categoryModel = ArrayList<CategoryModel>()
    lateinit var recycler_category: RecyclerView
    lateinit var movie_title: String
    lateinit var movie_image: String
    lateinit var categoryAdapter: CategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)
        val intent = getIntent()
        val category = intent.extras.getInt("position")
        recycler_category = findViewById(com.example.app_movie.R.id.recycler_category)
        categoryAdapter = CategoryAdapter(applicationContext, categoryModel)
        recycler_category.layoutManager = GridLayoutManager(applicationContext, 2) as RecyclerView.LayoutManager?
        recycler_category.adapter = categoryAdapter
        getMovie_Category("a", category)
    }

    fun getMovie_Category(name: String, category: Int) {
        val retrofit = Connecter.createApi()
        val call = retrofit.getMovie_Category(name, category)
        call.enqueue(object : Callback<ExampleModel> {
            override fun onResponse(call: Call<ExampleModel>, response: Response<ExampleModel>) {
                exampleModellist = response.body()!!
                for (i in 0 until exampleModellist.items!!.size) {
                    movie_title = exampleModellist.items!!.get(i).title!!
                    movie_image = exampleModellist.items!!.get(i).image!!
                    categoryModel.add(CategoryModel(movie_title, movie_image))
                }
                recycler_category.adapter = categoryAdapter
            }

            override fun onFailure(call: Call<ExampleModel>, t: Throwable) {

            }
        })
    }

}

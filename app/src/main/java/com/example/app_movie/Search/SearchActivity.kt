package com.example.app_movie.Search

import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.EditText
import com.bumptech.glide.Glide
import com.example.app_movie.Connect.Connecter
import com.example.app_movie.Main.Model.ExampleModel
import com.example.app_movie.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList


class SearchActivity : AppCompatActivity() {
    lateinit var recycler_search: RecyclerView
    var searchModel=ArrayList<SearchModel2>()
    lateinit var movie_title: String
    lateinit var movie_image: String
    lateinit var exampleModellist: ExampleModel
    lateinit var searchAdapter :SearchAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        val edit_search = findViewById<EditText>(com.example.app_movie.R.id.edit_search)
        recycler_search = findViewById(R.id.recycler_search)
        searchAdapter = SearchAdapter(applicationContext, searchModel)
        recycler_search.layoutManager = GridLayoutManager(applicationContext, 2) as RecyclerView.LayoutManager?
        recycler_search.adapter = searchAdapter
        edit_search.setOnClickListener {
            get_movie(edit_search.text.toString())
        }
    }

    fun get_movie(name: String) {
        val retrofit = Connecter.createApi()
        val call = retrofit.getMovie(name, 10)
        call.enqueue(object : Callback<ExampleModel> {
            override fun onResponse(call: Call<ExampleModel>, response: Response<ExampleModel>) {
                exampleModellist = response.body()!!
                for (i in 0 until exampleModellist.items!!.size) {
                    movie_title = exampleModellist.items!!.get(i).title!!
                    movie_image = exampleModellist.items!!.get(i).image!!
                    searchModel.add(SearchModel2(movie_title,movie_image))

                }
                recycler_search.adapter = searchAdapter
            }

            override fun onFailure(call: Call<ExampleModel>, t: Throwable) {

            }
        })
    }
}

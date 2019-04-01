package com.example.app_movie.Search

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.EditText
import com.example.app_movie.Connect.Connecter
import com.example.app_movie.Main.MainActivity
import com.example.app_movie.Main.Model.ExampleModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList
import android.view.WindowManager
import com.example.app_movie.Info.InfoActivity
import com.example.app_movie.RecyclerItemClickListener


class SearchActivity : AppCompatActivity() {
    lateinit var recycler_search: RecyclerView
    var searchModel = ArrayList<SearchModel2>()
    lateinit var movie_title: String
    lateinit var movie_image: String
    lateinit var exampleModellist: ExampleModel
    lateinit var searchAdapter: SearchAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.app_movie.R.layout.activity_search)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
        val edit_search = findViewById<EditText>(com.example.app_movie.R.id.edit_search)
        recycler_search = findViewById(com.example.app_movie.R.id.recycler_search)
        searchAdapter = SearchAdapter(applicationContext, searchModel)
        recycler_search.layoutManager = GridLayoutManager(applicationContext, 2) as RecyclerView.LayoutManager?
        recycler_search.adapter = searchAdapter
        edit_search.setOnClickListener {
            searchModel.clear()
            get_movie(edit_search.text.toString())
        }
        recycler_search.addOnItemTouchListener(
            RecyclerItemClickListener(
                applicationContext,
                recycler_search,
                object : RecyclerItemClickListener.OnItemClickListener {
                    override fun onItemClick(view: View, position: Int) {
                        val intent = Intent(applicationContext, InfoActivity::class.java)
                        intent.putExtra("image", exampleModellist.items!!.get(position).image)
                        intent.putExtra("title", exampleModellist.items!!.get(position).title)
                        intent.putExtra("rating", exampleModellist.items!!.get(position).userRating)
                        intent.putExtra("director", exampleModellist.items!!.get(position).director)
                        startActivity(intent)
                    }

                    override fun onLongItemClick(view: View?, position: Int) {

                    }
                })
        )
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
                    searchModel.add(SearchModel2(movie_title, movie_image))
                }
                recycler_search.adapter = searchAdapter
            }

            override fun onFailure(call: Call<ExampleModel>, t: Throwable) {

            }
        })
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}

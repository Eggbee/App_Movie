package com.example.app_movie.Search

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.EditText
import android.widget.GridLayout
import android.widget.HorizontalScrollView
import com.example.app_movie.Connect.Connecter
import com.example.app_movie.Main.Model.ItemModel
import com.example.app_movie.Main.Search.SearchAdapter
import com.example.app_movie.Main.Search.SearchModel
import com.example.app_movie.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList


class SearchActivity : AppCompatActivity() {
    lateinit var recycler_search:RecyclerView
    var ItemModel=ArrayList<ItemModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.app_movie.R.layout.activity_search)
        val edit_search=findViewById<EditText>(com.example.app_movie.R.id.edit_search)
        val searchAdapter = SearchAdapter(applicationContext, ItemModel)
        val gridLayoutManager = GridLayoutManager(this, 2)
        recycler_search.adapter=searchAdapter
        recycler_search.setLayoutManager(gridLayoutManager);
        edit_search.setOnClickListener {
            get_movie(edit_search.text.toString())
        }
    }

    fun get_movie(name:String){
        val retrofit = Connecter.createApi()
        val call=retrofit.getMovie("w3Sm4xnRiqhOIpNHgOY0","m4NT9Ij63u",name,10)
        call.enqueue(object : Callback<ItemModel> {
            override fun onResponse(call: Call<ItemModel>, response: Response<ItemModel>) {
//                ItemModel=response.body()
            }
            override fun onFailure(call: Call<ItemModel>, t: Throwable) {

            }
        })
    }


}

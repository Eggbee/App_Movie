package com.example.app_movie.Search

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import com.example.app_movie.Connect.Connecter
import com.example.app_movie.main.MainActivity
import com.example.app_movie.main.model.ExampleModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList
import android.view.WindowManager
import com.example.app_movie.Info.InfoActivity
import com.example.app_movie.RecyclerItemClickListener
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity() {
    lateinit var recycler_search: RecyclerView
    var searchModel = ArrayList<SearchModel2>()
    lateinit var movie_title: String
    lateinit var movie_image: String
    lateinit var exampleModellist: ExampleModel
    lateinit var searchAdapter: SearchAdapter
    val firebaseDatabase: FirebaseDatabase = FirebaseDatabase.getInstance()
    val database: DatabaseReference = firebaseDatabase.getReference()
    var start_num = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.app_movie.R.layout.activity_search)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
        recycler_search = findViewById(com.example.app_movie.R.id.recycler_search)
        searchAdapter = SearchAdapter(applicationContext, searchModel)
        recycler_search.layoutManager = GridLayoutManager(
            applicationContext,
            2
        )
        recycler_search.adapter = searchAdapter
        edit_search.setOnClickListener {
            text_movie.visibility = View.INVISIBLE
            ic_movie.visibility = View.INVISIBLE
            searchModel.clear()
            start_num = 1
            get_movie(edit_search.text.toString(), 1)
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

        recycler_search.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!recycler_search.canScrollVertically(1)) {
                    start_num = start_num + 10
                    get_movie(edit_search.text.toString(), start_num)
                    searchAdapter.notifyDataSetChanged()
                }
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }
        })
    }

    fun get_movie(name: String, num: Int) {
        val retrofit = Connecter.createApi()
        val call = retrofit.getMovie(name, 10, num)
        call.enqueue(object : Callback<ExampleModel> {
            override fun onResponse(call: Call<ExampleModel>, response: Response<ExampleModel>) {
                exampleModellist = response.body()!!
                if (exampleModellist.items!!.size == 0) {
                    text_movie.visibility = View.VISIBLE
                    ic_movie.visibility = View.VISIBLE
                    database.child("data").child(name).push().setValue(name)
                }
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

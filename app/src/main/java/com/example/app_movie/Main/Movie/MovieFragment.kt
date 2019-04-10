package com.example.app_movie.Main.Movie

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_movie.*
import android.support.v7.app.AppCompatActivity
import android.support.design.widget.CollapsingToolbarLayout
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.app_movie.Connect.Connecter
import com.example.app_movie.Main.Model.ExampleModel
import com.example.app_movie.Main.Recommend.RecommendModel
import com.example.app_movie.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList


class MovieFragment : Fragment() {

    lateinit var exampleModellist: ExampleModel
    lateinit var movie_title: String
    lateinit var movie_image: String
    lateinit var recycler_Movie_First: RecyclerView
    lateinit var recycler_Movie_Second: RecyclerView
    lateinit var movieRecyclerAdapter: MovieRecyclerAdapter
    val movieModel = ArrayList<MovieModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val layout = inflater.inflate(com.example.app_movie.R.layout.fragment_movie, container, false) as ViewGroup
        val viewPager = layout.findViewById<ViewPager>(com.example.app_movie.R.id.view_movie)
        viewPager.adapter = MovieAdapter(childFragmentManager)
        recycler_Movie_First = layout.findViewById<RecyclerView>(R.id.recycler_movie_first)
        recycler_Movie_Second = layout.findViewById<RecyclerView>(R.id.recycler_movie_second)
        movieRecyclerAdapter = MovieRecyclerAdapter(activity!!, movieModel)
        recycler_Movie_First.layoutManager = GridLayoutManager(context, 2)
        recycler_Movie_Second.layoutManager = GridLayoutManager(context, 2)
        recycler_Movie_First.setHasFixedSize(true)
        recycler_Movie_Second.setHasFixedSize(true)
        recycler_Movie_First.adapter = movieRecyclerAdapter
        recycler_Movie_Second.adapter = movieRecyclerAdapter
        if (movieModel.size == 0) {
            getMovie("e")
        }
        return layout
    }

    fun getMovie(name: String) {
        val retrofit = Connecter.createApi()
        val call = retrofit.getMovie(name, 4, 1)
        call.enqueue(object : Callback<ExampleModel> {
            override fun onResponse(call: Call<ExampleModel>, response: Response<ExampleModel>) {
                exampleModellist = response.body()!!
                for (i in 0 until exampleModellist.items!!.size) {
                    movie_title = exampleModellist.items!!.get(i).title!!
                    movie_image = exampleModellist.items!!.get(i).image!!
                    movieModel.add(MovieModel(movie_title, movie_image))
                }
                recycler_Movie_First.adapter = movieRecyclerAdapter
                recycler_Movie_Second.adapter = movieRecyclerAdapter
            }

            override fun onFailure(call: Call<ExampleModel>, t: Throwable) {

            }
        })
    }
}
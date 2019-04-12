package com.example.app_movie.Main.Movie

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Button
import com.example.app_movie.Category.CategoryActivity
import com.example.app_movie.Connect.Connecter
import com.example.app_movie.Info.InfoActivity
import com.example.app_movie.Main.Model.ExampleModel
import com.example.app_movie.NewMovie.NewMovieActivity
import com.example.app_movie.R
import com.example.app_movie.RecyclerItemClickListener
import kotlinx.android.synthetic.main.fragment_movie.*
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
        val bt_New_Movie=layout.findViewById<Button>(R.id.bt_new_movie)
        recycler_Movie_First = layout.findViewById(R.id.recycler_movie_first)
        recycler_Movie_Second = layout.findViewById(R.id.recycler_movie_second)
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
        bt_New_Movie.setOnClickListener { startActivity(Intent(context,NewMovieActivity::class.java)) }
        recycler_Movie_First.addOnItemTouchListener(
            RecyclerItemClickListener(
                context!!,
                recycler_Movie_First,
                object : RecyclerItemClickListener.OnItemClickListener {
                    override fun onItemClick(view: View, position: Int) {
                        val intent = Intent(context, InfoActivity::class.java)
                        intent.putExtra("title",exampleModellist.items!!.get(position).title)
                        intent.putExtra("image",exampleModellist.items!!.get(position).image)
                        startActivity(intent)
                    }

                    override fun onLongItemClick(view: View?, position: Int) {

                    }
                })
        )
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
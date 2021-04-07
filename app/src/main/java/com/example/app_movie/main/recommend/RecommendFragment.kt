package com.example.app_movie.main.recommend

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.app_movie.Connect.Connecter
import com.example.app_movie.main.model.ExampleModel
import com.example.app_movie.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class RecommendFragment : Fragment() {
    lateinit var recycler_recommend: RecyclerView
    val recommendModel = ArrayList<RecommendModel>()
    lateinit var movie_title: String
    lateinit var movie_image: String
    lateinit var movie_year: String
    lateinit var exampleModellist: ExampleModel
    lateinit var recommendAdapter: RecommendAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val layout = inflater.inflate(R.layout.fragment_recommend, container, false) as ViewGroup
        recycler_recommend = layout.findViewById<View>(R.id.recycler_recommend) as RecyclerView
        recycler_recommend.setHasFixedSize(true)
        recommendAdapter = RecommendAdapter(activity!!, recommendModel)
        recycler_recommend.layoutManager =
            LinearLayoutManager(context)
        val dividerItemDecoration =
            DividerItemDecoration(
                context!!,
                LinearLayoutManager(context).orientation
            )
        recycler_recommend.addItemDecoration(dividerItemDecoration)
        recycler_recommend.adapter = recommendAdapter
        if (recommendModel.size == 0) {
            getMovie("t")
        }
        return layout
    }

    fun getMovie(name: String) {
        val retrofit = Connecter.createApi()
        val call = retrofit.getMovie(name, 10, 1)
        call.enqueue(object : Callback<ExampleModel> {
            override fun onResponse(call: Call<ExampleModel>, response: Response<ExampleModel>) {
                exampleModellist = response.body()!!
                for (i in 0 until exampleModellist.items!!.size) {
                    movie_title = exampleModellist.items!!.get(i).title!!
                    movie_year = exampleModellist.items!!.get(i).pubDate!!
                    movie_image = exampleModellist.items!!.get(i).image!!
                    recommendModel.add(
                        RecommendModel(
                            movie_title,
                            movie_year,
                            movie_image
                        )
                    )
                }
                recycler_recommend.adapter = recommendAdapter
            }

            override fun onFailure(call: Call<ExampleModel>, t: Throwable) {

            }
        })
    }
}
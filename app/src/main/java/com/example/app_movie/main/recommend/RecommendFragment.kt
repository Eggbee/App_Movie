package com.example.app_movie.main.recommend

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.app_movie.connect.Connecter
import com.example.app_movie.databinding.FragmentRecommendBinding
import com.example.app_movie.main.model.ExampleModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class RecommendFragment : Fragment() {

    private var _binding: FragmentRecommendBinding? = null
    private val binding get() = _binding

    val recommendModel = ArrayList<RecommendModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (_binding == null) {
            _binding = FragmentRecommendBinding.inflate(inflater, container, false)
            getMovie()
        }
        return binding?.root
    }

    private fun getMovie() {
        val retrofit = Connecter.createApi()
        val call = retrofit.getMovie("t", 10, 1)
        call.enqueue(object : Callback<ExampleModel> {
            override fun onResponse(call: Call<ExampleModel>, response: Response<ExampleModel>) {
                response.body()?.items?.forEach {
                    recommendModel.add(
                        RecommendModel(
                            it.title ?: "",
                            it.pubDate ?: "",
                            it.image ?: ""
                        )
                    )
                }
                binding?.recyclerRecommend?.adapter = RecommendAdapter(recommendModel)
            }

            override fun onFailure(call: Call<ExampleModel>, t: Throwable) {

            }
        })
    }
}
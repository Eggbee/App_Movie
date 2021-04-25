package com.example.app_movie.main.movie

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.app_movie.connect.MovieServiceUtil
import com.example.app_movie.databinding.FragmentMovieBinding
import com.example.app_movie.databinding.ItemPopularMovieInfoBinding
import com.example.app_movie.main.model.BaseResponse
import com.example.app_movie.main.model.MovieModel
import com.example.app_movie.util.ClickEvent
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import org.koin.android.ext.android.inject
import java.util.*
import kotlin.collections.ArrayList


class MovieFragment : Fragment(),ClickEvent<Pair<ItemPopularMovieInfoBinding,MovieModel>> {

    private var _binidng: FragmentMovieBinding? = null
    private val binding get() = _binidng

    private val movieServiceUtil: MovieServiceUtil by inject()
    private val compositeDisposable = CompositeDisposable()

    private val movieTitle =  listOf("최고 인기작","개봉 예정작","최고 평점작","현재 상영작")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (_binidng == null) {
            _binidng = FragmentMovieBinding.inflate(inflater, container, false)
            getPopularMovieInfo()
        }
        return binding?.root
    }

    private fun getPopularMovieInfo() {
        val data = arrayListOf<Pair<String,ArrayList<MovieModel>>>()

        Flowable.rangeLong(0,4)
            .flatMap { getMovieInfo(it.toInt()).second.toFlowable() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                val title = movieTitle.get(data.size)
                data.add(Pair(title,it.results ?: arrayListOf()))
                if(data.size > 3) binding?.viewMovieInfo?.adapter = MovieAdapter(data,this)
            },{
                Log.e("asdfError",it.toString())
            }).addTo(compositeDisposable)
    }

    private fun getMovieInfo(index : Int): Pair<String, Single<BaseResponse<MovieModel>>> {
        return when(index){
            0 -> Pair("최고 인기작",movieServiceUtil.getPopularMovieInfo(page = rand(1,10)))
            1 -> Pair("개봉 예정작",movieServiceUtil.getUpComingMovie(page = rand(1,10)))
            2 -> Pair("최고 평점작",movieServiceUtil.getTopRatedMovie(page = rand(1,10)))
            else -> Pair("현재 상영작",movieServiceUtil.getNowPlayingMovie(page = rand(1,10)))
        }
    }

    fun rand(from: Int, to: Int) : Int {
        val page = Random()
        return page.nextInt(to - from) + from
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }

    override fun onClick(value: Pair<ItemPopularMovieInfoBinding, MovieModel>) {
        Log.e("asdfMovie",value.second.toString())
    }
}
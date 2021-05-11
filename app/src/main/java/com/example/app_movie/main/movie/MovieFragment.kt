package com.example.app_movie.main.movie

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.app_movie.R
import com.example.app_movie.connect.MovieServiceUtil
import com.example.app_movie.databinding.FragmentMovieBinding
import com.example.app_movie.databinding.ItemPopularMovieInfoBinding
import com.example.app_movie.info.InfoActivity
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


class MovieFragment : Fragment(), ClickEvent<Pair<ItemPopularMovieInfoBinding, MovieModel>> {

    private var _binidng: FragmentMovieBinding? = null
    private val binding get() = _binidng

    private val movieServiceUtil: MovieServiceUtil by inject()
    private val compositeDisposable = CompositeDisposable()

    private val movieTitle = listOf("최고 인기작", "개봉 예정작", "최고 평점작", "현재 상영작")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (_binidng == null) {
            _binidng = FragmentMovieBinding.inflate(inflater, container, false)
            getPopularMovieInfo()
            setPosterViewPager()
        }
        return binding?.root
    }

    private fun setPosterViewPager(){
        val posterData = arrayListOf(
            Triple(R.drawable.poster_1,"왓챠 최고 인기작","중경삼림 리마스터링, 해리포터 등\n지금 가장 많이 보는 작품"),
            Triple(R.drawable.poster_2,"최고 인기 시리즈","강철부대, 약속의 네버랜드 2기 등")
        )
        binding?.viewMovie?.adapter = MovieViewPagerAdapter(posterData)
    }

    private fun getPopularMovieInfo() {
        val data = arrayListOf<Pair<String, ArrayList<MovieModel>>>()

        Flowable.rangeLong(0, 4)
            .flatMap { getMovieInfo(it.toInt()).toFlowable() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                val title = movieTitle.get(data.size)
                data.add(Pair(title, it.results ?: arrayListOf()))
                if (data.size > 3) binding?.viewMovieInfo?.adapter = MovieAdapter(data, this)
            }, {
            }).addTo(compositeDisposable)
    }

    private fun getMovieInfo(index: Int): Single<BaseResponse<MovieModel>> {
        return when (index) {
            0 -> movieServiceUtil.getPopularMovieInfo(page = rand())
            1 -> movieServiceUtil.getUpComingMovie(page = rand())
            2 -> movieServiceUtil.getTopRatedMovie(page = rand())
            else -> movieServiceUtil.getNowPlayingMovie(page = rand())
        }
    }

    private fun rand(): Int {
        val page = Random()
        return page.nextInt(10 - 1) + 1
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }

    override fun onClick(value: Pair<ItemPopularMovieInfoBinding, MovieModel>) {
        startActivity(Intent(activity,InfoActivity::class.java).putExtra("id",value.second.id))
    }
}
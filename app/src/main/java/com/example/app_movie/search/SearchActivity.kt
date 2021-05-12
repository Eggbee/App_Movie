package com.example.app_movie.search

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.app_movie.connect.MovieServiceUtil
import com.example.app_movie.databinding.ActivitySearchBinding
import com.example.app_movie.info.InfoActivity
import com.example.app_movie.main.model.MovieModel
import com.example.app_movie.util.ClickEvent
import com.jakewharton.rxbinding3.widget.textChanges
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import org.koin.android.ext.android.inject
import java.util.concurrent.TimeUnit

class SearchActivity : AppCompatActivity(), ClickEvent<MovieModel> {

    private val binding: ActivitySearchBinding by lazy {
        ActivitySearchBinding.inflate(layoutInflater)
    }

    private var searchObservable: Disposable? = null
    private val compositeDisposable = CompositeDisposable()
    private val movieServiceUtil: MovieServiceUtil by inject()
    private val searchAdapter = SearchAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
        binding.recyclerSearch.adapter = searchAdapter

        setSearchEvent()
    }

    private fun setSearchEvent() {
        searchObservable = binding.editSearch.textChanges()
            .throttleLast(500, TimeUnit.MILLISECONDS)
            .observeOn(Schedulers.computation())
            .subscribe {
                if (it.isNotEmpty()) getMovie(it.toString())
            }
    }

    private fun getMovie(query: String) {
        movieServiceUtil.searchMovie(query = query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it.results?.isNullOrEmpty() == true) return@subscribe
                else {
                    binding.icMovie.visibility = View.GONE
                    binding.textMovie.visibility = View.GONE
                    searchAdapter.remove()
                    searchAdapter.addItem(it.results)
                }
            }, {}).addTo(compositeDisposable)
    }

    override fun onDestroy() {
        super.onDestroy()
        searchObservable?.dispose()
        compositeDisposable.dispose()
    }

    override fun onClick(value: MovieModel) {
        startActivity(Intent(this, InfoActivity::class.java).putExtra("id", value.id))
    }
}

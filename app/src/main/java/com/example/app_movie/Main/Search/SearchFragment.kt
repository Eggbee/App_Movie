package com.example.app_movie.Main.Search

import android.arch.lifecycle.ViewModel
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.app_movie.Category.CategoryActivity
import com.example.app_movie.Info.InfoActivity
import com.example.app_movie.R
import com.example.app_movie.RecyclerItemClickListener

import java.util.ArrayList

class SearchFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInsrtanceState: Bundle?): View? {
        val layout = inflater.inflate(R.layout.fragment_search, container, false) as ViewGroup
        val recyclerView = layout.findViewById<View>(R.id.recycler_category) as RecyclerView
        val searchModels = ArrayList<SearchModel>()
        recyclerView.setHasFixedSize(true)
        val searchAdapter = SearchAdapter(activity!!, searchModels)
        recyclerView.layoutManager = LinearLayoutManager(context)
        val dividerItemDecoration = DividerItemDecoration(context!!, LinearLayoutManager(context).orientation)
        recyclerView.addItemDecoration(dividerItemDecoration)
        recyclerView.adapter = searchAdapter
        searchModels.add(SearchModel("드라마"))
        searchModels.add(SearchModel("판타지"))
        searchModels.add(SearchModel("서부"))
        searchModels.add(SearchModel("공포"))
        searchModels.add(SearchModel("로맨스"))
        searchModels.add(SearchModel("모험"))
        searchModels.add(SearchModel("스릴러"))
        searchModels.add(SearchModel("가족"))
        searchModels.add(SearchModel("느와르"))
        searchModels.add(SearchModel("컬트"))
        searchModels.add(SearchModel("다큐멘터리"))
        searchModels.add(SearchModel("코미디"))
        searchModels.add(SearchModel("가족"))
        searchModels.add(SearchModel("미스터리"))
        searchModels.add(SearchModel("전쟁"))
        searchModels.add(SearchModel("애니메이션"))
        searchModels.add(SearchModel("범죄"))
        recyclerView.addOnItemTouchListener(
            RecyclerItemClickListener(
                context!!,
                recyclerView,
                object : RecyclerItemClickListener.OnItemClickListener {
                    override fun onItemClick(view: View, position: Int) {
                        val intent = Intent(context, CategoryActivity::class.java)
                        intent.putExtra("position", position)
                        startActivity(intent)
                    }

                    override fun onLongItemClick(view: View?, position: Int) {

                    }
                })
        )
        return layout
    }
}

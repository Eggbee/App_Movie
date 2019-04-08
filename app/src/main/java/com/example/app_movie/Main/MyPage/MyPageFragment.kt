package com.example.app_movie.Main.MyPage

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.support.v7.widget.RecyclerView
import com.example.app_movie.Info.InfoData
import com.example.app_movie.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*


class MyPageFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInsrtanceState: Bundle?): View? {
        val layout = inflater.inflate(R.layout.fragment_my_page, container, false) as ViewGroup
        val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
        val firebaseDatabase: FirebaseDatabase = FirebaseDatabase.getInstance()
        val database: DatabaseReference = firebaseDatabase.reference
        val recycler_favorite = layout.findViewById<RecyclerView>(R.id.recycler_favorite)
        val myPageModel = ArrayList<MyPageModel>()
        val adapter = MyPageAdapter(activity!!, myPageModel)
        recycler_favorite.layoutManager = LinearLayoutManager(context)
        recycler_favorite.adapter = adapter
        database.child(firebaseAuth.uid.toString()).child("like").addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(dataSnapshot: DataSnapshot, s: String?) {
                val myPageModels = dataSnapshot.getValue(MyPageModel::class.java)
                if (myPageModels != null) {
                    myPageModel.add(myPageModels)
                }
                adapter.notifyDataSetChanged()
            }

            override fun onChildChanged(dataSnapshot: DataSnapshot, s: String?) {

            }

            override fun onChildRemoved(dataSnapshot: DataSnapshot) {

            }

            override fun onChildMoved(dataSnapshot: DataSnapshot, s: String?) {

            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })

        return layout
    }
}

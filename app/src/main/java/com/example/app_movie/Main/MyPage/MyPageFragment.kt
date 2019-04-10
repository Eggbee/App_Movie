package com.example.app_movie.Main.MyPage

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.support.v7.widget.RecyclerView
import android.widget.Button
import com.example.app_movie.Info.InfoData
import com.example.app_movie.LoginActivity
import com.example.app_movie.R
import com.example.app_movie.RecyclerItemClickListener
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
        val bt_logout=layout.findViewById<Button>(R.id.bt_logout)
        val recycler_favorite = layout.findViewById<RecyclerView>(R.id.recycler_favorite)
        val infoData = ArrayList<InfoData>()
        val adapter = MyPageAdapter(activity!!, infoData)
        recycler_favorite.layoutManager = LinearLayoutManager(context)
        recycler_favorite.adapter = adapter
        val dividerItemDecoration = DividerItemDecoration(context!!, LinearLayoutManager(context).orientation)
        recycler_favorite.addItemDecoration(dividerItemDecoration)
        bt_logout.setOnClickListener {
            firebaseAuth.signOut()
            activity!!.finish()
            startActivity(Intent(context, LoginActivity::class.java))
        }
        database.child(firebaseAuth.uid.toString()).child("like").addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(dataSnapshot: DataSnapshot, s: String?) {
                val infoDatas = dataSnapshot.getValue(InfoData::class.java)
                if (infoDatas != null) {
                    infoData.add(infoDatas)
                }
                adapter.notifyDataSetChanged()
            }

            override fun onChildChanged(dataSnapshot: DataSnapshot, s: String?) {

            }

            override fun onChildRemoved(dataSnapshot: DataSnapshot) {
                adapter.notifyDataSetChanged()
            }

            override fun onChildMoved(dataSnapshot: DataSnapshot, s: String?) {

            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
        recycler_favorite.addOnItemTouchListener(
            RecyclerItemClickListener(
                context!!,
                recycler_favorite,
                object : RecyclerItemClickListener.OnItemClickListener {
                    override fun onItemClick(view: View, position: Int) {
                    }

                    override fun onLongItemClick(view: View?, position: Int) {

                    }
                })
        )
        return layout
    }
}

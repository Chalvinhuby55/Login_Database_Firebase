package com.example.loginapplication

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.loginapplication.databinding.ActivityDetailBinding
import com.example.loginapplication.databinding.ActivityHomeBinding
import com.example.loginapplication.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage

class Home : AppCompatActivity() {
//
private lateinit var binding : ActivityHomeBinding
    private lateinit var dataRecyclerView: RecyclerView
    private lateinit var firebaseAuth: FirebaseAuth

    private lateinit var  wisataAdapter: wisataAdapter

    private lateinit var listWisata: MutableList<ItemData>
    private  var  mStorage: FirebaseStorage? = null
    private var mDatabaseRef: DatabaseReference? = null
    private var mDBListener: ValueEventListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_home)
        binding = ActivityHomeBinding.inflate(layoutInflater)
//        binding = ActivityHomeBinding.inflate(layoutInflater
//        binding = ActivityHomeBinding.inflate(LayoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        dataRecyclerView = findViewById(R.id.isiData)

        dataRecyclerView.setHasFixedSize(true)
//        dataRecyclerView.layoutManager = LinearLayoutManager(this@Home)
        dataRecyclerView.layoutManager = LinearLayoutManager(this@Home)
        binding.myDataLoaderprogressBar.visibility = View.VISIBLE

        listWisata = ArrayList()
        wisataAdapter = wisataAdapter(this@Home, listWisata)
        dataRecyclerView.adapter = wisataAdapter

        mStorage = FirebaseStorage.getInstance()
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("wisata")

        mDBListener = mDatabaseRef!!.addValueEventListener(object : ValueEventListener {

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@Home, error.message, Toast.LENGTH_SHORT).show()
                binding.myDataLoaderprogressBar.visibility = View.INVISIBLE
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                listWisata.clear()
                for (teacherSnapshot in snapshot.children) {
                    val upload = teacherSnapshot.getValue(ItemData::class.java)
                    upload!!.key = teacherSnapshot.key
                    listWisata.add(upload)
                }
                wisataAdapter.notifyDataSetChanged()
                binding.myDataLoaderprogressBar.visibility = View.GONE
            }

        })


        binding.btnLogOut.setOnClickListener {
            firebaseAuth.signOut()

            Intent(this, MainActivity::class.java).also {
                it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(it)
            }

        }

    }
        override fun onStart() {
        super.onStart()
        if (firebaseAuth.currentUser == null) {
            Intent(this, MainActivity::class.java).also {
                it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(it)
            }
            }
        }




}


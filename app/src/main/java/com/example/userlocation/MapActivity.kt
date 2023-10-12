package com.example.userlocation

import android.os.Bundle
import android.util.Log
import android.view.View.OnTouchListener
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.userlocation.data.UserDataRepository
import com.example.userlocation.data.UserDataViewModel
import com.example.userlocation.data.UserDataViewModelFactory
import com.example.userlocation.databinding.ActivityMapBinding
import com.example.userlocation.model.UserModelItem
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class MapActivity:AppCompatActivity(),OnMapReadyCallback
{

    private lateinit var binding:ActivityMapBinding
    private lateinit var mMap: GoogleMap
    private lateinit var userItemAdapter:UserItemRecyclerView
    private lateinit var userDataViewModel:UserDataViewModel
    private var userModelItem=emptyList<UserModelItem>()
    private var currentMarker: Marker? = null


    override fun onCreate(savedInstanceState:Bundle?)
    {
        super.onCreate(savedInstanceState)

        binding=DataBindingUtil.setContentView(this,R.layout.activity_map)
        val mapFragment = supportFragmentManager.findFragmentById(binding.map.id) as SupportMapFragment
        mapFragment.getMapAsync(this)
        userDataViewModel=ViewModelProvider(this,UserDataViewModelFactory(UserDataRepository()))[UserDataViewModel::class.java]
        userDataViewModel.getUserData()

        initialiseRV()
        observeUserData()
        initScrollListener()


    }

    private fun observeUserData()
    {
        userDataViewModel.userLiveData.observe(this) {
            userItemAdapter.addUserData(it)
            userModelItem=it

        }
    }

    override fun onMapReady(p0:GoogleMap)
    {
        mMap = p0


    }

    private fun initialiseRV()
    {


        userItemAdapter=UserItemRecyclerView()
        binding.rv.adapter=userItemAdapter


    }

    private fun initScrollListener()
    {
        Log.d("Yes", "onScrolled: called")
        binding.rv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                Log.d("Yes", "onScrolled: called 1")
                val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager
                val firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition()

                if (firstVisibleItemPosition != RecyclerView.NO_POSITION) {
                    val currentItem = userModelItem[firstVisibleItemPosition]

                    val latitude=currentItem.address.geo.lat.toDouble()
                    val longitude=currentItem.address.geo.lng.toDouble()
                    addMarker(latitude,longitude,currentItem.address.city)

                }
            }
        })
    }

    private fun addMarker(latitude:Double,longitude:Double,city:String)
    {
        if (currentMarker != null) {
            currentMarker!!.remove()
        }
        val latLng=LatLng(latitude,longitude)
        currentMarker=mMap.addMarker(MarkerOptions()
            .position(latLng)
            .title(city))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,2.0f))
    }

}
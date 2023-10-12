package com.example.userlocation

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.userlocation.databinding.ActivityMainBinding
import com.example.userlocation.util.SharedPreferenceHelper

class MainActivity:AppCompatActivity()
{
    private lateinit var sharedPreferences:SharedPreferenceHelper
    private lateinit var binding:ActivityMainBinding


    override fun onCreate(savedInstanceState:Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.activity=this


        sharedPreferences=SharedPreferenceHelper(this)
        goToMapActivity()
    }

    private fun  goToMapActivity()
    {
        val isFirstTimeLogin=sharedPreferences.getValue()
        if(!isFirstTimeLogin)
        {
            val intent=Intent(this,MapActivity::class.java)
            intent.flags=Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

    }

    fun checkInput()
    {
        val email=binding.etEmail.text.toString().trim()
        val password=binding.etPassword.text.toString().trim()

        if(email.isEmpty())
        {
            Toast.makeText(this, "email field cannot be empty", Toast.LENGTH_SHORT).show()
            return
        }

        if(password.isEmpty())
        {
            Toast.makeText(this, "password field cannot be empty", Toast.LENGTH_SHORT).show()
            return
        }

        if(email != "123" || password != "123")
        {
            Toast.makeText(this, "credential does not match", Toast.LENGTH_SHORT).show()
        }

        else
        {
            sharedPreferences.changeValue()
            val intent=Intent(this,MapActivity::class.java)
            startActivity(intent)
        }
    }
}
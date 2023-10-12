package com.example.userlocation.util

import android.content.Context
import android.content.SharedPreferences

class SharedPreferenceHelper(private val context:Context)
{
    private val sharedPreferences:SharedPreferences=
        context.getSharedPreferences(MY_PREFERENCE,Context.MODE_PRIVATE)

    fun getValue():Boolean
    {
        return sharedPreferences.getBoolean(FIRST_TIME_LOGIN,true)
    }

    fun changeValue()
    {
        val editor=sharedPreferences.edit()
        editor.putBoolean(FIRST_TIME_LOGIN,false)
        editor.apply()
    }

}
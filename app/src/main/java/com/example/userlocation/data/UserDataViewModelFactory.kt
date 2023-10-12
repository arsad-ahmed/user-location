package com.example.userlocation.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class UserDataViewModelFactory(private val repository: UserDataRepository):ViewModelProvider.Factory
{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return UserDataViewModel(repository) as T
    }

}

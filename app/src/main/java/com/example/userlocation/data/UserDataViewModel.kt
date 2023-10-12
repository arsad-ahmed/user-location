package com.example.userlocation.data
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.userlocation.model.UserModelItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class UserDataViewModel(private val userDataRepository:UserDataRepository):ViewModel()
{
    private val _userLiveData=MutableLiveData<List<UserModelItem>>()
    val userLiveData:LiveData<List<UserModelItem>>
        get()=_userLiveData

    fun getUserData()
    {
        viewModelScope.launch(Dispatchers.IO) {

            try
            {
                val response:Response<List<UserModelItem>> = userDataRepository.getUserData()
                if(response.isSuccessful)
                {
                    _userLiveData.postValue(response.body())
                }
                else
                {

                }
            }
            catch(e:Exception)
            {

            }
        }
    }
}
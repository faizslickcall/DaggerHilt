package com.example.daggerhilt.ui.mian

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.daggerhilt.data.model.User
import com.example.daggerhilt.data.repository.MainRepository
import com.example.daggerhilt.utils.NetworkHelper
import com.example.daggerhilt.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    private val _users = MutableLiveData<Resource<List<User>>>()
    val users:LiveData<Resource<List<User>>>
    get() = _users

    init {
        fetchUser()
    }

    private fun fetchUser(){
        viewModelScope.launch {
            _users.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConected()){
                mainRepository.getUsers().let {
                    if (it.isSuccessful){
                        _users.postValue(Resource.success(it.body()))
                    }else{
                        _users.postValue(Resource.error(it.errorBody().toString(),null))
                    }
                }
            }else{
                _users.postValue(Resource.error("No Internet Connection",null))
            }
        }
    }
}
package com.test.framework.mvvm.ui.main.viewmodel

import androidx.lifecycle.*
import com.test.framework.mvvm.data.model.Photo
import com.test.framework.mvvm.data.repository.MainRepository
import com.test.framework.mvvm.utils.NetworkHelper
import com.test.framework.mvvm.utils.Resource
import kotlinx.coroutines.launch

public class MainViewModel(
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    private val _users = MutableLiveData<Resource<List<Photo>>>()
    val users: LiveData<Resource<List<Photo>>>
        get() = _users

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            _users.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                mainRepository.getData().let {
                    if (it.isSuccessful) {
                        _users.postValue(Resource.success(it.body()))
                    } else _users.postValue(Resource.error(it.errorBody().toString(), null))
                }
            } else _users.postValue(Resource.error("No internet connection", null))
        }
    }
}
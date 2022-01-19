package com.example.androidplayground.anotherDaggerHiltDemo.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidplayground.anotherDaggerHiltDemo.di.RetroRepository
import com.example.androidplayground.anotherDaggerHiltDemo.model.DisplayList
import com.example.androidplayground.anotherDaggerHiltDemo.repository.ResultOf
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class DaggerHiltActivityViewModel @Inject constructor(private val retroRepository: RetroRepository/*private val retroRepositoryImpl: RetroRepositoryImpl*/) :
    ViewModel() {
    private var _liveDataList: MutableLiveData<ResultOf<DisplayList?>> = MutableLiveData()

    var liveDataList: LiveData<ResultOf<DisplayList?>> = _liveDataList


    /*fun getLiveDataObserver(): ResultOf<DisplayList> {
        return liveDataList
    }*/

    init {
        loadListOfData()
    }

    fun loadListOfData() {
        //retroRepositoryImpl.makeApiCall("ny", _liveDataList)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = retroRepository.getDataFromAPI("ny")


                if (response.isSuccessful) {
                    _liveDataList.postValue(ResultOf.Success(response.body())/*ResultOf.Success(response.data)*/)
                } else {
                    //Handle error
                    //response.errorBody()
                    //response.code()

                    _liveDataList.postValue(ResultOf.Failure("Error", null))
                }
            } catch (ioe: IOException) {
                _liveDataList.postValue(ResultOf.Failure("[IO] error please retry", ioe))
            } catch (he: HttpException) {
                _liveDataList.postValue(ResultOf.Failure("[HTTP] error please retry", he))
            }
        }
    }

    override fun onCleared() {
        Log.e("destroyed","viewmodel_true")
        super.onCleared()
        Log.e("destroyed","viewmodel")
    }
}
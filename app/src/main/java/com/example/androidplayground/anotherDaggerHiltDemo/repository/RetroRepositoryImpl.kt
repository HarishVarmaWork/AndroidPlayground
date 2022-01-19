package com.example.androidplayground.anotherDaggerHiltDemo.repository

/*class RetroRepositoryImpl @Inject constructor(private val retroInstance: RetroRepository) {*//*: RetroRepository{*//*
    *//*override fun getDataFromAPI(searchString: String): Call<DisplayList> {

    }*//*

    fun makeApiCall(query: String, liveData: MutableLiveData<DisplayList>) {
        val call: Call<DisplayList> = retroInstance.getDataFromAPI(query)
        call.enqueue(object : Callback<DisplayList> {
            override fun onResponse(call: Call<DisplayList>, response: Response<DisplayList>) {
                liveData.postValue(response.body())
            }

            override fun onFailure(call: Call<DisplayList>, t: Throwable) {
                liveData.postValue(null)
            }

        })
    }
}*/


sealed class ResultOf<out T> {
    data class Success<out R>(val value: R): ResultOf<R>()
    data class Failure(
        val message: String?,
        val throwable: Throwable?
    ): ResultOf<Nothing>()
    object Loading : ResultOf<Nothing>()//added this on my own
}
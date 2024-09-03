package br.com.outsera.piorfilmeawards.network

import br.com.outsera.piorfilmeawards.ui.PropertyModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("moviesjson.json")
    fun getAllData(): Call<PropertyModel>

}
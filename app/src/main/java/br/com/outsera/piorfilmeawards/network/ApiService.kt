package br.com.outsera.piorfilmeawards.network

import br.com.outsera.piorfilmeawards.ui.PropertyModel
import br.com.outsera.piorfilmeawards.ui.PropertyModelItem
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

interface ApiService {
    @GET("moviesjson.json")
    fun getAllData(): Call<PropertyModel>

}
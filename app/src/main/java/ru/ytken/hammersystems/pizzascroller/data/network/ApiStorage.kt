package ru.ytken.hammersystems.pizzascroller.data.network

import io.reactivex.rxjava3.core.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import ru.ytken.hammersystems.pizzascroller.data.model.DishGetParam

interface ApiStorage {

    companion object {
        private const val BASE_URL = "https://zoo-animal-api.herokuapp.com"

        val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        private val retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
        }

        val api: ApiStorage by lazy {
            retrofit.create(ApiStorage::class.java)
        }
    }

    @GET("/animals/rand/{number}")
    fun get(@Path(value = "number") number: Int) : Single<List<DishGetParam>>

}
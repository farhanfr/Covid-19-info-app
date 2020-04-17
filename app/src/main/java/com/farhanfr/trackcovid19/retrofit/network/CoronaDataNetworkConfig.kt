package com.farhanfr.trackcovid19.retrofit.network

import com.farhanfr.trackcovid19.retrofit.api.ApiCoronaService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class CoronaDataNetworkConfig {
    companion object {
        fun getNetwork(): Retrofit {
            val BASE_URL = "https://api.kawalcorona.com"
            val okHttp = OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(120, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
            return Retrofit.Builder().baseUrl(BASE_URL)
                .client(okHttp.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        public fun coronaApi(): ApiCoronaService {
            return getNetwork().create(ApiCoronaService::class.java)
        }
    }


}
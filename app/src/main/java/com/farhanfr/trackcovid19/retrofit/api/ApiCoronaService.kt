package com.farhanfr.trackcovid19.retrofit.api

import com.farhanfr.trackcovid19.retrofit.model.DetailLocalModel
import com.farhanfr.trackcovid19.retrofit.model.GlobalModel
import com.farhanfr.trackcovid19.retrofit.model.IndonesiaModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiCoronaService {

    //Get all indonesia data
    @GET("/indonesia")
    fun getIndonesiaData(): Call<List<IndonesiaModel>>

    //Get Global Recovered
    @GET("/sembuh")
    fun getGlobalRecovered(): Call<GlobalModel>

    //Get Global Recovered
    @GET("/positif")
    fun getGlobalConfirmed(): Call<GlobalModel>

    //Get Global Recovered
    @GET("/meninggal")
    fun getGlobalDeath(): Call<GlobalModel>

    //Get Indonesia Detail Data
    @GET("/indonesia/provinsi")
    fun getDetailIndonesia(): Call<List<DetailLocalModel>>

}
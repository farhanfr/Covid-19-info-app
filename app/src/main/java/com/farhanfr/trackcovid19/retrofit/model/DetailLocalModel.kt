package com.farhanfr.trackcovid19.retrofit.model

data class DetailLocalModel (
    val attributes : Attributes
)

data class Attributes(
    val FID:String?=null,
    val Provinsi:String?=null,
    val Kasus_Posi:String?=null,
    val Kasus_Semb:String?=null,
    val Kasus_Meni:String?=null
)

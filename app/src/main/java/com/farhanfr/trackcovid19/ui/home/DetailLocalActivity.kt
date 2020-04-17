package com.farhanfr.trackcovid19.ui.home

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.farhanfr.trackcovid19.R
import com.farhanfr.trackcovid19.adapter.DetailLocalAdapter
import com.farhanfr.trackcovid19.retrofit.model.DetailLocalModel
import com.farhanfr.trackcovid19.retrofit.network.CoronaDataNetworkConfig
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import kotlinx.android.synthetic.main.activity_detail_local.*
import kotlinx.android.synthetic.main.app_bar_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DetailLocalActivity : AppCompatActivity() {

    lateinit var detailLocalAdapter: DetailLocalAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_local)
        setSupportActionBar(toolbar)

        detailLocalAdapter = DetailLocalAdapter(this)
        rc_detailLocalTable.layoutManager = LinearLayoutManager(this)
        rc_detailLocalTable.adapter = detailLocalAdapter
        getTest()
        setupPieChart()

    }

    private fun setupPieChart() {
        val rainFall = listOf(98.8f,28.1f,30.5f)
        val month = listOf("Sembuh","Positif","Meninggal")
        val pieEntries:ArrayList<PieEntry> = arrayListOf()
        for (i in rainFall.indices) {
            pieEntries.add(PieEntry(rainFall[i],month[i]))
        }

        val dataSet = PieDataSet(pieEntries," ")
        dataSet.setColors(*ColorTemplate.COLORFUL_COLORS)
        val data = PieData(dataSet)

        //Get data chart
        val pieChart = findViewById<PieChart>(R.id.pchart_id)
        pieChart.data = data
        pieChart.invalidate()
    }

    private fun getTest() {
        CoronaDataNetworkConfig.coronaApi().getDetailIndonesia().enqueue(object : Callback<List<DetailLocalModel>>{
            override fun onFailure(call: Call<List<DetailLocalModel>>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(call: Call<List<DetailLocalModel>>, response: Response<List<DetailLocalModel>>
            ) {
                if (response.isSuccessful){
                        detailLocalAdapter.setDataDetail(response.body()!!)
                }
            }
        })
    }

}


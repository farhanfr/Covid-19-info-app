package com.farhanfr.trackcovid19.ui.home

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.app.Dialog
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.farhanfr.trackcovid19.R
import com.farhanfr.trackcovid19.retrofit.model.GlobalModel
import com.farhanfr.trackcovid19.retrofit.network.CoronaDataNetworkConfig
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.fragment_global_home.*
import kotlinx.android.synthetic.main.fragment_local_home.*
import kotlinx.android.synthetic.main.fragment_local_home.tv_lastUpdate
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class GlobalHomeFragment : Fragment() {

    val numberFormat = NumberFormat.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_global_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dialog = context?.let { Dialog(it, R.style.AppTheme_NoActionBar) }
        val view1 = activity!!.layoutInflater.inflate(R.layout.loading, null)
        dialog?.setContentView(view1)
        dialog?.setCancelable(false)
        dialog?.show()

        getDateNow()
        getGlobalRecoveredApi(dialog)
        getGlobalConfirmedApi(dialog)
        getGlobalDeathApi(dialog)
    }

    private fun getGlobalDeathApi(dialog: Dialog?) {
        CoronaDataNetworkConfig.coronaApi().getGlobalDeath().enqueue(object : Callback<GlobalModel>{
            override fun onFailure(call: Call<GlobalModel>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(call: Call<GlobalModel>, response: Response<GlobalModel>) {
                if (response.isSuccessful){
                        val numberDeath = numberFormat.parse(response.body()?.value.toString())
                        animationCount3(numberDeath!!.toInt())
                        dialog?.dismiss()
                }else {
                    Toast.makeText(activity, "error to get global death data", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        })
    }

    private fun getGlobalConfirmedApi(dialog: Dialog?) {
        CoronaDataNetworkConfig.coronaApi().getGlobalConfirmed().enqueue(object : Callback<GlobalModel>{
            override fun onFailure(call: Call<GlobalModel>, t: Throwable) {
                t.printStackTrace()
            }
            override fun onResponse(call: Call<GlobalModel>, response: Response<GlobalModel>) {
                if (response.isSuccessful){
                        val numberConfirmed = numberFormat.parse(response.body()?.value.toString())
                        animationCount2(numberConfirmed!!.toInt())
                        dialog?.dismiss()

                }else {
                    Toast.makeText(activity, "error to get global confirmed data", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        })
    }

    private fun getGlobalRecoveredApi(dialog: Dialog?) {
        CoronaDataNetworkConfig.coronaApi().getGlobalRecovered().enqueue(object : Callback<GlobalModel>{
            override fun onFailure(call: Call<GlobalModel>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(call: Call<GlobalModel>, response: Response<GlobalModel>) {
                if (response.isSuccessful){
                        val numberRecovered = numberFormat.parse(response.body()?.value.toString())
                        animationCount(numberRecovered!!.toInt())
                        dialog?.dismiss()

                }else {
                    Toast.makeText(activity, "error to get global recovered data", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        })
    }

    private fun animationCount(numberValue: Int) {
        val animatorValue = ValueAnimator.ofInt(0, numberValue)
        animatorValue.duration = 5000
        animatorValue.addUpdateListener { animation ->
            tv_dynamicRecoverCountG0?.text = animation.animatedValue.toString()
        }
        animatorValue.start()
    }

    private fun animationCount2(numberValue: Int) {
        val animatorValue = ObjectAnimator.ofInt(0, numberValue)
        animatorValue.duration = 5000
        animatorValue.addUpdateListener { animation ->
            tv_dynamicPositiveCountGO?.text = animation.animatedValue.toString().replace(',','.')
        }
        animatorValue.start()
    }

    private fun animationCount3(numberValue: Int) {
        val animatorValue = ValueAnimator.ofInt(0, numberValue)
        animatorValue.duration = 5000
        animatorValue.addUpdateListener { animation ->
            tv_dynamicDeathCountGO?.text = animation.animatedValue.toString()
        }
        animatorValue.start()
    }

    private fun getDateNow() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val current = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")
            val answer: String = current.format(formatter)
            tv_lastUpdate.text = answer

        } else {
            val date = Date();
            val formatter = SimpleDateFormat("MMM dd yyyy HH:mma")
            val answer: String = formatter.format(date)

        }

    }
}
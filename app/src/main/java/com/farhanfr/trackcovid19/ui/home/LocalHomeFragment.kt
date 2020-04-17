package com.farhanfr.trackcovid19.ui.home

import android.animation.ValueAnimator
import android.app.Dialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.farhanfr.trackcovid19.R
import com.farhanfr.trackcovid19.retrofit.model.IndonesiaModel
import com.farhanfr.trackcovid19.retrofit.network.CoronaDataNetworkConfig
import kotlinx.android.synthetic.main.fragment_local_home.*
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
class LocalHomeFragment : Fragment() {

    private var spinner:Spinner?=null
    private var arrayAdapter:ArrayAdapter<String>?=null
    private var itemList = arrayOf(
        "Green",
        "Blue"
    )


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_local_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dialog = context?.let { Dialog(it, R.style.AppTheme_NoActionBar) }
        val view1 = activity!!.layoutInflater.inflate(R.layout.loading,null)
        dialog?.setContentView(view1)
        dialog?.setCancelable(false)
        dialog?.show()

        spinner = view.findViewById(R.id.sp_action)
        arrayAdapter = ArrayAdapter(view.context,android.R.layout.simple_list_item_1,itemList)
        spinner?.adapter = arrayAdapter
        spinner!!.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                Toast.makeText(activity, "No change data", Toast.LENGTH_SHORT)
                    .show()
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                Toast.makeText(activity, "error to get indonesia data", Toast.LENGTH_SHORT)
                    .show()
            }

        }
        getDateNow()
        getIndonesiaDataApi(dialog)

    }

    private fun getIndonesiaDataApi(dialog: Dialog?) {
        CoronaDataNetworkConfig.coronaApi().getIndonesiaData()
            .enqueue(object : Callback<List<IndonesiaModel>> {
                override fun onFailure(call: Call<List<IndonesiaModel>>, t: Throwable) {
                    t.printStackTrace()
                }
                override fun onResponse(
                    call: Call<List<IndonesiaModel>>,
                    response: Response<List<IndonesiaModel>>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.forEach {
                            val numberFormat = NumberFormat.getInstance()
                            val numberRecovered = numberFormat.parse(it.sembuh.toString())
                            val numberPositive = numberFormat.parse(it.positif.toString())
                            val numberDeath = numberFormat.parse(it.meninggal.toString())
                            animationCount(
                                numberPositive!!.toInt(),
                                numberRecovered!!.toInt(),
                                numberDeath!!.toInt()
                            )
                            dialog?.dismiss()
                        }
                    } else {
                        Toast.makeText(activity, "error to get indonesia data", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            })
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

    private fun animationCount(
        numberPositive: Int,
        numberRecovered: Int,
        numberDeath: Int
    ) {
        val animatorRecovered = ValueAnimator.ofInt(0, numberRecovered)
        val animatorPositive = ValueAnimator.ofInt(0, numberPositive)
        val animatorDeath = ValueAnimator.ofInt(0, numberDeath)
        animatorRecovered.duration = 5000
        animatorPositive.duration = 5000
        animatorDeath.duration = 5000
        animatorRecovered.addUpdateListener { animation ->
            tv_dynamicRecoverCountLO?.text = animation.animatedValue.toString()
        }
        animatorPositive.addUpdateListener { animation ->
            tv_dynamicPositiveCountLO?.text = animation.animatedValue.toString()
        }
        animatorDeath.addUpdateListener { animation ->
            tv_dynamicDeathCountLO?.text = animation.animatedValue.toString()
        }
        animatorRecovered.start()
        animatorPositive.start()
        animatorDeath.start()
    }


}

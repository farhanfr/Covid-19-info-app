package com.farhanfr.trackcovid19.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.getColor
import androidx.recyclerview.widget.RecyclerView
import com.farhanfr.trackcovid19.R
import com.farhanfr.trackcovid19.retrofit.model.Attributes
import com.farhanfr.trackcovid19.retrofit.model.DetailLocalModel


class DetailLocalAdapter(val context: Context):RecyclerView.Adapter<DetailLocalAdapter.HolderData>() {

    var dataDetailLocal:List<DetailLocalModel> = listOf()
//    var dataDetailLocal2:List<Attributes> = lis

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int
    ): DetailLocalAdapter.HolderData {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_detail_local_item,parent,false)
        return HolderData(view)
    }

    override fun getItemCount(): Int {
        return dataDetailLocal.size + 1
    }

    override fun onBindViewHolder(holder: DetailLocalAdapter.HolderData, position: Int) {
        val rowPos = holder.adapterPosition
        if (rowPos == 0){
            holder.tv_no.text = "No"
            holder.tv_province.text = "Province"
            holder.tv_recovered.text = "Sembuh"
            holder.tv_confirmed.text = "Positif"
            holder.tv_death.text = "Meninggal"
            //Layout Setting
            holder.tv_no.setBackgroundResource(R.drawable.background_table_header)
            holder.tv_province.setBackgroundResource(R.drawable.background_table_header)
            holder.tv_recovered.setBackgroundResource(R.drawable.background_table_header)
            holder.tv_confirmed.setBackgroundResource(R.drawable.background_table_header)
            holder.tv_death.setBackgroundResource(R.drawable.background_table_header)

            holder.tv_no.setTextColor(context.resources.getColor(R.color.colorWhite))
            holder.tv_province.setTextColor(context.resources.getColor(R.color.colorWhite))
            holder.tv_recovered.setTextColor(context.resources.getColor(R.color.colorWhite))
            holder.tv_confirmed.setTextColor(context.resources.getColor(R.color.colorWhite))
            holder.tv_death.setTextColor(context.resources.getColor(R.color.colorWhite))

        }
        else{
            val getDetailLocalData = dataDetailLocal.get(rowPos-1)
            holder.tv_no.text = "1"
            holder.tv_province.text = getDetailLocalData.attributes.Provinsi
            holder.tv_recovered.text = getDetailLocalData.attributes.Kasus_Semb
            holder.tv_confirmed.text = getDetailLocalData.attributes.Kasus_Posi
            holder.tv_death.text = getDetailLocalData.attributes.Kasus_Meni

            holder.tv_no.setBackgroundResource(R.drawable.background_table_content)
            holder.tv_province.setBackgroundResource(R.drawable.background_table_content)
            holder.tv_recovered.setBackgroundResource(R.drawable.background_table_content)
            holder.tv_confirmed.setBackgroundResource(R.drawable.background_table_content)
            holder.tv_death.setBackgroundResource(R.drawable.background_table_content)
            holder.tv_no.setTextColor(context.resources.getColor(R.color.colorBlack))
            holder.tv_province.setTextColor(context.resources.getColor(R.color.colorBlack))
            holder.tv_recovered.setTextColor(context.resources.getColor(R.color.colorBlack))
            holder.tv_confirmed.setTextColor(context.resources.getColor(R.color.colorBlack))
            holder.tv_death.setTextColor(context.resources.getColor(R.color.colorBlack))
        }
    }

    fun setDataDetail(dataDetailLocal: List<DetailLocalModel>){
        this.dataDetailLocal = dataDetailLocal
        notifyDataSetChanged()
    }

    class HolderData(val view:View):RecyclerView.ViewHolder(view){
        val tv_no:TextView = view.findViewById(R.id.tv_no)
        val tv_province:TextView = view.findViewById(R.id.tv_province)
        val tv_recovered:TextView = view.findViewById(R.id.tv_recovered)
        val tv_confirmed:TextView = view.findViewById(R.id.tv_confirmed)
        val tv_death:TextView = view.findViewById(R.id.tv_death)
    }
}
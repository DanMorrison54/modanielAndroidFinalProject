package project.stn991516745.final_project_modaniel.ui.weights

import androidx.recyclerview.widget.RecyclerView
import project.stn991516745.final_project_modaniel.dbase.FreeWeights
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.list_item.view.*
import project.stn991516745.final_project_modaniel.R


class WeightsRecycler(private val sampleList: List<FreeWeights>): RecyclerView.Adapter<WeightsRecycler.MyViewHolder>() {
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val nameText: TextView =itemView.NameText
        val numText1: TextView =itemView.numText1
        val decimal :TextView=itemView.numTextDecimal
        val numText2 :TextView=itemView.numText2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.list_item,
            parent, false

        )
        return MyViewHolder(itemView)

    }

    override fun getItemCount() = sampleList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        if(sampleList.size!=0) {
            val currentItem = sampleList[position]
            holder.nameText.text ="Name "+ currentItem.fullName
            holder.numText1.text ="Max(lbs) "+ currentItem.maxWeightsAmt.toString()
            holder.decimal.text = "time(min) " +currentItem.minutes.toString()
            holder.numText2.text = "min(lbs) "+currentItem.minWeightsAmt.toString()
        }
        else {
            holder.nameText.text = "N/A"
            holder.numText1.text = "N/A"
            holder.decimal.text = "N/A"
            holder.numText2.text = "N/A"
        }

        }
    }


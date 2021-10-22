package com.example.part1diceroller

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


data class Result(
    val dice1: Int,
    val dice2: Int,
    val dice3: Int,
    val dice4 : Int) {
    constructor(dicesVals: Array<Int> ) : this(dicesVals[0], dicesVals[1], dicesVals[2], dicesVals[3]){}

}

object results {
    var items:MutableList<Result> = mutableListOf()
}
class ResultAdapter(
    private val context: Context,
    private val dataset: List<Result>
    ) : RecyclerView.Adapter<ResultAdapter.ItemViewHolder>()
{
    class ItemViewHolder(private val view: View) :RecyclerView.ViewHolder(view) {
        val textView1: TextView = view.findViewById(R.id.result1)
        val textView2: TextView = view.findViewById(R.id.result2)
        val textView3: TextView = view.findViewById(R.id.result3)
        val textView4: TextView = view.findViewById(R.id.result4)
    }

    override fun getItemCount() =
        dataset.size

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
            val adapterLayout =
                LayoutInflater.from(parent.context).inflate(R.layout.results, parent, false)
            return ItemViewHolder(adapterLayout)
        }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val Item =results.items[position]
        holder.textView1.text=results.items[position].dice1.toString()
        holder.textView2.text=results.items[position].dice2.toString()
        holder.textView3.text=results.items[position].dice3.toString()
        holder.textView4.text=results.items[position].dice4.toString()
    }
    }



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

    fun toMap() : Map<String, String> {

        return mapOf("dice1" to this.dice1.toString(),"dice2" to this.dice2.toString(),"dice3" to this.dice3.toString(),"dice4" to this.dice4.toString())
    }
}

object results: RemoteDataHandler {
    var items:MutableList<Result> = mutableListOf()

    fun loadItems() {
        val response = RemoteServices.get("https://6172ab9561ed900017c409d5.mockapi.io/:endpoint")
    }
    fun addIteem(result: Result) {
        RemoteServices.post("https://6172ab9561ed900017c409d5.mockapi.io/:endpoint", result.toMap(), this)
        this.items.add(result)
    }
    override onFinishLoadingData(response: String)
    {
        val i = 0
    }
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



package com.example.part1diceroller

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class ResultsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)

        findViewById<TextView>(R.id.resultsTv1).text = Reslts.dice1.tostring()
        findViewById<TextView>(R.id.resultsTv2).text = Reslts.dice2.tostring()
        findViewById<TextView>(R.id.resultsTv3).text = Reslts.dice3.tostring()
        findViewById<TextView>(R.id.resultsTv4).text = Reslts.dice4.tostring()
    }
}
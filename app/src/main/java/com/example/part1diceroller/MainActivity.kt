package com.example.part1diceroller

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {

    private var nbDices: Int = 4
    private var Dice = -1
    private lateinit var nbDicesLbl: TextView
    private lateinit var dicesTxtV: Array<TextView>
    private lateinit var diceImgV: Array<ImageView>
    private lateinit var recyclerView: RecyclerView
    private var isDiceStyleImg = true
    private var dicesViewVals:Array<Int> = Array(4, init = {
        0
    })
    private var results:MutableList<Result> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rollButton: Button = findViewById(R.id.button)
        rollButton.setOnClickListener { rollDice() }
        recyclerView = findViewById<RecyclerView>(R.id.ResultsView)
        recyclerView.adapter = ResultAdapter(this, results)
        //roll the dice onLoad
        rollDice()
    }
        /*nbDicesLbl = findViewById(R.id.lblNbDices)
        dicesTxtV = Array(4, init = {
            if (it == 0) {
                findViewById<TextView>(R.id.diceText1)
            } else if (it == 1) {
                findViewById<TextView>(R.id.diceText2)
            } else if (it == 2) {
                findViewById<TextView>(R.id.diceText3)
            } else if (it == 3) {
                findViewById<TextView>(R.id.diceText4)
            } else {
                findViewById<TextView>(R.id.diceText1)
            }
        })

        diceImgV = Array(4, init = {
            if (it == 0) {
                findViewById<ImageView>(R.id.Img1)
            } else if (it == 1) {
                findViewById<ImageView>(R.id.Img2)
            } else if (it == 2) {
                findViewById<ImageView>(R.id.Img3)
            } else if (it == 3) {
                findViewById<ImageView>(R.id.Img4)
            } else {
                findViewById<ImageView>(R.id.Img1)
            }
        })
    }*/

    /* fun btnSwitchDiceStyleClicked(sender: View) {
        isDiceStyleImg = !isDiceStyleImg
        findViewById<View>(R.id.dicStyleText).isVisible = isDiceStyleImg
        findViewById<View>(R.id.diceStyleImg).isVisible = isDiceStyleImg
    }*/

    /* val rollButton: Button = findViewById(R.id.button)
        rollButton.setOnClickListener {
            rollDice()
        }*/
    /*private fun btnRollOneClicked(sender: View) {
        if (Dice + 1 < nbDices) {
            Dice++
        } else {
            Dice = 0
            resetDice()
        }
        rollDice()
    }

    private fun btnRollAllClicked(sender: View) {
        for (i in 0 until nbDices) {
            rollDice()
        }
        Dice = 3
    }*/

    private fun btnDiceIncClicked(sender: View) {
        if (nbDices == 4) return
        diceImgV[nbDices].isVisible = true
        resetDice(nbDices)
        nbDices++
        nbDicesLbl.text = nbDices.toString()
        configureButtons()
    }

    private fun btnDicDecClicked(sender: View) {
        if (nbDices == 1) return
        dicesTxtV[nbDices - 1].text = ""
        diceImgV[nbDices - 1].isVisible = false
        nbDices--
        nbDicesLbl.text = nbDices.toString()
        configureButtons()
    }

    private fun rollDice() {
        // Create new Dice object with 6 sides and roll it
        val dice = Dice(6)
        val diceRoll = dice.roll()
        val dice2 = Dice(6)
        val diceRoll2 = dice.roll()
        val dice3 = Dice(6)
        val diceRoll3 = dice.roll()
        val dice4 =Dice(6)
        val diceRoll4 = dice.roll()

        val diceImage: ImageView = findViewById(R.id.Img1)
        val diceImage2: ImageView = findViewById(R.id.Img3)
        val diceImage3: ImageView = findViewById(R.id.Img2)
        val diceImage4: ImageView = findViewById(R.id.Img4)

        // Determine which drawable resource ID to use based on the dice roll
        val drawableResource = when (diceRoll) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
        val drawableResource2 = when (diceRoll2) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
        val drawableResource3 = when (diceRoll3) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
        val drawableResource4 = when (diceRoll4) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }

        diceImage.setImageResource(drawableResource)
        diceImage.contentDescription = diceRoll.toString()
        diceImage2.setImageResource(drawableResource2)
        diceImage2.contentDescription = diceRoll.toString()
        diceImage3.setImageResource(drawableResource3)
        diceImage3.contentDescription = diceRoll.toString()
        diceImage4.setImageResource(drawableResource4)
        diceImage4.contentDescription = diceRoll.toString()
    }
    private fun configureButtons() {
        findViewById<Button>(R.id.btn_inc).isEnabled = nbDices != 4
        findViewById<Button>(R.id.btn_dec).isEnabled = nbDices != 1
    }

    private fun getDiceDrawable(imgName: String): Drawable {
        return resources.getDrawable(
            resources.getIdentifier(
                "@drawable/dice$imgName",
                null,
                packageName
            )
        )

    }

    private fun resetDice() {
        for (i in 0 until nbDices) {
            resetDice()
        }

    }

    private fun resetDice(i: Int) {
        dicesTxtV[i].text = "-"
        diceImgV[i]
    }
}


    class Dice(private val numSides: Int) {

        fun roll(): Int {
            return (1..numSides).random()
        }
    }








        /*private fun rollDice(i: Int) {
        // Create new Dice object with 6 sides and roll it
        var d = (1..6).random()
        dicesTxtV[i].text = d.toString()
        diceImgV[i].setImageDrawable(getDiceDrawable(d.toString()))
    }*/

        /*val diceRoll = dice.roll()
        val diceImage: ImageView = findViewById(R.id.imageView)
        val drawableResource = when (diceRoll) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
        diceImage.setImageResource(drawableResource)
        diceImage.contentDescription = diceRoll.toString()
    }
}*/

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
import android.view.Surface
import android.view.SurfaceView
import java.util.*


class MainActivity : AppCompatActivity() {

    private var nbDices: Int = 4
    private var Dice = -1
    private lateinit var nbDicesLbl: TextView
    private lateinit var dicesTxtV: Array<TextView>
    private lateinit var diceImgV: Array<ImageView>
    private lateinit var recyclerView: RecyclerView
    private var isDiceStyleImg = true
    private var dicesViewVals: Array<Int> = Array(4, init = {
        0
    })

    private lateinit var camSurfaceView: SurfaceView
    private var results: MutableList<Result> = mutableListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //val rollButton: Button = findViewById(R.id.button)
        //rollButton.setOnClickListener { rollDice() }
        recyclerView = findViewById<RecyclerView>(R.id.ResultsView)
        recyclerView.adapter = ResultAdapter(this, results)
        //roll the dice onLoad
        //rollDice()

        Resultes.loadItems()

        nbDicesLbl = findViewById(R.id.lblnbDices)
        dicesTxtV = Array(4, init = {
            if (it == 0) {
                findViewById<TextView>(R.id.diceTv1)
            } else if (it == 1) {
                findViewById<TextView>(R.id.diceTv2)
            } else if (it == 2) {
                findViewById<TextView>(R.id.diceTv3)
            } else if (it == 3) {
                findViewById<TextView>(R.id.diceTv4)
            } else {
                findViewById<TextView>(R.id.diceTv1)
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

        /* camSurfaceView = findViewById<SurfaceView>(R.id.camSurfaceView)

        if (!CameraServices.checkPermission(this)) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CAMERA),
                1
            )

        }

        if (CameraServices.checkPermission(this)) {
            val cameraManager = this.getSystemService(Context.CAMERA_SERVICE) as CameraManager?
            val cameraIdList = Array<String>
            cameraIdList = cameraManager!!.cameraIdList

            if (cameraIdList.size != 0) {
                val cameraId = cameraIdList[0]
                cameraManager.openCamera(camera0Id, deviceCallback, null)

            }
        }*/

        CameraServices.loadSurface(this, findViewById<SurfaceView>(R.id.camSurfaceView))
    }

    fun btnResults(sender: View) {
        val intent = Intent(this, ResultsActivity:

        class.java).apply{
            putExtra("resIndex", resIndex)
        }
        startActivity(intent)
    }

    /* val rollButton: Button = findViewById(R.id.button)
        rollButton.setOnClickListener {
            rollDice()
        }*/
    private fun btnRollOneClicked(sender: View) {
        /*if (Dice + 1 < nbDices) {
            Dice++
        } else {
            Dice = 0
            resetDices()
        }
        rollDice(Dice)
        if (Dice == nbDices - 1)
            saveResult()*/
    }

    private fun btnRollAllClicked(sender: View) {
        for (i in 0 until nbDices - 1) {
            rollDice(i)
        }
        saveResult()
        Dice = 3
    }

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

    /*private fun rollDice() {
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
    }*/
    private fun rollDice(i: Int) {
        var y = (1..6).random()
        dicesViewVals[i] = y
        dicesTxtV[i].text = y.toString()
        diceImgV[i].setImageDrawable(getDiceDrawable(y.toString()))

    }

    private fun configureButtons() {
        findViewById<Button>(R.id.btn_inc).isEnabled = nbDices != 4
        findViewById<Button>(R.id.btn_dec).isEnabled = nbDices != 1
    }

    private fun getDiceDrawable(imgName: String): Drawable {
        return resources.getDrawable(
            resources.getIdentifier(
                "@drawable/dice_" + imgName,
                null,
                packageName
            ), theme
        )
    }


    private fun resetDices() {
        for (i in 0 until nbDices - 1) {
            resetDice(i)
        }

    }

    private fun resetDice(i: Int) {
        dicesViewVals = arrayOf(0)
        dicesTxtV[i].text = "-"
        diceImgV[i].setImageDrawable(getDiceDrawable("dice"))
    }

    private fun saveResult() {
        val r = Result(dicesViewVals)
        Results.addItem(r)
        recyclerView.adapter?notifyDataSetChanged()
    }

    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.channel_name)
            val descriptionText = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}

    /*var deviceCallback: CameraDevice.StateCallback = object : CameraDevice.State() {
        override fun onOpened(camera: CameraDevice) {
            val surface: Surface = camSurfaceView?.getHolder()!!.getSurface()

            val surfaceList: List<Surface> = Collections.singletonList(surface)
            try {
                camera.createCaptureSession(surfaceList, sessionCallback, null)
            } catch (e: CameraAccessException)

            return

        }

    }

    override fun onDisconnected(camera: CameraDevice) {

    }

    override fun onError(camera: CameraDevice, error: Int) {

    }
    var sessionCallback: CameraCaptureSession.StateCallback =
        object : CameraCaptureSession.StateCallback() {
            override fun onConfigured(session: CameraCaptureSession) {

            }
            override fun onConfiguredFailed(session: CameraCaptureSession) {

            }

        }*/




    class Dice(private val numSides: Int) {

        fun roll(): Int {
            return (1..numSides).random()
        }
    }








   /*   private fun rollDice(i: Int) {
        // Create new Dice object with 6 sides and roll it
       var d = (1..6).random()
        dicesTxtV[i].text = d.toString()
        diceImgV[i].setImageDrawable(getDiceDrawable(d.toString()))
    }

       val diceRoll = dice.roll()
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

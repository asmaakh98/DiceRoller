package com.example.part1diceroller

import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.part1diceroller.R
import com.google.firebase.messaging.FirebaseMessagingServic

class pushNotification : FirebaseMessagingService() {


    private val CHANNEL_ID = "1"
    override fun onNewToken(token: String) {
        super.onNewToken(token);

    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {

        var builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.dice_1)
            .setContentTitle(remoteMessage.notification!!.title)
            .setContentText(remoteMessage.notification!!.body)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        with(NotificationManagerCompat.from(this)){
            notify(3,builder.build())

        }
    }
}
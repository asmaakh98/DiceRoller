package com.example.part1diceroller

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.hardware.camera2.CameraAccessException
import android.hardware.camera2.CameraCaptureSession
import android.hardware.camera2.CameraDevice
import android.hardware.camera2.CameraManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import android.view.Surface
import android.view.SurfaceView
import java.util.*


object CameraServices {
    var camSurfaceView : SurfaceView? = null
    fun checkPermission(activity: AppCompatActivity) :Boolean {
        return ActivityCompat.checkSelfPermission(activity, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
    }
    fun requestedPermission(activity: AppCommpatActivity) {
        ActivityCompat.requestPermissions(
            activity,
            arrayOf(Manifest.permission.CAMERA),
            1
        )
    }
    fun initSurface(activity: AppCompatAtivity, csv: SurfaceView) {

        if(this.checkPermission(activity)) {
            initSurface(activity)
        } else {
            this.requestPermission(activity)
        }
    }

    fun initSurface(activity: AppCompatAtivity, csv: SurfaceView) {
        this.camSurfaceView = csv
        val cameraManager = this.getSystemService(Context.CAMERA_SERVICE) as CameraManager?
        val cameraIdList: Array<String>
        cameraIdList = cameraManager!!.cameraIdList

        if (cameraIdList.size != 0) {
            val camera0Id = cameraIdList[0]
            cameraManager.openCamera(camera0Id, deviceCallback, null)
        }
    }
}
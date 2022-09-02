package com.dillu.cheapflashlight

import android.content.Context
import android.hardware.camera2.CameraManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    lateinit var cameraM: CameraManager
    lateinit var switchFlash: ImageView
    var isFlash = false


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setLogo(R.drawable.ic_baseline_flash_on_24)
        supportActionBar!!.setDisplayUseLogoEnabled(true)

        cameraM = getSystemService(Context.CAMERA_SERVICE) as CameraManager

        switchFlash = findViewById(R.id.flashLight)
        switchFlash.setImageResource(R.drawable.flashlight_off)
        switchFlash.setOnClickListener{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                flashOnOrOff(it)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun flashOnOrOff(v: View?){
        if(!isFlash){
            val cameraListId = cameraM.cameraIdList[0]
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                cameraM.setTorchMode(cameraListId, true)
            }
            isFlash = true
            switchFlash.setImageResource(R.drawable.flashlight_on)
        }else{
            val cameraListId = cameraM.cameraIdList[0]
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                cameraM.setTorchMode(cameraListId, false)
            }
            isFlash = false
            switchFlash.setImageResource(R.drawable.flashlight_off)
        }
    }
}
















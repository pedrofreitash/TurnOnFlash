package com.freitaspedro.swtich

import android.annotation.SuppressLint
import android.content.Context
import android.hardware.camera2.CameraManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Switch
import android.widget.Toast
import androidx.annotation.RequiresApi

class MainActivity : AppCompatActivity() {
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sLampada = findViewById<Switch>(R.id.s_lampada)
        val img = findViewById<ImageView>(R.id.lampada)

        sLampada.setOnCheckedChangeListener{ _, isChecked ->
            if(isChecked){
                img.setImageResource(R.drawable.lightbulb_on)
                turnOnFlash(this)
            }
            else{
                img.setImageResource(R.drawable.ightbulb_off)
                turnOffFlash(this)
            }
        }
    }

    fun turnOnFlash(context: Context){
        val cameraManager = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            context.getSystemService(Context.CAMERA_SERVICE) as CameraManager
        } else {
            TODO("VERSION.SDK_INT < LOLLIPOP")
        }
        val cameraId = cameraManager.getCameraIdList()[0]
        try{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                cameraManager.setTorchMode(cameraId, true)
            }
        }catch (e: Exception){
            return
        }

    }

    fun turnOffFlash(context: Context){
        val cameraManager = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            context.getSystemService(Context.CAMERA_SERVICE) as CameraManager
        } else {
            TODO("VERSION.SDK_INT < LOLLIPOP")
        }
        val cameraId = cameraManager.getCameraIdList()[0]
        try{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                cameraManager.setTorchMode(cameraId, false)
            }
        }catch (e: Exception){
            return
        }

    }
}
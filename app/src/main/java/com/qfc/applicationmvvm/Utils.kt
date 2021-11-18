package com.qfc.applicationmvvm

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.View
import android.widget.Toast
import pub.devrel.easypermissions.EasyPermissions


fun<A : Activity> Activity.startNewActivity(activity: Class<A>){
    Intent(this, activity).also{

        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(it)
    }
}

fun View.enable(enabled: Boolean){
    isEnabled = enabled
    alpha = if(enabled) 1f else 0.5f
}

fun Context.toast(message:String) = Toast.makeText(this,message,Toast.LENGTH_LONG).show()


fun Context.hasStoragePermissions():Boolean{
    return  EasyPermissions.hasPermissions(
        this,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )
}

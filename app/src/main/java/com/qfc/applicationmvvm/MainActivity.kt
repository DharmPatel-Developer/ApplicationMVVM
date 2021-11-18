package com.qfc.applicationmvvm

import android.Manifest
import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.view.View
import com.qfc.applicationmvvm.addbook.ui.AddBookActivity
import com.qfc.applicationmvvm.databinding.ActivityMainBinding
import com.qfc.applicationmvvm.randomlist.ui.ListActivity
import java.io.*
import androidx.core.content.FileProvider

import com.qfc.applicationmvvm.Contants.Constants.Companion.PDF_NAME
import com.qfc.applicationmvvm.Contants.Constants.Companion.PDF_URL
import com.qfc.applicationmvvm.Contants.Constants.Companion.REQUEST_CODE_LOCATION_PERMISSION

import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions

import android.content.BroadcastReceiver
import android.content.IntentFilter


class MainActivity : AppCompatActivity(),EasyPermissions.PermissionCallbacks {

    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.progressBarPdf.visibility = View.GONE
        binding.addBook.setOnClickListener {
            startNewActivity( AddBookActivity::class.java)
        }

        binding.downLoadPDF.setOnClickListener {
            requestPermissions()
        }

        binding.randomList.setOnClickListener {
            startNewActivity( ListActivity::class.java)
        }

    }

    private fun requestPermissions() {
        if(this.hasStoragePermissions()) {
            downloadPDF()
        }
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            EasyPermissions.requestPermissions(
                this,
                "You need to accept storage permissions to use this feature.",
                REQUEST_CODE_LOCATION_PERMISSION,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
        } else {
            EasyPermissions.requestPermissions(
                this,
                "You need to accept storage permissions to use this feature.",
                REQUEST_CODE_LOCATION_PERMISSION,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
        }
    }



    var onComplete: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(ctxt: Context, intent: Intent) {
            pdfOpener()
        }
    }

    private fun downloadPDF() {
        val request = DownloadManager.Request(Uri.parse(PDF_URL))
        request.setTitle(PDF_NAME)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            request.allowScanningByMediaScanner()
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        }
        request.setDestinationInExternalPublicDir(
            Environment.DIRECTORY_DOWNLOADS,
            PDF_NAME
        )
        val downloadManager = getSystemService(DOWNLOAD_SERVICE) as DownloadManager
        request.setMimeType("application/pdf")
        request.allowScanningByMediaScanner()
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE or DownloadManager.Request.NETWORK_WIFI)
        downloadManager.enqueue(request)
        registerReceiver(onComplete,
             IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE)
        )
    }

    private fun pdfOpener(){

        val file = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).absolutePath
                + File.separator + PDF_NAME)

        val uri = FileProvider.getUriForFile(
            this@MainActivity,
            BuildConfig.APPLICATION_ID + ".provider",
            file
        )


        val intent = Intent(Intent.ACTION_VIEW)
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.setDataAndType(uri, "application/pdf")
        startActivity(intent)
    }


    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
       // downloadFile()
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        if(EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            AppSettingsDialog.Builder(this).build().show()
        } else {
            requestPermissions()
        }
    }

}
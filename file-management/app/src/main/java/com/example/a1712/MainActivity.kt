package com.example.a1712

import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.File
import android.Manifest
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var fileAdapter: FileAdapter
    private var currentDirectory: File = Environment.getExternalStorageDirectory()

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        if (checkPermission()) {
            displayFiles(currentDirectory)
        } else {
            requestPermission()
        }
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun checkPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            this, Manifest.permission.READ_MEDIA_AUDIO
        ) == PackageManager.PERMISSION_GRANTED
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.READ_MEDIA_AUDIO),
            100
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 100 && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            displayFiles(currentDirectory)
        } else {
            Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
            Log.d("Toast", "Permission Denied")
        }
    }

    private fun displayFiles(directory: File) {
        val files = directory.listFiles()?.toList() ?: emptyList()
        fileAdapter = FileAdapter(files) { file ->
            if (file.isDirectory) {
                currentDirectory = file
                displayFiles(file)
            } else {
                Log.d("Toast", "wtf")
                // Xử lý mở file văn bản
                if (file.extension == "txt") {
                    val intent = FileViewerActivity.newIntent(this, file.absolutePath)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Unsupported file type", Toast.LENGTH_SHORT).show()
                }
            }
        }
        recyclerView.adapter = fileAdapter
    }
}
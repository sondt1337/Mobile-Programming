package com.example.a1712

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.File

class FileViewerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file_viewer)

        val filePath = intent.getStringExtra(FILE_PATH_KEY)
        val fileContentTextView: TextView = findViewById(R.id.fileContent)

        if (filePath != null) {
            val file = File(filePath)
            fileContentTextView.text = file.readText()
        }
    }

    companion object {
        private const val FILE_PATH_KEY = "file_path"

        fun newIntent(context: Context, filePath: String): Intent {
            return Intent(context, FileViewerActivity::class.java).apply {
                putExtra(FILE_PATH_KEY, filePath)
            }
        }
    }
}

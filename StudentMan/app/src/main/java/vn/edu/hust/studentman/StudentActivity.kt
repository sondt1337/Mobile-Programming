package vn.edu.hust.studentman

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class StudentActivity : AppCompatActivity() {
    private lateinit var editTextName: EditText
    private lateinit var editTextId: EditText
    private var position: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student)

        editTextName = findViewById(R.id.editTextName)
        editTextId = findViewById(R.id.editTextId)

        val student = intent.getParcelableExtra<StudentModel>("student")
        position = intent.getIntExtra("position", -1)

        student?.let {
            editTextName.setText(it.studentName)
            editTextId.setText(it.studentId)
        }

        findViewById<Button>(R.id.buttonSave).setOnClickListener {
            val name = editTextName.text.toString()
            val id = editTextId.text.toString()
            
            val resultIntent = Intent()
            resultIntent.putExtra("student", StudentModel(name, id))
            if (position != -1) {
                resultIntent.putExtra("position", position)
            }
            
            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }
} 
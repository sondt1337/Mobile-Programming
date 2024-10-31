package com.example.bai_3

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bai_3.model.Student
import com.example.bai_3.adapter.StudentAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var studentAdapter: StudentAdapter
    private lateinit var studentList: List<Student>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // Sửa thành activity_main

        // Dữ liệu mẫu
        studentList = listOf(
            Student("123456", "Nguyễn Văn A"),
            Student("123457", "Trần Thị B"),
            Student("123458", "Lê Văn C")
        )

        // Thiết lập RecyclerView
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        studentAdapter = StudentAdapter(studentList)
        recyclerView.adapter = studentAdapter

        // Thiết lập ô tìm kiếm
        val editTextSearch = findViewById<EditText>(R.id.editTextSearch)
        editTextSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val query = s.toString()
                filterList(query)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    private fun filterList(query: String) {
        // Thực hiện lọc nếu độ dài từ khóa > 2, nếu không hiển thị toàn bộ danh sách
        val filteredList = if (query.length > 2) {
            studentList.filter {
                it.fullName.contains(query, ignoreCase = true) || it.mssv.contains(query, ignoreCase = true)
            }
        } else {
            studentList
        }

        // Cập nhật danh sách hiển thị trong Adapter
        studentAdapter.updateList(filteredList)
    }
}

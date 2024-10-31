package com.example.danhsachdongian

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
class MainActivity : AppCompatActivity() {
    private lateinit var editTextNumber: EditText
    private lateinit var radioGroup: RadioGroup
    private lateinit var buttonShow: Button
    private lateinit var listViewResult: ListView
    private lateinit var textViewError: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Áp dụng các thuộc tính hệ thống vào layout
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // Khởi tạo các thành phần giao diện
        editTextNumber = findViewById(R.id.editTextNumber)
        radioGroup = findViewById(R.id.radioGroup)
        buttonShow = findViewById(R.id.buttonShow)
        listViewResult = findViewById(R.id.listViewResult)
        textViewError = findViewById(R.id.textViewError)
        // Xử lý khi nhấn nút Show
        buttonShow.setOnClickListener {
            textViewError.visibility = TextView.GONE
            val inputText = editTextNumber.text.toString()
            // Kiểm tra nếu người dùng không nhập gì
            if (inputText.isEmpty()) {
                textViewError.text = "Vui lòng nhập số."
                textViewError.visibility = TextView.VISIBLE
                return@setOnClickListener
            }
            // Chuyển đổi dữ liệu nhập sang số nguyên và kiểm tra tính hợp lệ
            val n = inputText.toIntOrNull()
            if (n == null || n <= 0) {
                textViewError.text = "Vui lòng nhập số nguyên dương hợp lệ."
                textViewError.visibility = TextView.VISIBLE
                return@setOnClickListener
            }
            val resultList = ArrayList<String>()
            val selectedId = radioGroup.checkedRadioButtonId
            when (selectedId) {
                R.id.radioEven -> { // Số chẵn
                    for (i in 0..n step 2) {
                        resultList.add(i.toString())
                    }
                }
                R.id.radioOdd -> { // Số lẻ
                    for (i in 1..n step 2) {
                        resultList.add(i.toString())
                    }
                }
                R.id.radioSquare -> { // Số chính phương
                    var i = 0
                    while (i * i <= n) {
                        resultList.add((i * i).toString())
                        i++
                    }
                }
                else -> {
                    textViewError.text = "Vui lòng chọn loại số cần hiển thị."
                    textViewError.visibility = TextView.VISIBLE
                    return@setOnClickListener
                }
            }
            // Hiển thị danh sách kết quả trong ListView
            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, resultList)
            listViewResult.adapter = adapter
        }
    }
}
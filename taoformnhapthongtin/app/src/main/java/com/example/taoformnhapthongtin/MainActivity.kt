package com.example.taoformnhapthongtin

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var calendarView: CalendarView
    private lateinit var btnNgaySinh: Button
    private lateinit var tinhThanhSpinner: Spinner
    private lateinit var quanHuyenSpinner: Spinner
    private lateinit var phuongXaSpinner: Spinner
    private var selectedDate: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize CalendarView and Button
        calendarView = findViewById(R.id.calendar_view)
        btnNgaySinh = findViewById(R.id.btn_ngay_sinh)
        tinhThanhSpinner = findViewById(R.id.spinner_tinh_thanh)
        quanHuyenSpinner = findViewById(R.id.spinner_quan_huyen)
        phuongXaSpinner = findViewById(R.id.spinner_phuong_xa)

        // Set up the Button to show/hide the CalendarView
        btnNgaySinh.setOnClickListener {
            calendarView.visibility = if (calendarView.visibility == View.GONE) View.VISIBLE else View.GONE
        }

        // Set up CalendarView listener to capture selected date
        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val calendar = Calendar.getInstance()
            calendar.set(year, month, dayOfMonth)

            // Format the selected date as a string
            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            selectedDate = sdf.format(calendar.time)

            // Update the Button text with the selected date
            btnNgaySinh.text = selectedDate

            // Hide the CalendarView after selecting a date
            calendarView.visibility = View.GONE
        }

        // Spinner cho Tỉnh/Thành, Quận/Huyện, Phường/Xã
        setupSpinnerData()

        // Submit button
        val btnSubmit: Button = findViewById(R.id.btn_submit)
        btnSubmit.setOnClickListener {
            if (validateForm()) {
                Toast.makeText(this, "Thông tin đã được nộp!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Vui lòng điền đủ thông tin!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupSpinnerData() {
        // Dữ liệu giả lập cho Spinner Tỉnh/Thành
        val tinhThanhList = arrayListOf("Hà Nội", "TP. Hồ Chí Minh", "Đà Nẵng")
        val tinhThanhAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, tinhThanhList)
        tinhThanhAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        tinhThanhSpinner.adapter = tinhThanhAdapter

        // Dữ liệu giả lập cho Spinner Quận/Huyện
        val quanHuyenList = arrayListOf("Quận 1", "Quận 2", "Quận 3")
        val quanHuyenAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, quanHuyenList)
        quanHuyenAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        quanHuyenSpinner.adapter = quanHuyenAdapter

        // Dữ liệu giả lập cho Spinner Phường/Xã
        val phuongXaList = arrayListOf("Phường A", "Phường B", "Phường C")
        val phuongXaAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, phuongXaList)
        phuongXaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        phuongXaSpinner.adapter = phuongXaAdapter
    }

    private fun validateForm(): Boolean {
        val mssv = findViewById<EditText>(R.id.et_mssv).text.toString()
        val hoTen = findViewById<EditText>(R.id.et_ho_ten).text.toString()
        val email = findViewById<EditText>(R.id.et_email).text.toString()
        val soDienThoai = findViewById<EditText>(R.id.et_so_dien_thoai).text.toString()
        val cbDongY = findViewById<CheckBox>(R.id.cb_dong_y)

        return mssv.isNotEmpty() && hoTen.isNotEmpty() && email.isNotEmpty() &&
                soDienThoai.isNotEmpty() && cbDongY.isChecked
    }
}

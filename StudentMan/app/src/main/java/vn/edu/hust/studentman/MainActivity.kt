package vn.edu.hust.studentman

import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var listView: ListView
    private lateinit var studentList: ArrayList<StudentModel>
    private lateinit var adapter: ArrayAdapter<StudentModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.listViewStudents)
        studentList = ArrayList()
        
        // Thêm dữ liệu mẫu
        studentList.add(StudentModel("Nguyễn Văn A", "20200001"))
        studentList.add(StudentModel("Trần Thị B", "20200002"))

        adapter = ArrayAdapter(this, 
            android.R.layout.simple_list_item_1, 
            studentList)
        listView.adapter = adapter

        registerForContextMenu(listView)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menuAdd -> {
                val intent = Intent(this, StudentActivity::class.java)
                startActivityForResult(intent, REQUEST_ADD_STUDENT)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.menu_context, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        val position = info.position
        
        when (item.itemId) {
            R.id.menuEdit -> {
                val student = studentList[position]
                val intent = Intent(this, StudentActivity::class.java)
                intent.putExtra("student", student)
                intent.putExtra("position", position)
                startActivityForResult(intent, REQUEST_EDIT_STUDENT)
                return true
            }
            R.id.menuRemove -> {
                studentList.removeAt(position)
                adapter.notifyDataSetChanged()
                return true
            }
        }
        return super.onContextItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            when (requestCode) {
                REQUEST_ADD_STUDENT -> {
                    data?.getParcelableExtra<StudentModel>("student")?.let {
                        studentList.add(it)
                        adapter.notifyDataSetChanged()
                    }
                }
                REQUEST_EDIT_STUDENT -> {
                    val position = data?.getIntExtra("position", -1) ?: -1
                    data?.getParcelableExtra<StudentModel>("student")?.let {
                        if (position != -1) {
                            studentList[position] = it
                            adapter.notifyDataSetChanged()
                        }
                    }
                }
            }
        }
    }

    companion object {
        const val REQUEST_ADD_STUDENT = 1
        const val REQUEST_EDIT_STUDENT = 2
    }
}
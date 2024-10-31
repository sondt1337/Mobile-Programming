package com.example.bai_3.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bai_3.model.Student

class StudentAdapter(private var studentList: List<Student>) :
    RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    fun updateList(newList: List<Student>) {
        studentList = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(android.R.layout.simple_list_item_2, parent, false)
        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = studentList[position]
        holder.mssvTextView.text = student.mssv
        holder.nameTextView.text = student.fullName
    }

    override fun getItemCount() = studentList.size

    inner class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mssvTextView: TextView = itemView.findViewById(android.R.id.text1)
        val nameTextView: TextView = itemView.findViewById(android.R.id.text2)
    }
}

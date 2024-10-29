package com.example.studentlist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.studentlist.model.Student


class StudentAdapter(private val studentList: List<Student>) :
    RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {
    inner class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val studentInfoTextView: TextView = itemView.findViewById(android.R.id.text1)
    }

    private var filteredList: MutableList<Student> = studentList.toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(android.R.layout.simple_list_item_1, parent, false)
        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = filteredList[position]
        holder.studentInfoTextView.text = "Tên SV: ${student.name} MSSV: ${student.mssv}"
    }


    override fun getItemCount(): Int {
        return filteredList.size
    }

    fun filter(query: String) {
        filteredList.clear()
        if (query.length >= 2) {
            val lowerCaseQuery = query.lowercase()
            filteredList.addAll(
                studentList.filter {
                    it.name.lowercase().contains(lowerCaseQuery) ||
                            it.mssv.lowercase().contains(lowerCaseQuery)
                }
            )
        } else {
            filteredList.addAll(studentList)
        }
        notifyDataSetChanged()
    }


}

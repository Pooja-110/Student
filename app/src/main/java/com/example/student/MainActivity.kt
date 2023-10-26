package com.example.student

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var buttonAddTask: FloatingActionButton
    private lateinit var recyclerView: RecyclerView
    private lateinit var studentAdapter: StudentAdapter
    private lateinit var studentViewModel: StudentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        studentAdapter = StudentAdapter(this, emptyList())
        recyclerView.adapter = studentAdapter

        buttonAddTask = findViewById(R.id.addButton)
        buttonAddTask.setOnClickListener {
            val intent = Intent(this@MainActivity, RegisterActivity::class.java)
            startActivity(intent)
        }

        studentViewModel = ViewModelProvider(this).get(StudentViewModel::class.java)

        studentViewModel.allStudents.observe(this, Observer<List<Student>> { students ->
            studentAdapter.setStudents(students)
        })
    }

    fun onUpdateButtonClick(student: Student) {
        val intent = Intent(this, RegisterActivity::class.java)
        intent.putExtra("student", student)
        startActivity(intent)
    }

    fun onDeleteButtonClick(student: Student) {
        studentViewModel.delete(student)
    }

}

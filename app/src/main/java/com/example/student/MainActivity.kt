package com.example.student

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
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
    private lateinit var searchView: SearchView

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

        searchView = findViewById(R.id.searchView)


        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                studentAdapter.filter.filter(newText)
                return true
            }
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

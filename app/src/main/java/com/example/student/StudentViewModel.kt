package com.example.student

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
class StudentViewModel(application: Application) : AndroidViewModel(application) {
    private val studentRepository: StudentRepository
    val allStudents: LiveData<List<Student>>

    init {
        val studentDao = StudentDatabase.getDatabase(application).studentDao()
        studentRepository = StudentRepository(studentDao)
        allStudents = studentRepository.allStudents
    }

    fun insert(student: Student) = viewModelScope.launch(Dispatchers.IO) {
        studentRepository.insert(student)
    }

    fun delete(student: Student) {
        viewModelScope.launch(Dispatchers.IO) {
            studentRepository.delete(student)
        }
    }

    fun updateStudent(student: Student) {
        viewModelScope.launch(Dispatchers.IO) {
            studentRepository.update(student)
        }
    }


}

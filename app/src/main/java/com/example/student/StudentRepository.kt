package com.example.student

import androidx.lifecycle.LiveData

class StudentRepository(private val studentDao: StudentDao) {
    val allStudents: LiveData<List<Student>> = studentDao.getAllStudents()

    suspend fun insert(student: Student) {
        studentDao.insert(student)
    }

    suspend fun delete(student: Student) {
        studentDao.deleteUser(student)
    }

    suspend fun update(student: Student) {
        studentDao.update(student)
    }
}


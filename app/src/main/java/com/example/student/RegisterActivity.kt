package com.example.student

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

class RegisterActivity : AppCompatActivity() {

    private lateinit var studentViewModel: StudentViewModel
    private lateinit var nameEditText: EditText
    private lateinit var studentid: EditText
    private lateinit var fathername: EditText
    private lateinit var ageEditText: EditText
    private lateinit var contact: EditText
    private lateinit var course: EditText
    private lateinit var coursedate: EditText
    private lateinit var gender: EditText
    private lateinit var saveButton: Button
    private var studentToUpdate: Student? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        studentViewModel = ViewModelProvider(this).get(StudentViewModel::class.java)

        nameEditText = findViewById(R.id.nameEditText)
        studentid = findViewById(R.id.SID)
        fathername = findViewById(R.id.Fathername)
        ageEditText = findViewById(R.id.ageEditText)
        contact = findViewById(R.id.Contact)
        course = findViewById(R.id.course)
        coursedate = findViewById(R.id.csdate)
        gender = findViewById(R.id.Gender)
        saveButton = findViewById(R.id.saveButton)

        // Check if a Student object is passed as an extra (for updating)
        val student = intent.getParcelableExtra("student") as? Student
        if (student != null) {
            studentToUpdate = student
            // Populate the form fields with existing data
            nameEditText.setText(student.name)
            studentid.setText(student.sid)
            fathername.setText(student.father)
            ageEditText.setText(student.age)
            contact.setText(student.num)
            course.setText(student.course)
            coursedate.setText(student.coursedateText)
            gender.setText(student.genderText)
        }

        saveButton.setOnClickListener {
            if (student != null) {

                updateStudent(student)
            } else {

                saveNewStudent()
            }
        }
    }

    private fun saveNewStudent() {
        val name = nameEditText.text.toString().trim()
        val id = studentid.text.toString().trim()
        val father = fathername.text.toString().trim()
        val ageText = ageEditText.text.toString().trim()
        val num = contact.text.toString().trim()
        val courseText = course.text.toString().trim()
        val coursedateText = coursedate.text.toString().trim()
        val genderText = gender.text.toString().trim()

        if (name.isNotEmpty() && id.isNotEmpty() && father.isNotEmpty() && ageText.isNotEmpty()
            && num.isNotEmpty() && courseText.isNotEmpty() && coursedateText.isNotEmpty() && genderText.isNotEmpty()
        ) {
            val student = Student(0, name, id, father, ageText, num, courseText, coursedateText, genderText)

            studentViewModel.insert(student)

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun updateStudent(student: Student) {

        val updatedStudent = Student(
            student.id,
            nameEditText.text.toString().trim(),
            studentid.text.toString().trim(),
            fathername.text.toString().trim(),
            ageEditText.text.toString().trim(),
            contact.text.toString().trim(),
            course.text.toString().trim(),
            coursedate.text.toString().trim(),
            gender.text.toString().trim()
        )

        studentViewModel.updateStudent(updatedStudent)

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}

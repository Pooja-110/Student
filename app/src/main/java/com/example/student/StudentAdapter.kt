package com.example.student
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StudentAdapter(private val mainActivity: MainActivity, private var students: List<Student>) :
    RecyclerView.Adapter<StudentAdapter.StudentViewHolder>(), Filterable {

    private var filteredStudents: List<Student> = students

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_student, parent, false)
        return StudentViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val currentStudent = filteredStudents[position]
        holder.bind(currentStudent)
    }

    override fun getItemCount(): Int {
        return filteredStudents.size
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filteredList = mutableListOf<Student>()
                if (constraint.isNullOrBlank()) {
                    filteredList.addAll(students)
                } else {
                    val filterPattern = constraint.toString().toLowerCase().trim()
                    for (student in students) {
                        if (student.name.toLowerCase().contains(filterPattern)) {
                            filteredList.add(student)
                        }
                    }
                }
                val results = FilterResults()
                results.values = filteredList
                return results
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                if (results?.values is List<*>) {
                    filteredStudents = results.values as List<Student>
                } else {
                    filteredStudents = emptyList()
                }
                notifyDataSetChanged()
            }
        }
    }

    inner class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        private val ageTextView: TextView = itemView.findViewById(R.id.ageTextView)
        private val fatherNameTextView: TextView = itemView.findViewById(R.id.fname)
        private val contactTextView: TextView = itemView.findViewById(R.id.Contact)
        private val courseTextView: TextView = itemView.findViewById(R.id.course)
        private val genderTextView: TextView = itemView.findViewById(R.id.gender)
        private val coursestartdate: TextView = itemView.findViewById(R.id.csdate)
        private val studID: TextView = itemView.findViewById(R.id.sid)
        private val updateButton: TextView = itemView.findViewById(R.id.updateButton)
        private val deleteButton: TextView = itemView.findViewById(R.id.deleteButton)

        fun bind(student: Student) {
            nameTextView.text = "Name: ${student.name}"
            ageTextView.text = "Age: ${student.age}"
            fatherNameTextView.text = "Father's Name: ${student.father}"
            contactTextView.text = "Contact: ${student.num}"
            courseTextView.text = "Course: ${student.course}"
            genderTextView.text = "Gender: ${student.genderText}"
            coursestartdate.text = "Course Date: ${student.coursedateText}"
            studID.text = "Student ID: ${student.sid}"
            updateButton.setOnClickListener {
                mainActivity.onUpdateButtonClick(student)
            }


            deleteButton.setOnClickListener {
                mainActivity.onDeleteButtonClick(student)
            }
        }

    }


    fun setStudents(students: List<Student>) {
        this.students = students
        this.filteredStudents = students
        notifyDataSetChanged()
    }
}

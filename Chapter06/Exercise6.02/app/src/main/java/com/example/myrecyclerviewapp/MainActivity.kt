package com.example.myrecyclerviewapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myrecyclerviewapp.model.EmployeeRole
import com.example.myrecyclerviewapp.model.EmployeeUiModel
import com.example.myrecyclerviewapp.model.Gender
import kotlinx.android.synthetic.main.activity_main.recycler_view as recyclerView

class MainActivity : AppCompatActivity() {
    private val employeesAdapter by lazy { EmployeesAdapter(layoutInflater, GlideImageLoader(this)) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView.adapter = employeesAdapter
        recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        employeesAdapter.setData(
            listOf(
                EmployeeUiModel(
                    "Robert",
                    "Rose quickly through the organization",
                    EmployeeRole.Management,
                    Gender.Male,
                    "https://images.pexels.com/photos/220453/pexels-photo-220453.jpeg?auto=compress&cs=tinysrgb&h=650&w=940"
                ),
                EmployeeUiModel(
                    "Wilma",
                    "A talented developer",
                    EmployeeRole.Technology,
                    Gender.Female,
                    "https://images.pexels.com/photos/3189024/pexels-photo-3189024.jpeg?auto=compress&cs=tinysrgb&h=650&w=940"
                ),
                EmployeeUiModel(
                    "Curious George",
                    "Excellent at retention",
                    EmployeeRole.HumanResources,
                    Gender.Unknown,
                    "https://images.pexels.com/photos/771742/pexels-photo-771742.jpeg?auto=compress&cs=tinysrgb&h=750&w=1260"
                )
            )
        )
    }
}

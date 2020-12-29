package com.example.planetquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), AnswersListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fragment_container?.let { frameLayout ->

            val questionsFragment = QuestionsFragment()

            supportFragmentManager.beginTransaction()
                .add(frameLayout.id, questionsFragment).commit()
        }
    }

    override fun onSelected(questionId: Int) {

        fragment_container?.let {frameLayout ->

            val answersFragment = AnswersFragment.newInstance(questionId)

            supportFragmentManager.beginTransaction()
                .replace(frameLayout.id, answersFragment)
                .addToBackStack(null)
                .commit()
        }
    }
}

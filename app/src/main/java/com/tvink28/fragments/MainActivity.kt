package com.tvink28.fragments

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.tvink28.fragments.task_one.TaskOneActivity
import com.tvink28.fragments.task_two.TaskTwoActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnTaskOne = findViewById<Button>(R.id.taskOne)
        val btnTaskTwo = findViewById<Button>(R.id.taskTwo)

        btnTaskOne.setOnClickListener {
            val intent = Intent(this, TaskOneActivity::class.java)
            startActivity(intent)
        }

        btnTaskTwo.setOnClickListener {
            val intent = Intent(this, TaskTwoActivity::class.java)
            startActivity(intent)
        }
    }
}
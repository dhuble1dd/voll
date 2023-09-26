package com.example.voll

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity



class ShowHintActivity: AppCompatActivity(){

    lateinit var hintText: TextView
    val vollViewModel : VollViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_hint)
        val index = intent.getIntExtra("index", 0)
        var hintCounter = intent.getIntExtra("hintCounter", 0)
        hintText = findViewById(R.id.hintText)
        hintText.text = "Correct answer is ${vollViewModel.strList[index].answer}"
        val backBtn = findViewById<Button>(R.id.returnBtn)
        val hintButton = findViewById<Button>(R.id.hintButton)
        hintButton.setOnClickListener{
            Log.d("hehe", "${hintCounter}")
            hintCounter++
            hintText.visibility = View.VISIBLE
            hintButton.visibility = View.INVISIBLE
            Log.d("hehe", "${hintCounter}")
        }
        backBtn.setOnClickListener{
            hintText.visibility = View.GONE
            hintButton.visibility = View.GONE
            intent.putExtra("hintCounter", vollViewModel.hintCounter)
            setResult(Activity.RESULT_OK, intent.putExtra("hintCounter", hintCounter))
            finish()

        }
    }
}



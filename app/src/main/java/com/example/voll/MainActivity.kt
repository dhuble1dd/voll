package com.example.voll

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.core.view.children
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.voll.databinding.ActivityMainBinding
import java.util.UUID
import java.util.concurrent.RecursiveAction
import kotlin.math.log
import kotlin.properties.Delegates
import kotlin.reflect.KProperty


class Delegate{
    operator fun getValue(propRef: Any?, prop: KProperty<*>):String{
        return "propRef ${propRef} and prop ${prop.name}"
    }
    operator fun setValue(propRef: Any?, prop: KProperty<*>, value: String): String{
        return "200"
    }
}



class MainActivity : AppCompatActivity() {


    // lazy, observable
//    val ourList = mutableListOf<String>()
//
//    var pablo: String by Delegate()
//    val ppp: String by lazy{
//       Log.d("HEHEHE", "EXECUTING LAMBDA")
//       "200"
//    }
//
//    var ooo: String by Delegates.observable("Pablo"){a,b,c->
//        Log.d("HEHEHE", "something is changed")
//    }


    val vollViewModel : VollViewModel by viewModels()

//    lateinit var text: TextView

//    fun updateText(){
//        text.setText(vollViewModel.strList[vollViewModel.index].question)
//    }

//    lateinit var counter: TextView
//    lateinit var buttonAgain: Button
//    lateinit var buttonPrev: Button
//    lateinit var buttonNext: Button

//    fun updateCounter(){
//        counter.setText("Correct answers ${vollViewModel.correctAns} from 10" +
//                "right answer is ${vollViewModel.strList[vollViewModel.index].answer}")
////        counter.setText(" ${vollViewModel.ans} ")
//    }
//    fun compairAns(){
//        if (vollViewModel.ans == vollViewModel.strList[vollViewModel.index].answer) {
//            vollViewModel.correctAns++
////            updateCounter()
//        }
////        vollViewModel.correctAns++
//    }
//    var hintCounter = 0

//    fun claimTheResult(){
//        compairAns()
//        if (vollViewModel.index==vollViewModel.strList.lastIndex){
//            text.setText("Correct answers ${vollViewModel.correctAns} from ${vollViewModel.strList.count()}" +
//                    "\n hint used ${hintCounter}")
//            counter.setText(when(vollViewModel.correctAns){
//                10 -> "excellent"
//                7,8,9 -> "good"
//                4,5,6 -> "normal"
//                1,2,3 -> "bad"
//                0 -> "you knew all answers"
//                else -> "hehehe"
//            })
//            buttonAgain.text = "Try again"
//            buttonNext.isEnabled = false
//            buttonPrev.isEnabled = false
//
//        } else {
//            vollViewModel.incIndex()
//            updateText()
////            updateCounter()
//        }
//    }

//    val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
//
//    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (data != null) {
//            hintCounter = data.getIntExtra("hintCounter", 0)
//        }
//    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction().apply {
            replace (R.id.fragmentContainerView, RecyclerViewFragment())
            commit()
        }
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController

//        text = findViewById(R.id.textView)
//        updateText()
//        counter = findViewById(R.id.centerText)
//        updateCounter()
//        val recy = binding.recy

//        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
//        val layoutManager = GridLayoutManager(this,2, GridLayoutManager.VERTICAL, false)



//        pablo = "300"
//        Log.d("HEHEHE", "${pablo}")

//        buttonPrev = findViewById(R.id.button)
//        buttonPrev.setOnClickListener {
//            vollViewModel.ans = true
//            claimTheResult()
//        }

//        buttonNext = findViewById(R.id.button2)
//        buttonNext.setOnClickListener {
//            if (vollViewModel.index==vollViewModel.strList.lastIndex){
//                buttonNext.isEnabled = false
//            }
//            vollViewModel.ans = false
//            claimTheResult()
//        }

//        buttonAgain = findViewById(R.id.button3)
//        buttonAgain.text = "Get the answer"
//        buttonAgain.setOnClickListener {
//            if (vollViewModel.index==vollViewModel.strList.lastIndex) {
//                vollViewModel.index = 0
//                vollViewModel.correctAns = 0
//                claimTheResult()
//                buttonAgain.text = "Get the answer"
//                hintCounter = 0
//                buttonNext.isEnabled = true
//                buttonPrev.isEnabled = true
//                counter.text = ""
//            } else {
//                val  intent = Intent(this, ShowHintActivity::class.java)
//                intent.putExtra("index", vollViewModel.index)
//                intent.putExtra("hintCounter", hintCounter)
//                launcher.launch(intent)
//
//            }
//            buttonAgain.setVisibility(View.INVISIBLE)
//        }
    }
}

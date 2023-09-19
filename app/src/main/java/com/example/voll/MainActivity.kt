package com.example.voll

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.SavedStateViewModelFactory
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

    var pablo: String by Delegate()
    val ppp: String by lazy{
       Log.d("HEHEHE", "EXECUTING LAMBDA")
       "200"
    }

    var ooo: String by Delegates.observable("Pablo"){a,b,c->
        Log.d("HEHEHE", "something is changed")
    }


    val vollViewModel : VollViewModel by viewModels()

    lateinit var text: TextView

    fun updateText(){
        text.setText(vollViewModel.strList[vollViewModel.index].question)
    }

    lateinit var counter: TextView
    lateinit var buttonAgain: Button

    fun updateCounter(){
        counter.setText("Correct answers ${vollViewModel.correctAns} from 10" +
                "right answer is ${vollViewModel.strList[vollViewModel.index].answer}")
//        counter.setText(" ${vollViewModel.ans} ")
    }
    fun compairAns(){
        if (vollViewModel.ans == vollViewModel.strList[vollViewModel.index].answer) {
            vollViewModel.correctAns++
//            updateCounter()
        }
//        vollViewModel.correctAns++
    }


    fun claimTheResult(){
        compairAns()
        if (vollViewModel.index==vollViewModel.strList.lastIndex){
            text.setText("Correct answers ${vollViewModel.correctAns} from ${vollViewModel.strList.count()}")
            counter.setText(when(vollViewModel.correctAns){
                10 -> "exelent"
                7,8,9 -> "good"
                4,5,6 -> "normal"
                1,2,3 -> "bad"
                0 -> "you knew all answers"
                else -> "hehehe"
            })
            buttonAgain.setVisibility(View.VISIBLE)

        } else {
            vollViewModel.incIndex()
            updateText()
            updateCounter()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        text = findViewById(R.id.textView)
        updateText()
        counter = findViewById(R.id.centerText)
        updateCounter()

        pablo = "300"
        Log.d("HEHEHE", "${pablo}")

        val buttonPrev: Button = findViewById(R.id.button)
        buttonPrev.setOnClickListener {
            vollViewModel.ans = true
            claimTheResult()
        }

        val buttonNext: Button = findViewById(R.id.button2)
        buttonNext.setOnClickListener {
//            Log.d("HEHEHE", "ppp is ${ppp}")
//            ooo = "${vollViewModel.index} + a"
            vollViewModel.ans = false
            claimTheResult()
        }

        buttonAgain = findViewById(R.id.button3)
        buttonAgain.setOnClickListener {
            vollViewModel.index = 0
            vollViewModel.correctAns = 0
            claimTheResult()
            buttonAgain.setVisibility(View.INVISIBLE)
        }
    }
}

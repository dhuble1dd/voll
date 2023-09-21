package com.example.voll

import android.annotation.SuppressLint
import android.content.Context
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
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.core.view.children
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.SavedStateViewModelFactory
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

class OurRecyclerAdapter(val context: Context, val elements: MutableList<String>): RecyclerView.Adapter<OurRecyclerAdapter.ViewHolder>() {

    inner class  ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val textView = view.findViewById<TextView>(R.id.uuid)
//        val container = view.findViewById<LinearLayout>(R.id.uubox)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.recy_item, parent, false)
        val viewHolder = ViewHolder(view)
        return viewHolder
    }

    override fun getItemCount(): Int {
        return elements.size
    }

//    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = elements[position]
        if(position%2 == 0){
            holder.textView.setBackgroundColor(ContextCompat.getColor(context,R.color.colorPrimary))
        }
        else {
            holder.textView.setBackgroundColor(ContextCompat.getColor(context,R.color.colorAccent))
        }
        Log.d("hehehe", "${position%2==0}")
    }

}

class MainActivity : AppCompatActivity() {


    // lazy, observable
    val ourList = mutableListOf<String>()

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
                10 -> "excellent"
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
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        text = findViewById(R.id.textView)
        updateText()
        counter = findViewById(R.id.centerText)
        updateCounter()
        val recy = binding.recy

        ourList.addAll((1..30).map{UUID.randomUUID().toString()})

        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
//        val layoutManager = GridLayoutManager(this,2, GridLayoutManager.VERTICAL, false)

        recy.layoutManager = LinearLayoutManager(this)
        recy.adapter = OurRecyclerAdapter(this, ourList)
        recy.addItemDecoration(DividerItemDecoration(baseContext,layoutManager.orientation))

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

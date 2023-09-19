package com.example.voll

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

data class Quiz(val question: String, val answer: Boolean)
val q1 = Quiz("The Pacific Ocean is the largest ocean?", true)
val q2 = Quiz("Barack Obama is the current USA President?", false)
val q3 = Quiz("Is Muhammad Ali Olympic champion?", true)
val q4 = Quiz("Are there 9 planets in the solar system?", false)
val q5 = Quiz("Is St. Petersburg the capital of Russia?", false)
val q6 = Quiz("Is a panda a raccoon?", false)
val q7 = Quiz("Vladimir Putin is the current Russia President?", true)
val q8 = Quiz("Is Baikal the deepest lake?", true)
val q9 = Quiz("Is Сhomolungma the largest mountain?", true)
val q10 = Quiz("One is the smallest prime number?", false)
class VollViewModel(private val savedStateHandle: SavedStateHandle): ViewModel() {

    val strList = listOf(q1,q2,q3,q4,q5,q6,q7,q8,q9,q10)

    var index:Int
        get(){
            return savedStateHandle.get<Int>("index")?:let{0}
        }
        set(value:Int){
            savedStateHandle.set<Int>("index", value)
        }

    fun incIndex(){
        index = (index+1)%strList.count()
    }

    fun decIndex(){
        index = (index - 1 + strList.count())%strList.count()
    }

    var ans: Boolean = true
//        get(){
//            return savedStateHandle.get<Boolean?>("ans")
//        }
//        set(value:Boolean?){
//            savedStateHandle.set<Boolean?>("index", value)
//        }
    var correctAns: Int
        get(){
            return savedStateHandle.get<Int>("correctAns")?:let{0}
        }
        set(value:Int){
            savedStateHandle.set<Int>("correctAns", value)
        }
//    var corText: String = "Correct answers $index from 10"
//    var corText: String = "$index"
}
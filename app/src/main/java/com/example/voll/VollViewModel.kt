package com.example.voll

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.get
import kotlinx.coroutines.launch
import java.util.UUID

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

data class Posts(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String
)
class VollViewModel(private val savedStateHandle: SavedStateHandle): ViewModel() {

    val strList = listOf(q1,q2,q3,q4,q5,q6,q7,q8,q9,q10)
    val ourList = mutableListOf<String>()
    val ourList2 = mutableListOf<Boolean>()
    val postsListString = mutableListOf<String>()
    val postsList by lazy { mutableListOf<Posts>() }
    var refresh = ""

    init {
//        ourList.addAll((1..30).map{UUID.randomUUID().toString()})
        ourList.addAll(strList.map { it.question })
        ourList2.addAll(strList.map { it.answer })
    }

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

    var hintCounter: Int
        get(){
            return savedStateHandle.get<Int>("hintCounter")?:let{0}
        }
        set(value:Int){
            savedStateHandle.set<Int>("hintCounter", value)
        }


//    var jsonString = gson.fromJson()

    init {
        viewModelScope.launch {
            getPosts()
        }
    }
    suspend fun getPosts() {
        var gson = Gson()
        val client = HttpClient(CIO)
        var post = ""
        for (i in 1..30){
            post = client.get<String>("https://jsonplaceholder.typicode.com/posts/$i")
            postsListString.add(post)
        }
        postsList.addAll(postsListString.map{gson.fromJson(it, Posts::class.java)})
    }

}
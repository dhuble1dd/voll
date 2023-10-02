package com.example.voll

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class StartFragment : Fragment() {

    val vollViewModel: VollViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_start, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = view.findNavController()
        val perText = view.findViewById<TextView>(R.id.percText)
        val navBtn = view.findViewById<Button>(R.id.navBtn)
        var percent = 0
        perText.text = "Please wait while getting posts "
        navBtn.setOnClickListener{

        }
        suspend fun percCount():Int{

            repeat(100){
                delay(300)
                GlobalScope.launch(Dispatchers.Main) {

                    percent+=1
                    perText.text = "Please wait while getting posts $percent %"

                }
            }
            return percent
        }

        GlobalScope.launch(Dispatchers.Main) {
                var perc = async { percCount() }

                val a = async { vollViewModel.getPosts() }
                awaitAll(perc,a)
                navController.run {
                    popBackStack()
                    navigate(R.id.recyclerViewFragment)

            }
        }


    }

}
package com.example.voll

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Telephony
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.database.getStringOrNull
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class StartFragment : Fragment() {

    val vollViewModel: VollViewModel by activityViewModels()
    val APP_PREFERENCES = "mysettings"
    val APP_PREFERENCES_INDEX = "Index"
    val APP_PREFERENCES_ADDRESS = "Address"

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
        val add1Btn = view.findViewById<Button>(R.id.add1)
        val add2Btn = view.findViewById<Button>(R.id.add2)
        val add3Btn = view.findViewById<Button>(R.id.add3)
        val smsText = view.findViewById<TextView>(R.id.smsText)
        var percent = 0
        val mSettings = requireContext().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
        perText.text = "Please wait while getting posts "
        navBtn.setOnClickListener{

        }
        add1Btn.setOnClickListener{
            mSettings.edit().putString(APP_PREFERENCES_ADDRESS, add1Btn.text.toString()).apply()
        }
        add2Btn.setOnClickListener{
            mSettings.edit().putString(APP_PREFERENCES_ADDRESS, add2Btn.text.toString()).apply()
        }
        add3Btn.setOnClickListener{
            mSettings.edit().putString(APP_PREFERENCES_ADDRESS, add3Btn.text.toString()).apply()
        }

//        val defaultValue = resources.getInteger(R.integer.)

        val SMSpermisionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()){isAllowed ->
            if(isAllowed){
                val mcursor = requireContext().contentResolver.query(
                    Telephony.Sms.Inbox.CONTENT_URI,
                    null,
                    null,
                    null,
                    "date ASC"
                )
                GlobalScope.launch {

                    repeat(10000){
                        delay(10000)
                        GlobalScope.launch(Dispatchers.Main){
                            mcursor?.also { cursor ->

                                if(mSettings.getInt(APP_PREFERENCES_INDEX, 0) > cursor.count){
                                    mSettings.edit().putInt(APP_PREFERENCES_INDEX, 0).apply()
                                }
                                if (cursor.moveToPosition(mSettings.getInt(APP_PREFERENCES_INDEX, 0)) ){
                                    Log.d("hehe", "${cursor.getString(2)}")
                                    mSettings.edit().putInt(APP_PREFERENCES_INDEX, mSettings.getInt(APP_PREFERENCES_INDEX,0)+1).apply()
                                    if (cursor.getString(2) == mSettings.getString(APP_PREFERENCES_ADDRESS, "")){
                                        val toast = Toast.makeText(requireContext(), "${cursor.getString(12)}", Toast.LENGTH_LONG)
                                        toast.show()
                                        smsText.text = "${cursor.getString(12)}"
                                    }
//                            do {
//
////                                forLoop@for (index in 0..cursor.columnCount){
////                                    if(cursor.getColumnName(index) == Telephony.Sms.Inbox.ADDRESS){
//                                            if (cursor.getString(2) == "RSCHS"){
////                                                Log.d("hehe", "the sms is ${cursor.getString(12)}")
//                                            }
//
////                                        break@forLoop
////                                    }
////                                }
//                            }while (cursor.moveToNext())
                                }
                            }
                        }
                    }
                }

            }else{

            }
        }
        SMSpermisionLauncher.launch(android.Manifest.permission.READ_SMS)

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
//                awaitAll(perc,a)
//                navController.run {
//                    popBackStack()
//                    navigate(R.id.recyclerViewFragment)
//
//            }
        }


    }

}
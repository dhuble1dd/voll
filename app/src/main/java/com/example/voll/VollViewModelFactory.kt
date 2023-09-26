//package com.example.voll
//
//import androidx.lifecycle.SavedStateHandle
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.ViewModelProvider
//
//class VollViewModelFactory(private val savedStateHandle: SavedStateHandle): ViewModelProvider.Factory {
//    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//        return VollViewModel(savedStateHandle) as T
//    }
//}
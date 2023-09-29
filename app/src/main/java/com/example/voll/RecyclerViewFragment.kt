package com.example.voll

import android.os.Bundle
import android.service.autofill.OnClickAction
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.findFragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class RecyclerViewFragment : Fragment() {

    val vollViewModel: VollViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recycler_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = view.findNavController()
//        val recy = view.findViewById<RecyclerView>(R.id.frag_rec)

        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
//        recy.layoutManager = LinearLayoutManager(requireContext())
//        recy.adapter = OurRecyclerAdapter(requireContext(), vollViewModel.ourList)
//        recy.addItemDecoration(DividerItemDecoration(requireContext(),layoutManager.orientation))
//        recy.setOnClickListener{
//            view.findNavController().navigate(R.id.informationFragment)
//            vollViewModel.index = recy.getChildLayoutPosition(it)
//        }
        val nb = view.findViewById<Button>(R.id.navbtn)
        nb.setOnClickListener {
            
            navController.navigate(R.id.informationFragment)

        }


    }

}
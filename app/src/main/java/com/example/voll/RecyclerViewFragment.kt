package com.example.voll

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.get
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class RecyclerViewFragment : Fragment() {

    val vollViewModel: VollViewModel by activityViewModels()

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
        val recy = view.findViewById<RecyclerView>(R.id.frag_rec)
        val child = view.findViewById<LinearLayout>(R.id.uubox)
        suspend fun getTodo(): Posts{
            val gson = Gson()
            val client = HttpClient(CIO)
            val todo = client.get<String>("https://jsonplaceholder.typicode.com/posts/1")
            val gs = gson.fromJson(todo, Posts::class.java)
            return gs
        }

            GlobalScope.launch {

//                vollViewModel.getPosts()
                val a = getTodo()
                Log.d("hehehe", "${a.title}")
            }


//        GlobalScope.launch(Dispatchers.Main) {
//            if(vollViewModel.refresh.isEmpty()){
//                vollViewModel.getPosts()
//                vollViewModel.refresh = "1"
//                navController.run {
//                    popBackStack()
//                    navigate(R.id.recyclerViewFragment)
//                }
//            }
//
//        }

        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        recy.layoutManager = LinearLayoutManager(requireContext())
        recy.adapter = OurRecyclerAdapter(requireContext(), vollViewModel.postsList, object: OurRecyclerAdapter.ItemClickListener{
            override fun onItemClick(position: Int) {
                vollViewModel.index = position
                navController.navigate(R.id.informationFragment)
            }
        })
        recy.addItemDecoration(DividerItemDecoration(requireContext(),layoutManager.orientation))
        vollViewModel.index = vollViewModel.index
        Log.d("hehe", "${vollViewModel.index}")
//        recy.setOnClickListener{
//            navController.navigate(R.id.informationFragment)
//            vollViewModel.index = recy.getChildLayoutPosition(it)
//
//        }

//        val nb = view.findViewById<Button>(R.id.navbtn)
//        nb.setOnClickListener {
//            val navController = view.findNavController()
//            navController.navigate(R.id.informationFragment)
//
//        }


    }

}
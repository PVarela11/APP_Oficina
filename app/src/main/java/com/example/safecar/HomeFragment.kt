package com.example.safecar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.safecar.model.Oficina
import com.example.safecar.model.fakeOficinas
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    //private lateinit var recyclerView: RecyclerView
    //private lateinit var viewAdapter: RecyclerView.Adapter<*>
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler_view.adapter = OficinaAdapter(requireActivity(),fakeOficinas())
        recycler_view.layoutManager = LinearLayoutManager(activity)

    }
}
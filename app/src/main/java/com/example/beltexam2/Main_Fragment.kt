package com.example.beltexam2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController


class Main_Fragment : Fragment() {
    //Declare Button
    lateinit var apiBtn: Button
    lateinit var dbBtn: Button
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
         var view=  inflater.inflate(R.layout.fragment_main_, container, false)


        //initialization
        apiBtn = view.findViewById(R.id.api_btn)
        dbBtn = view.findViewById(R.id.db_btn)

        apiBtn.setOnClickListener {
            findNavController().navigate(R.id.action_main_Fragment_to_API_Fragment)

        }

        dbBtn.setOnClickListener {
            findNavController().navigate(R.id.action_main_Fragment_to_DB_Fragment)

        }

        return view
    }


}
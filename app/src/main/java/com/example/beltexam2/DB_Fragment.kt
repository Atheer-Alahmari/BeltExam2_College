package com.example.beltexam2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.beltexam2.Adapters.RV_Adapter_DB
import com.example.beltexam2.DBRoom.CollegeEntity
import com.example.beltexam2.View_Model.MyViewModel

class DB_Fragment : Fragment() {
    val myViewModel by lazy { ViewModelProvider(this).get(MyViewModel::class.java)}//--------------------------------
    private lateinit var rvAdapter: RV_Adapter_DB
    lateinit var rec_view2: RecyclerView
    lateinit var uni_TV_DB: TextView
   lateinit var dbList:List<CollegeEntity>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_db, container, false)

        dbList= listOf()
        rec_view2=view.findViewById(R.id.recycler_ViewDB)
        uni_TV_DB=view.findViewById(R.id.uniTV_DB)

        myViewModel.getAllCollegeInfo().observe(viewLifecycleOwner, { x ->
            rvAdapter=RV_Adapter_DB(this, x)
            rec_view2.adapter = rvAdapter
            rec_view2.layoutManager = LinearLayoutManager(requireContext())
            rec_view2.scrollToPosition(x.size - 1)

            if(x.isEmpty())
                uni_TV_DB.isVisible=true
        })


        return view
    }


}
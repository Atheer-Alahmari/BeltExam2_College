package com.example.beltexam2

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.beltexam2.Adapters.RV_Adapter_API
import com.example.beltexam2.Retrofit.APIClient
import com.example.beltexam2.Retrofit.APIInterface
import com.example.beltexam2.Retrofit.University_APIItem
import com.example.beltexam2.View_Model.MyViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class API_Fragment : Fragment() {
    //Declare
    lateinit var rec_view: RecyclerView
    lateinit var searchBtn: Button
    lateinit var nameED: EditText

    var uniList= ArrayList<University_APIItem>()
    lateinit var rvAdapter: RV_Adapter_API
    val myViewModel by lazy { ViewModelProvider(this).get(MyViewModel::class.java)}//--------------------------------
    private val apiInterface by lazy { APIClient().getClient()?.create(APIInterface::class.java) }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       var view=  inflater.inflate(R.layout.fragment_api, container, false)

        //initialization
        rec_view=view.findViewById(R.id.recycler_viewAPI)
        searchBtn=view.findViewById(R.id.search_btn)
        nameED=view.findViewById(R.id.uniName_ED)


        //RecyclerView adapter section
       rvAdapter = RV_Adapter_API(this,uniList)



        searchBtn.setOnClickListener {
uniList.clear()
            var userInput = nameED.text.toString()
            if (userInput.isNotEmpty()) {

                    GlobalScope.launch {
                        requestAPI(userInput)
                    }


            }else{
                Toast.makeText(activity, "Please Enter University Name  ", Toast.LENGTH_SHORT).show()

            }

            nameED.text.clear()

        }

        return  view
    }

    private fun requestAPI(userInput: String) {
        apiInterface!!.getAllUniversities(userInput)?.enqueue(object : Callback<ArrayList<University_APIItem>?> {
            override fun onResponse(call: Call<ArrayList<University_APIItem>?>, response: Response<ArrayList<University_APIItem>?>) {
                val resource: ArrayList<University_APIItem>? = response.body()
                for(i in resource!!) {
                    //add to RV
                    uniList.add(i)
                }

                rec_view.adapter = rvAdapter
                rec_view.layoutManager = LinearLayoutManager(requireContext())
                               rec_view.adapter?.notifyDataSetChanged()

            }

            override fun onFailure(call: Call<ArrayList<University_APIItem>?>, t: Throwable) {
                Toast.makeText(activity, "Unable to get Info", Toast.LENGTH_LONG).show()

                call.cancel()
            }
        })

    }
}
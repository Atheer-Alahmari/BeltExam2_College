package com.example.beltexam2.Adapters

import android.app.AlertDialog
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.beltexam2.API_Fragment
import com.example.beltexam2.DBRoom.CollegeEntity
import com.example.beltexam2.R
import com.example.beltexam2.Retrofit.University_APIItem
import kotlinx.android.synthetic.main.item_row_api.view.*

class RV_Adapter_API (private var frag1: API_Fragment, private var uni_List: ArrayList<University_APIItem>): RecyclerView.Adapter<RV_Adapter_API.ItemViewHolder>() {

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_row_api, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val nameU = uni_List[position].name
        val countryU = uni_List[position].country


        holder.itemView.apply {
            api_btn.text = nameU

            api_btn.setOnClickListener {
                var alt = AlertDialog.Builder(context)
                alt.setTitle("Add to database  ")

                alt.setPositiveButton("Add", DialogInterface.OnClickListener{_,_ ->
                    frag1.myViewModel.addCollege(nameU,countryU,"")
                    Toast.makeText(context, "Universty Added ", Toast.LENGTH_SHORT).show()

                })
                alt.setNegativeButton("Cansel", DialogInterface.OnClickListener { dialog, _ ->
                    dialog.cancel()

                })
                val alt1: AlertDialog = alt.create()
                alt1.setCanceledOnTouchOutside(false)
                alt1.show()

            }



        }
    }

    override fun getItemCount() = uni_List.size



}
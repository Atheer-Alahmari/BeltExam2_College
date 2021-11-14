package com.example.beltexam2.Adapters

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.beltexam2.DBRoom.CollegeEntity
import com.example.beltexam2.DB_Fragment
import com.example.beltexam2.R
import kotlinx.android.synthetic.main.item_row_db.view.*

class RV_Adapter_DB (val frag2: DB_Fragment, private var listOfUni: List<CollegeEntity> ): RecyclerView.Adapter<RV_Adapter_DB.ItemViewHolder>() {
    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_row_db,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val idn = listOfUni[position].id
        val name1 = listOfUni[position].uniName
        val country1 = listOfUni[position].country
        val note1 = listOfUni[position].note


        holder.itemView.apply {
            db_TV.text = "$name1 \n $country1 \n "

            update_btn.setOnClickListener {
                var alt = AlertDialog.Builder(context)
                alt.setTitle("Update Note ")
                val mEtNote = EditText(context)
                alt.setView(mEtNote)
                mEtNote.setHint("Enter notes")

                // Positive button text and action
                alt.setPositiveButton("Save", DialogInterface.OnClickListener { _, _ ->
                    var input = mEtNote.text.toString()
                    frag2.myViewModel.updateNote(idn,name1,country1,input)
                    Toast.makeText(context, "Update Note ", Toast.LENGTH_SHORT).show()
                    Toast.makeText(context, input , Toast.LENGTH_SHORT).show()

                })


                // negative button text and action
                alt.setNegativeButton("Cansel", DialogInterface.OnClickListener { dialog, _ ->
                    dialog.cancel()

                })

                val alt1: AlertDialog = alt.create()
                alt1.setCanceledOnTouchOutside(false)
                alt1.show()

            }
            delete_btn.setOnClickListener {
                frag2.myViewModel.deleteCollege(idn)//--------------------------------------------------------------
                Toast.makeText(context, "University Deleted", Toast.LENGTH_SHORT).show()


            }
        }
    }

    override fun getItemCount() = listOfUni.size


}
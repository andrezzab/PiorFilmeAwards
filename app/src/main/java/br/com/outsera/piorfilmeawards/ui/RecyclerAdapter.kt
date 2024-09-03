package br.com.outsera.piorfilmeawards.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import br.com.outsera.piorfilmeawards.R

class RecyclerAdapter(private var list: ArrayList<PropertyModelItem>, val context: Fragment) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>(){

    class ViewHolder(val itemView: View): RecyclerView.ViewHolder(itemView){
        val idtx: TextView=itemView.findViewById(R.id.textid)
        val titletx: TextView=itemView.findViewById(R.id.texttitle)
        val yeartx: TextView=itemView.findViewById(R.id.textyear)
        val winnertx: TextView=itemView.findViewById(R.id.textwinner)
    }

    fun setFilteredList(mList: ArrayList<PropertyModelItem>){
        this.list = mList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.simple_list_item, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = list[position]
        val wint = "winner"
        val winn = "loser"
        holder.apply {
            yeartx.text = currentItem.year.toString()
            idtx.text = currentItem.id.toString()
            titletx.text = currentItem.title
            if (currentItem.winner=="yes"){
                winnertx.text = wint
            } else{
                winnertx.text = winn
            }
        }
    }
}
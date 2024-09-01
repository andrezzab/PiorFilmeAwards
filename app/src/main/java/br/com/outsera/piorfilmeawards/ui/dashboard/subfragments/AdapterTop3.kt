package br.com.outsera.piorfilmeawards.ui.dashboard.subfragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import br.com.outsera.piorfilmeawards.R
import br.com.outsera.piorfilmeawards.ui.PropertyModelItem
import br.com.outsera.piorfilmeawards.ui.list.ListFragment

class AdapterTop3(private var list: ArrayList<PropertyModelItem>, val context: Fragment) : RecyclerView.Adapter<AdapterTop3.ViewHolder>(){

    class ViewHolder(val itemView: View): RecyclerView.ViewHolder(itemView){
        val studiostx: TextView=itemView.findViewById(R.id.textstudios)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.simple_list_item_top3, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = list[position]
        holder.apply {
            studiostx.text = currentItem.studios
        }
    }
}
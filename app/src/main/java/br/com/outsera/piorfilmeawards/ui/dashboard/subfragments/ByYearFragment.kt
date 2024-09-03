package br.com.outsera.piorfilmeawards.ui.dashboard.subfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.isEmpty
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.outsera.piorfilmeawards.R
import br.com.outsera.piorfilmeawards.network.ApiService
import br.com.outsera.piorfilmeawards.ui.PropertyModel
import br.com.outsera.piorfilmeawards.ui.PropertyModelItem
import br.com.outsera.piorfilmeawards.ui.RecyclerAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ByYearFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var list: ArrayList<PropertyModelItem>
    private lateinit var searchView: SearchView
    private lateinit var adapter: RecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val contentView: View = inflater.inflate(R.layout.fragment_by_year, container, false)
        recyclerView = contentView.findViewById(R.id.recyclerView_byyear)
        searchView = contentView.findViewById(R.id.searchView_byyear)
        list = ArrayList()
        val layoutManager = LinearLayoutManager(this.context)
        adapter = RecyclerAdapter(list, this)
        recyclerView.layoutManager = layoutManager
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }
        })
        val retrofit: Retrofit =
            Retrofit.Builder().baseUrl("https://andrezzab.github.io/API-Filmes/")
                .addConverterFactory(GsonConverterFactory.create()).build()
        val api: ApiService = retrofit.create(ApiService::class.java)
        val call: Call<PropertyModel> = api.getAllData()
        call.enqueue(object : Callback<PropertyModel?> {
            override fun onResponse(
                call: Call<PropertyModel?>,
                response: Response<PropertyModel?>
            ) {
                if (response.isSuccessful) {
                    list.clear()
                    for (myData in response.body()!!) {
                        list.add(myData)
                    }
                }
            }

            override fun onFailure(p0: Call<PropertyModel?>, p1: Throwable) {
                list.clear()
            }
        })
        return contentView
    }
    private fun filterList(query: String?) {
        if (query != null) {
            val filteredList = ArrayList<PropertyModelItem>()
            for (i in list) {
                if (i.year.toString().equals(query)) {
                    filteredList.add(i)
                }
                adapter.notifyDataSetChanged()
                recyclerView.adapter = adapter
            }
            if (filteredList.isEmpty() and !searchView.isEmpty()) {
                adapter.setFilteredList(filteredList)
                adapter.notifyDataSetChanged()
                recyclerView.adapter = adapter
            } else {
                adapter.setFilteredList(filteredList)
            }
        }
        else {
            list.clear()
        }
    }
}

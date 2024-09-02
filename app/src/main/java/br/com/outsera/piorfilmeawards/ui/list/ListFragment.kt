package br.com.outsera.piorfilmeawards.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.outsera.piorfilmeawards.R
import br.com.outsera.piorfilmeawards.ui.PropertyModelItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import br.com.outsera.piorfilmeawards.network.ApiService
import br.com.outsera.piorfilmeawards.ui.PropertyModel
import br.com.outsera.piorfilmeawards.ui.dashboard.RecyclerAdapter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var list: ArrayList<PropertyModelItem>
    private lateinit var searchView: SearchView
    private lateinit var adapter: RecyclerAdapter

    lateinit var texte: EditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate the layout for this fragment
        //return inflater.inflate(android.R.layout.fragment_wifi, container, false)

        val contentView: View = inflater.inflate(R.layout.fragment_list, container, false)
        recyclerView = contentView.findViewById(R.id.recyclerView)
        searchView = contentView.findViewById(R.id.searchView)


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
                    adapter.notifyDataSetChanged()
                    recyclerView.adapter = adapter
                }
            }

            override fun onFailure(p0: Call<PropertyModel?>, p1: Throwable) {
                TODO("Not yet implemented")
            }
        })
        return contentView
    }

    private fun filterList(query: String?) {
        val filteredList = ArrayList<PropertyModelItem>()
        if (query != null) {

            for (i in list) {
                if (i.year.toString().equals(query)) {
                    filteredList.add(i)
                }
            }
            if (filteredList.isEmpty()) {
                //
            } else {
                adapter.setFilteredList(filteredList)
            }
        }
    }
}
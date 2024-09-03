package br.com.outsera.piorfilmeawards.ui.list

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
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

class ListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var list: ArrayList<PropertyModelItem>
    private lateinit var searchView: SearchView
    private lateinit var adapter: RecyclerAdapter

    @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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
                filterListBySearch(newText)
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
                list.clear()
            }
        })
        return contentView
    }

    fun filterListBySearch(query: String?) {
        val filteredList = ArrayList<PropertyModelItem>()
        if (query != null) {
            var winner: String
            for (i in list) {
                if (i.winner=="yes"){
                    winner = "winner"
                } else{
                    winner = "loser"
                }
                if ((i.year.toString() == query) or (winner == query)) {
                    filteredList.add(i)
                }
            }
            adapter.setFilteredList(filteredList)
        }
    }
}

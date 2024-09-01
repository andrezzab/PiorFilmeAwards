package br.com.outsera.piorfilmeawards.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.outsera.piorfilmeawards.R
import br.com.outsera.piorfilmeawards.network.ApiService
import br.com.outsera.piorfilmeawards.ui.PropertyModel
import br.com.outsera.piorfilmeawards.ui.PropertyModelItem
import br.com.outsera.piorfilmeawards.ui.dashboard.subfragments.AdapterTop3
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Top3Fragment : Fragment() {

    //private lateinit var recyclerView: RecyclerView
    //private lateinit var adapter: AdapterTop3
    //private lateinit var list: ArrayList<PropertyModelItem>
    private lateinit var studios: ArrayList<String>
    private lateinit var teste: ArrayList<String>
    private lateinit var listview: ListView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val contentView: View = inflater.inflate(R.layout.fragment_top3, container, false)
        //recyclerView = contentView.findViewById(R.id.recyclerView_top3)
        listview = contentView.findViewById(R.id.listview)
        teste = arrayListOf()

        studios = ArrayList()
        //list = ArrayList()
        //val layoutManager = LinearLayoutManager(this.context)
       // adapter = AdapterTop3(list, this)
       // recyclerView.layoutManager = layoutManager

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
                    for (myData in response.body()!!) {
                        if (myData.winner.equals("yes")){
                            if(myData.studios.contains(",")){
                                val substitui: List<String> = myData.studios.split(",")
                                for (indice in substitui){
                                    studios.add(indice)
                                }
                            }
                            studios.add(myData.studios)
                        }
                    }
                    studios.groupingBy{ it }
                        .eachCount()
                        .toList()
                        .sortedByDescending{ it.second }
                        .take(3)
                    for (item in studios.groupingBy{ it }
                        .eachCount()
                        .toList()
                        .sortedByDescending{ it.second }
                        .take(3)){
                        var a: String = item.first
                        var b: String = item.second.toString()
                        teste.add(a+" - "+ b + "wins")
                    }

                    val adapter2: ArrayAdapter<String> = ArrayAdapter<String>(activity!!, android.R.layout.simple_list_item_1, teste)
                    listview.setAdapter(adapter2)
                }
            }

            override fun onFailure(p0: Call<PropertyModel?>, p1: Throwable) {
                TODO("Not yet implemented")
            }
        })
        return contentView
    }
}


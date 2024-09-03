package br.com.outsera.piorfilmeawards.ui.dashboard.subfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import br.com.outsera.piorfilmeawards.R
import br.com.outsera.piorfilmeawards.network.ApiService
import br.com.outsera.piorfilmeawards.ui.PropertyModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class IntervalFragment : Fragment() {
    private lateinit var interval: ArrayList<String>
    private lateinit var anos: ArrayList<Int>
    private lateinit var list: ArrayList<String>
    private lateinit var listview: ListView
    private lateinit var substitui: ArrayList<String>
    private lateinit var mudar: List<String>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val contentView: View = inflater.inflate(R.layout.fragment_interval, container, false)
        listview = contentView.findViewById(R.id.listview_int)
        anos = arrayListOf()
        list = arrayListOf()
        interval = ArrayList()
        substitui = arrayListOf()
        var producers_max = ""
        var producers_min = ""
        var ano_max = 1
        var ano_min = 1
        var ano_maior = 0
        var ano_menor = 0

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
                        if (myData.winner.equals("yes")) {
                            if (myData.producers.contains(" and ")){
                                var str = myData.producers.replace(" and ",", ")
                                val delimiter = ","
                                mudar = str.split(delimiter)
                                for (valor in mudar){
                                    interval.add(valor.trim())
                                }
                            }
                            else {
                                interval.add(myData.producers)
                            }
                        }
                    }
                    for(autor in interval.groupingBy{ it }
                        .eachCount()
                        .toList()
                        .filter { it.second > 1 }
                        .take(interval.size)){
                        substitui.add(autor.first)
                    }
                    for (i in substitui){
                        for (data in response.body()!!){
                            if (data.producers.contains(i) and (data.winner == "yes")){
                                anos.add(data.year)
                            }
                        }
                        ano_maior = anos.max()
                        ano_menor = anos.min()
                        var dif_wins = ano_maior-ano_menor
                        if (dif_wins<=ano_min){
                            producers_min = i
                            ano_min = dif_wins
                        }
                        if (dif_wins>ano_max){
                            producers_max = i
                            ano_max = dif_wins
                        }
                        anos.clear()
                    }
                    list.add("Minimum: " + producers_min+" - "+ ano_min + " year(s)")
                    list.add("Maximum: " + producers_max+" - "+ ano_max + " year(s)")
                    val adapter1: ArrayAdapter<String> =
                        ArrayAdapter<String>(activity!!, android.R.layout.simple_list_item_1, list)
                    listview.setAdapter(adapter1)
                }
            }

            override fun onFailure(p0: Call<PropertyModel?>, p1: Throwable) {
                list.clear()
            }
        })
        return contentView
    }
}

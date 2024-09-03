package br.com.outsera.piorfilmeawards.ui.dashboard.subfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import br.com.outsera.piorfilmeawards.R
import br.com.outsera.piorfilmeawards.databinding.FragmentMultipleBinding
import br.com.outsera.piorfilmeawards.network.ApiService
import br.com.outsera.piorfilmeawards.ui.PropertyModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MultipleFragment : Fragment() {
    private lateinit var multiple: ArrayList<String>
    private lateinit var list: ArrayList<String>
    private lateinit var listview: ListView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val contentView: View = inflater.inflate(R.layout.fragment_multiple, container, false)
        listview = contentView.findViewById(R.id.listview_multiple)
        list = arrayListOf()
        multiple = ArrayList()
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
                            multiple.add(myData.year.toString())
                        }
                    }
                    for (item in multiple.groupingBy { it }
                        .eachCount()
                        .toList()) {
                        var year: String = item.first
                        var count: String = item.second.toString()
                        if (count.toInt()>1){
                            list.add(year + " - " + count + " winners")
                        }
                    }
                    val adapter2: ArrayAdapter<String> =
                        ArrayAdapter<String>(activity!!, android.R.layout.simple_list_item_1, list)
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

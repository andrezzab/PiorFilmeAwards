package br.com.outsera.piorfilmeawards.ui.dashboard.subfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
    private lateinit var list: ArrayList<String>
    private lateinit var sublist: ArrayList<String>
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
        list = arrayListOf()
        sublist = arrayListOf()
        interval = ArrayList()
        substitui = arrayListOf()
        var producers = ""
        var ano = 0
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
                                    interval.add(valor)
                                    mudar = arrayListOf("")
                                }
                            }
                            else {
                                interval.add(myData.producers)
                            }
                        }
                    }
                    println(interval.groupingBy{ it }
                        .eachCount()
                        .toList()
                        .sortedByDescending{ it.second }
                        .take(interval.size))
                    /*for (item in interval.groupingBy { it }
                        .eachCount()
                        .filter { it.value > 2 }) {
                        list.add(item)
                    }*/
                    /*for (item in interval){
                        if(item.contains(",")){
                            substitui = item.split(",")
                            list.add(substitui.toString())
                            *//*  for (indice in substitui){
                                  studios.add(indice)
                              }*//*
                        }
                    }*/


                    /* for (item in interval.groupingBy { it }
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

                     */
                }
                println(interval) //[Allan Carr,1980, Frank Yablans,1981, Mitsuharu Ishii,1982, Robert R. Weston,1983, Bo Derek,1984, Buzz Feitshans,1985, Gloria Katz,1986, Bob Cavallo, Joe Ruffalo and Steve Fargnoli,1986, Bill Cosby,1987, Ted Field and Robert W. Cort,1988, Harve Bennett,1989, Steven Perry and Joel Silver,1990, Bo Derek,1990, Joel Silver,1991, Carol Baum and Howard Rosenman,1992, Sherry Lansing,1993, Buzz Feitshans and David Matalon,1994, Charles Evans and Alan Marshall,1995, Andrew Bergman and Mike Lobell,1996, Kevin Costner, Steve Tisch and Jim Wilson,1997, Ben Myron and Joe Eszterhas,1998, Jon Peters and Barry Sonnenfeld,1999, Jonathan D. Krane, Elie Samaha and John Travolta,2000, Larry Brezner, Howard Lapides and Lauren Lloyd,2001, Matthew Vaughn,2002, Martin Brest and Casey Silver,2003, Denise Di Novi and Edward McDonnell,2004, John Mallory Asher, BJ Davis, Rod Hamilton, Kimberley Kates, Michael Manasseri, Jenny McCarthy and Trent Walford,2005, Mario Kassar, Joel B. Michaels and Andrew G. Vajna,2006, Gary Barber, Michael DeLuca and Mike Myers,2008, Lorenzo di Bonaventura, Ian Bryce, Tom DeSanto and Don Murphy,2009, Frank Marshall, Kathleen Kennedy, Sam Mercer and M. Night Shyamalan,2010, Todd Garner, Jack Giarraputo and Adam Sandler,2011, Wyck Godfrey, Stephenie Meyer and Karen Rosenfelt,2012, Peter Farrelly, Ryan Kavanaugh, John Penotti and Charles B. Wessler,2013, Darren Doane, Raphi Henley, Amanda Rosser and David Shannon,2014, Simon Kinberg, Matthew Vaughn, Hutch Parker, Robert Kulzer and Gregory Goodman,2015, Michael De Luca, Dana Brunetti and E. L. James,2015, Gerald R. Molen,2016, Michelle Raimo Kouyate,2017]
                println(list) //[[Allan Carr, 1980], [Frank Yablans, 1981], [Mitsuharu Ishii, 1982], [Robert R. Weston, 1983], [Bo Derek, 1984], [Buzz Feitshans, 1985], [Gloria Katz, 1986], [Bob Cavallo,  Joe Ruffalo and Steve Fargnoli, 1986], [Bill Cosby, 1987], [Ted Field and Robert W. Cort, 1988], [Harve Bennett, 1989], [Steven Perry and Joel Silver, 1990], [Bo Derek, 1990], [Joel Silver, 1991], [Carol Baum and Howard Rosenman, 1992], [Sherry Lansing, 1993], [Buzz Feitshans and David Matalon, 1994], [Charles Evans and Alan Marshall, 1995], [Andrew Bergman and Mike Lobell, 1996], [Kevin Costner,  Steve Tisch and Jim Wilson, 1997], [Ben Myron and Joe Eszterhas, 1998], [Jon Peters and Barry Sonnenfeld, 1999], [Jonathan D. Krane,  Elie Samaha and John Travolta, 2000], [Larry Brezner,  Howard Lapides and Lauren Lloyd, 2001], [Matthew Vaughn, 2002], [Martin Brest and Casey Silver, 2003], [Denise Di Novi and Edward McDonnell, 2004], [John Mallory Asher,  BJ Davis,  Rod Hamilton,  Kimberley Kates,  Michael Manasseri,  Jenny McCarthy and Trent Walford, 2005], [Mario Kassar,  Joel B. Michaels and Andrew G. Vajna, 2006], [Gary Barber,  Michael DeLuca and Mike Myers, 2008], [Lorenzo di Bonaventura,  Ian Bryce,  Tom DeSanto and Don Murphy, 2009], [Frank Marshall,  Kathleen Kennedy,  Sam Mercer and M. Night Shyamalan, 2010], [Todd Garner,  Jack Giarraputo and Adam Sandler, 2011], [Wyck Godfrey,  Stephenie Meyer and Karen Rosenfelt, 2012], [Peter Farrelly,  Ryan Kavanaugh,  John Penotti and Charles B. Wessler, 2013], [Darren Doane,  Raphi Henley,  Amanda Rosser and David Shannon, 2014], [Simon Kinberg,  Matthew Vaughn,  Hutch Parker,  Robert Kulzer and Gregory Goodman, 2015], [Michael De Luca,  Dana Brunetti and E. L. James, 2015], [Gerald R. Molen, 2016], [Michelle Raimo Kouyate, 2017]]

            }

            override fun onFailure(p0: Call<PropertyModel?>, p1: Throwable) {
                TODO("Not yet implemented")
            }
        })
        return contentView
    }
}

package br.com.outsera.piorfilmeawards.ui.dashboard.subfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import br.com.outsera.piorfilmeawards.databinding.FragmentIntervalBinding

class IntervalFragment : Fragment() {

    private lateinit var intervalViewModel: IntervalViewModel
    private var _binding: FragmentIntervalBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        intervalViewModel = ViewModelProvider(this).get(IntervalViewModel::class.java)

        _binding = FragmentIntervalBinding.inflate(inflater, container, false)
        val root: View = binding.root

        /*val textView: TextView = binding.textMultiple
        multipleViewModel.text.observe(viewLifecycleOwner,Observer{
            textView.text = it
        })*/

        // Inflate the layout for this fragment
        return root
    }
}

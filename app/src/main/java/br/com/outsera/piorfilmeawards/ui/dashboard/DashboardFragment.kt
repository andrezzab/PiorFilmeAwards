package br.com.outsera.piorfilmeawards.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.outsera.piorfilmeawards.databinding.FragmentDashboardBinding
import com.google.android.material.tabs.TabLayoutMediator

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    val fragmentsArray = arrayOf(
        "Multiple",
        "Interval",
        "Top3",
        "ByYear"
    )
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val view = _binding!!.root;
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val viewPager = binding.postsTabsViewPager
        val tabLayout = binding.postsTabs
        val adapter = PagerAdapter(childFragmentManager, lifecycle)
        viewPager.adapter = adapter
        TabLayoutMediator(tabLayout,viewPager) { tab,
                                                 position -> tab.text = fragmentsArray[position]}.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
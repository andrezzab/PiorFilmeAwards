package br.com.outsera.piorfilmeawards.ui.dashboard

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import br.com.outsera.piorfilmeawards.ui.dashboard.subfragments.ByYearFragment
import br.com.outsera.piorfilmeawards.ui.dashboard.subfragments.IntervalFragment
import br.com.outsera.piorfilmeawards.ui.dashboard.subfragments.MultipleFragment
import br.com.outsera.piorfilmeawards.ui.dashboard.subfragments.Top3Fragment

private const val NUM_TABS = 4

class PagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle): FragmentStateAdapter(fragmentManager, lifecycle)  {
    override fun getItemCount(): Int {
        return NUM_TABS
    }

    override fun createFragment(position: Int): Fragment {
        when(position) {
            0 -> return MultipleFragment()
            1 -> return IntervalFragment()
            2 -> return Top3Fragment()
            3 -> return ByYearFragment()
        }

        return DashboardFragment()
    }


}
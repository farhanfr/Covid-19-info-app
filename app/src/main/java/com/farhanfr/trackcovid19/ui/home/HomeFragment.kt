package com.farhanfr.trackcovid19.ui.home

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.farhanfr.trackcovid19.R
import com.farhanfr.trackcovid19.adapter.HomePager
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vpHome.adapter = HomePager(childFragmentManager)
        tlMain.setupWithViewPager(vpHome)
        swipeRefresh.setOnRefreshListener {
            vpHome.adapter = HomePager(childFragmentManager)
            tlMain.setupWithViewPager(vpHome)
            swipeRefresh.isRefreshing = false
        }
    }

}

package com.cankarademir.atmosware_internship_project.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cankarademir.atmosware_internship_project.R
import com.cankarademir.atmosware_internship_project.databinding.FragmentDetailUserBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class DetailUserFragment : Fragment() {
    private lateinit var binding: FragmentDetailUserBinding

    var nav_bar: BottomNavigationView? = null

    companion object {
        fun newInstance() = DetailUserFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailUserBinding.inflate(inflater, container, false)
        val view = binding.root

        nav_bar = activity?.findViewById<BottomNavigationView>(R.id.nav_view)
        nav_bar?.visibility = View.GONE

        arguments?.let {
            val safeArgs = DetailUserFragmentArgs.fromBundle(it)
            binding.data = safeArgs.userData
        }
        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        nav_bar?.visibility = View.VISIBLE
    }

}
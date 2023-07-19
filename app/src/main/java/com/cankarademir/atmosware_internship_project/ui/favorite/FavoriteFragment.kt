package com.cankarademir.atmosware_internship_project.ui.favorite

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.cankarademir.atmosware_internship_project.R
import com.cankarademir.atmosware_internship_project.adapters.FavoriteAdapter
import com.cankarademir.atmosware_internship_project.databinding.FragmentFavoriteBinding

class FavoriteFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteBinding
    private lateinit var favoriteViewModel: FavoriteViewModel
    private lateinit var adapter: FavoriteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_favorite, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        favoriteViewModel = ViewModelProvider(this).get(FavoriteViewModel::class.java)
        adapter = FavoriteAdapter(emptyList())

        val gridLayoutManager = GridLayoutManager(requireContext(), 3)
        binding.recyclerview.layoutManager = gridLayoutManager
        binding.recyclerview.adapter = adapter

        favoriteViewModel.data.observe(viewLifecycleOwner) { dataList ->
            adapter = FavoriteAdapter(dataList)
            binding.recyclerview.adapter = adapter
        }

        favoriteViewModel.getPhotosData()
    }
}
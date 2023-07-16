package com.cankarademir.atmosware_internship_project.ui.photos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.cankarademir.atmosware_internship_project.R
import com.cankarademir.atmosware_internship_project.adapters.PhotoAdapter
import com.cankarademir.atmosware_internship_project.databinding.FragmentPhotosBinding

class PhotosFragment : Fragment() {

    private lateinit var binding: FragmentPhotosBinding
    private lateinit var photosViewModel: PhotosViewModel
    private lateinit var adapter: PhotoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_photos, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        photosViewModel = ViewModelProvider(this).get(PhotosViewModel::class.java)
        adapter = PhotoAdapter(emptyList())

        val gridLayoutManager = GridLayoutManager(requireContext(), 3)
        binding.recyclerview.layoutManager = gridLayoutManager
        binding.recyclerview.adapter = adapter

        photosViewModel.data.observe(viewLifecycleOwner) { dataList ->
            adapter = PhotoAdapter(dataList)
            binding.recyclerview.adapter = adapter
        }

        photosViewModel.getPhotosData()
    }
}
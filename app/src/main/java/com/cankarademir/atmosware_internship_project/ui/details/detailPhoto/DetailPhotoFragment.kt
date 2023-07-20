package com.cankarademir.atmosware_internship_project.ui.details.detailPhoto

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.cankarademir.atmosware_internship_project.R
import com.cankarademir.atmosware_internship_project.configs.FavoriteDatabase
import com.cankarademir.atmosware_internship_project.databinding.FragmentDetailPhotoBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailPhotoFragment : Fragment() {
    private lateinit var binding: FragmentDetailPhotoBinding
    private lateinit var viewModel: DetailPhotoViewModel
    var nav_bar: BottomNavigationView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailPhotoBinding.inflate(inflater, container, false)
        val view = binding.root
        viewModel = ViewModelProvider(this).get(DetailPhotoViewModel::class.java)

        arguments?.let {
            val safeArgs = DetailPhotoFragmentArgs.fromBundle(it)
            binding.data = safeArgs.photoData
        }
        val FavoriteButton = binding.favoriteButton

        CoroutineScope(Dispatchers.Main).launch {
            viewModel.fillFavoriteList()
        }

        // Favori butonunun başlangıçtaki görselini belirliyor
        viewModel.allFavorites.observe(viewLifecycleOwner) { favoritesList ->
            val photoData = binding.data
            if (favoritesList!!.any { it.id == photoData?.id }) {
                binding.favoriteButton.setImageResource(R.drawable.icon_favorite)
            } else {
                binding.favoriteButton.setImageResource(R.drawable.icon_favorite_border)
            }
        }

        FavoriteButton.setOnClickListener {
            val photoData = binding.data
            //photoData varsa tıklama işlemi yapılınca favori tablosuna ekle
            if (photoData != null) {
                if (viewModel.isFavorite(photoData)) {
                    viewModel.deleteFavorite(photoData)
                    Toast.makeText(requireContext(), "Favorilerden çıkarıldı", Toast.LENGTH_SHORT)
                        .show()
                    FavoriteButton.setImageResource(R.drawable.icon_favorite_border)
                } else {
                    viewModel.insertFavorite(photoData)
                    Toast.makeText(requireContext(), "Favorilere eklendi", Toast.LENGTH_SHORT)
                        .show()
                    FavoriteButton.setImageResource(R.drawable.icon_favorite)
                }
            }
        }

        navGone()
        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        nav_bar?.visibility = View.VISIBLE
    }

    fun navGone() {
        nav_bar = activity?.findViewById<BottomNavigationView>(R.id.nav_view)
        nav_bar?.visibility = View.GONE
    }
}
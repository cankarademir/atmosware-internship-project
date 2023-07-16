package com.cankarademir.atmosware_internship_project.ui.user

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.cankarademir.atmosware_internship_project.R
import com.cankarademir.atmosware_internship_project.adapters.UsersAdapter
import com.cankarademir.atmosware_internship_project.databinding.FragmentUserBinding

class UserFragment : Fragment() {

    private lateinit var binding: FragmentUserBinding
    private lateinit var usersViewModel: UserViewModel
    private lateinit var adapter: UsersAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_user, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        usersViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        adapter = UsersAdapter(emptyList())

        binding.recyclerview.adapter = adapter
        usersViewModel.data.observe(viewLifecycleOwner) {
            with(binding) {
                adapter = UsersAdapter(it)
                recyclerview.layoutManager = LinearLayoutManager(requireContext())
                recyclerview.adapter = adapter
                adapter.notifyDataSetChanged()
            }
        }
        usersViewModel.getUserData()

    }
}
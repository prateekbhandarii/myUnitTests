package com.ods.myunittests.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ods.myunittests.R
import com.ods.myunittests.databinding.FragmentHomeBinding
import com.ods.myunittests.viewmodels.SpendViewModel

class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var binding: FragmentHomeBinding
    private val spendsAdapter = SpendsAdapter()
    private lateinit var viewModel: SpendViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[SpendViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        binding.recyclerViewSpends.also {
            it.layoutManager = LinearLayoutManager(requireContext())
            it.adapter = spendsAdapter
        }

        binding.buttonAddSpend.setOnClickListener {
            val fragment = AddSpendFragment()
            requireActivity().supportFragmentManager.beginTransaction().also {
                it.add(R.id.frameLayout, fragment)
                it.commitAllowingStateLoss()
            }
        }

        viewModel.getListOfSpends().observe(viewLifecycleOwner) { spends ->
            spendsAdapter.spends = spends
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.setListOfSpend()
    }
}
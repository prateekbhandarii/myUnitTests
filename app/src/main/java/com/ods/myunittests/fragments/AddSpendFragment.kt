package com.ods.myunittests.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ods.myunittests.R
import com.ods.myunittests.databinding.FragmentAddSpendBinding
import com.ods.myunittests.viewmodels.SpendViewModel

class AddSpendFragment : Fragment(R.layout.fragment_add_spend) {

    private lateinit var binding: FragmentAddSpendBinding
    private lateinit var viewModel: SpendViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this)[SpendViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAddSpendBinding.bind(view)

        binding.btnSave.setOnClickListener {
            addSpend()
            binding.textResult.text = "Spend added!"
        }
    }

    private fun addSpend() {
        val amount = binding.etAmount.text.toString().toInt()
        val desc = binding.etDescription.text.toString()
        if (amount >= 0 && desc.isNotEmpty())
            viewModel.addSpend(amount, desc)
        else
            binding.textResult.text = "Spend added!"
    }
}
package com.kh69.passmath.questions.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayout
import com.kh69.passmath.databinding.FragmentDashboardBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_dashboard.*

@AndroidEntryPoint
class DashboardFragment : Fragment() {
    private val binding get() = _binding!!
    private var _binding: FragmentDashboardBinding? = null

    private var tab_layout: TabLayout? = null
    private var nested_scroll_view: NestedScrollView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
        requestInitialAnimalsList()
    }

    private fun setupUI() {
        card_form_6?.setOnClickListener {
            val action = DashboardFragmentDirections.actionDashboardFragmentToQuizFragment()
            findNavController().navigate(action)

        }
    }

    private fun requestInitialAnimalsList() {
//        TODO("Not yet implemented")
    }


}
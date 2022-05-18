package com.kh69.passmath.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.kh69.passmath.databinding.DashboardFragmentBinding
import com.kh69.passmath.extensions.getViewModelFactory

class DashboardFragment : Fragment() {

    private val viewModel by viewModels<DashboardViewModel> { getViewModelFactory() }

    private lateinit var viewDataBinding: DashboardFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewDataBinding = DashboardFragmentBinding.inflate(inflater, container, false).apply {
            viewmodel = viewModel
        }
        setHasOptionsMenu(true)
        return viewDataBinding.root
    }


}
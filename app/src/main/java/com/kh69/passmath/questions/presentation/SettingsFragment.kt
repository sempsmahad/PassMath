package com.kh69.passmath.questions.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kh69.passmath.databinding.FragmentQuizBinding
import com.kh69.passmath.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {
    private val binding get() = _binding!!
    private var _binding: FragmentSettingsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        setupUI()
//        requestInitialAnimalsList()
    }
}
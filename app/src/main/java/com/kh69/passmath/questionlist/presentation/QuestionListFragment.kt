package com.kh69.passmath.questionlist.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kh69.passmath.databinding.FragmentQuestionListBinding

class QuestionListFragment : Fragment() {
    private val binding get() = _binding!!
    private var _binding: FragmentQuestionListBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentQuestionListBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        setupUI()
//        requestInitialAnimalsList()

        // if 30 questions are available begin reading and displaying them_____1
        // else test net connection___2
        // if available fetch from remote else display error____3
        // else if result ok save to db____4
        // else display error from server____5
        // if all the above were successful start reading else display issue and retry____6


    }
}
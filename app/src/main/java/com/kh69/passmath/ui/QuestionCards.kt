package com.kh69.passmath.ui

import android.graphics.PorterDuff
import android.os.Bundle
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.kh69.passmath.MyViewPagerAdapter
import com.kh69.passmath.R
import com.kh69.passmath.data.Repository
import com.kh69.passmath.data.cache.Question
import com.kh69.passmath.data.model.QuizState
import com.kh69.passmath.databinding.ActivityCardWizardOverlapBinding
import com.kh69.passmath.getViewModel
import com.kh69.passmath.viewmodel.MainViewModel
import com.kh69.passmath.viewmodel.QuestionsViewModel

class QuestionCards : AppCompatActivity() {

    companion object {
        const val MAX_QUESTIONS = 8
    }

    private lateinit var binding: ActivityCardWizardOverlapBinding
    private val viewModel by lazy { getViewModel { MainViewModel(Repository()) } }
    private val quizViewModel by lazy { getViewModel { QuestionsViewModel(Repository()) } }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_wizard_overlap)

        binding = ActivityCardWizardOverlapBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpViews()

        getQuestions()
    }

    private fun setUpViews() {
        bottomProgressDots(0)
        binding.btnNext.setOnClickListener {
            val current = binding.viewPager.currentItem + 1
            if (current < MAX_QUESTIONS) {
                binding.viewPager.currentItem = current
            } else {
                finish()
            }

        }
        binding.viewPager.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                bottomProgressDots(position)
            }

            override fun onPageScrollStateChanged(state: Int) {
            }

        })
    }

    private fun bottomProgressDots(current_index: Int) {
        val dots = arrayOfNulls<ImageView>(MAX_QUESTIONS)
        binding.layoutDots.removeAllViews()

        for (i in dots.indices) {
            dots[i] = ImageView(this)
            val width_height = 15
            val params =
                LinearLayout.LayoutParams(ViewGroup.LayoutParams(width_height, width_height))
            params.setMargins(10, 10, 10, 10)
            dots[i]!!.layoutParams = params
            dots[i]!!.setImageResource(R.drawable.shape_circle)
            dots[i]!!.setColorFilter(resources.getColor(R.color.grey_20), PorterDuff.Mode.SRC_IN)
            binding.layoutDots.addView(dots[i])
        }
        if (dots.isNotEmpty()) {
            dots[current_index]!!.setImageResource(R.drawable.shape_circle)
            dots[current_index]!!.setColorFilter(
                resources.getColor(R.color.light_green_600),
                PorterDuff.Mode.SRC_IN
            );
        }
    }

    private fun prepopulateQuestions() = viewModel.prepopulateQuestions()


    private fun getQuestions() {
        prepopulateQuestions()
        quizViewModel.getCurrentState().observe(this)
        {
            render(it)
        }
    }

    private fun render(state: QuizState) {
        when (state) {
//            is QuizState.EmptyState   -> renderEmptyState()
            is QuizState.DataState -> renderDataState(state)
            is QuizState.LoadingState -> renderLoadingState()
        }
    }

    private fun renderLoadingState() {

    }

    private fun renderDataState(quizState: QuizState.DataState) {
        binding.viewPager.adapter = MyViewPagerAdapter(quizState.data as ArrayList<Question>, this)
    }
}
package com.kh69.passmath

import android.app.Activity
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.WindowManager
import androidx.annotation.ColorRes
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private var mQuestions = ArrayList<Question>()
    private lateinit var mQuestionRecyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question_list) //TODO create question list activity separately
        initToolbar()

        val formulas = DataHelpers.getFormulas(this)
        val questions_text = DataHelpers.getQuestions(this)
        val text = "$$ c = \\pm\\sqrt{a^2 + b^2} $$"
        val text3 = " \$\n" +
                "    \\begin{array}{r}\n" +
                "    4 \\cos x-2\\left(2 \\cos ^{2} x-1\\right)=3 \\\\\n" +
                "    4 \\cos x-4 \\cos ^{2} x+2=3 \\\\\n" +
                "    4 \\cos x-4 \\cos ^{2} x-1=0 \\\\\n" +
                "    4 \\cos ^{2} x-4 \\cos x+1=0 \\\\\n" +
                "    4 \\cos ^{2} x-2 \\cos x-2 \\cos x+1=0 \\\\\n" +
                "    2 \\cos x(2 \\cos x-1)-1(2 \\cos x-1)=0 \\\\\n" +
                "    (2 \\cos x-1)(2 \\cos x-1)=0 \\\\\n" +
                "    \\Rightarrow 2 \\cos x-1=0 \\\\\n" +
                "    2 \\cos x=1 \\\\\n" +
                "    \\cos x=\\dfrac{1}{2} \\\\\n" +
                "    x=60^{\\circ}, 300^{\\circ}\n" +
                "    \\end{array}\n" +
                "    \$"


//        mQuestions.add(
//            Question(
//                1,
//                formulas.get(1),
//                1997,
//                Paper.PAPER_ONE,
//                Section.SECTION_A,
//                "Alegebra",
//                null
//            )
//        )
        mQuestionRecyclerView = findViewById(R.id.rv_question_list)
        mQuestionRecyclerView.layoutManager = LinearLayoutManager(this)

        val data = ArrayList<Question>()
        for (i in 0 until questions_text.size) {
//            data.add(Question(i,formulas.get(i),1997,Paper.PAPER_ONE,Section.SECTION_A,"Alegebra",null))
//            data.add(Question(i,question2,1997,Paper.PAPER_ONE,Section.SECTION_A,"Alegebra",Answer(text3)))
//            data.add(
//                Question(
//                    i,("$\\mathrm{ ${i+1}\\space .\\space}$ " +questions_text[i]), 1997, Paper.PAPER_ONE, Section.SECTION_A, "Alegebra", Answer(text3)
////            ((i+1).toString() + ". " +questions_text[i]), 1997, Paper.PAPER_ONE, Section.SECTION_A, "Alegebra", Answer(text3)
//                )
//            )
        }

        val adapter = QuestionAdapter(data)
        mQuestionRecyclerView.adapter = adapter

    }

    private fun initToolbar() {
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        toolbar.setNavigationIcon(R.drawable.ic_menu)
        setSupportActionBar(toolbar)
        supportActionBar!!.setTitle("Math Quiz")
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        setSystemBarColor(this, R.color.indigo_700)
    }

    fun setSystemBarColor(act: Activity, @ColorRes color: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = act.window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.statusBarColor = act.resources.getColor(color)
        }
    }



}
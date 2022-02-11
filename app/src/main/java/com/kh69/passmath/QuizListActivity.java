package com.kh69.passmath;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kh69.passmath.data.cache.Question;
import com.kh69.passmath.remote.APIUtils;
import com.kh69.passmath.remote.QuestionService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuizListActivity extends AppCompatActivity {
    private List<Question>  mQuestions = new ArrayList<>();
    private QuestionService mQuestionService;
    private RecyclerView    rv_questions;
    private Toolbar         toolbar;
    private QuestionAdapter mQuestionAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_list);

        initToolbar();
        initComponent();
    }

    private void initComponent() {
        mQuestionService = APIUtils.getQuestionService();
        rv_questions     = findViewById(R.id.rv_question_list);
        rv_questions.setLayoutManager(new LinearLayoutManager(this));
        rv_questions.setHasFixedSize(true);

        getQuestionsList();


    }

    private void getQuestionsList() {
        Call<com.kh69.passmath.Response> call = mQuestionService.getQuestions();
        call.enqueue(new Callback<com.kh69.passmath.Response>() {

            @Override
            public void onResponse(Call<com.kh69.passmath.Response> call, Response<com.kh69.passmath.Response> response) {
                if (response.isSuccessful()) {
                    mQuestions       = response.body().getAlldata();
                    mQuestionAdapter = new QuestionAdapter(mQuestions);
                    rv_questions.setAdapter(mQuestionAdapter);
                }
            }

            @Override
            public void onFailure(Call<com.kh69.passmath.Response> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });

    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Math Quiz");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setSystemBarColor(this, R.color.indigo_700);
    }

    private void setSystemBarColor(Activity act, int color) {
        Window window = act.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(act.getResources().getColor(color));
    }

}

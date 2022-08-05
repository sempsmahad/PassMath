package com.kh69.passmath.data.source.remote;


import com.kh69.passmath.Response;
import com.kh69.passmath.data.Question;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface QuestionService {

        @GET("questions")
        Call<Response> getQuestions();

        @POST("questions")
        Call<Question> createQuestion(@Body Question question);

        @PATCH("questions/{id}")
        Call<Question> updateQuestion(@Path("id") String id, @Body Question question);

        @DELETE("questions/{id}")
        Call<Question> deleteQuestion(@Path("id") String id);


}

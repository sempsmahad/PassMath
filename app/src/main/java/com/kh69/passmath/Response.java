package com.kh69.passmath;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.kh69.passmath.data.Question;

import java.util.ArrayList;

public class Response {


    @SerializedName("questions")
    @Expose
    private ArrayList<Question> alldata;


    public ArrayList<Question> getAlldata() {
        return alldata;
    }

    public void setAlldata(ArrayList<Question> alldata) {
        this.alldata = alldata;
    }
}

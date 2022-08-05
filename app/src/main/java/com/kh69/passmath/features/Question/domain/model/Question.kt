package com.kh69.passmath.features.question.domain.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


@Entity(tableName = "questions")
data class Question(
    @PrimaryKey
    @ColumnInfo(name = "questionId")
    @SerializedName("_id")
    @Expose
    @NonNull
    val id: String,

    @SerializedName("qtn_text")
    @Expose
    val text: String,

    @SerializedName("year")
    @Expose
    val year: Int,

    @SerializedName("paper")
    @Expose
    val paper: Int,

    @SerializedName("section")
    @Expose
    val section: String,

    @SerializedName("topic")
    @Expose
    val topic: String,

    @SerializedName("answer")
    @Expose
    val answer: String,

    @SerializedName("katex_question")
    @Expose
    val katex_question: String,

    @SerializedName("katex_answer")
    @Expose
    val katex_answer: String,

    @SerializedName("edited")
    @Expose
    val edited: Boolean,

    )

class InvalidNoteException(message: String) : Exception(message)
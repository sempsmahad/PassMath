package com.kh69.passmath.dashboard

import android.app.Activity
import android.graphics.PorterDuff
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.material.tabs.TabLayout
import com.kh69.passmath.Event
import com.kh69.passmath.R
import com.kh69.passmath.data.source.QuestionsRepository
import com.kh69.passmath.extensions.launchSettings
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * ViewModel for the Dashboard screen.
 */
class DashboardViewModel(
    private val questionsRepository: QuestionsRepository
) : ViewModel() {

    private val _snackbarText = MutableLiveData<Event<Int>>()
    val snackbarText: LiveData<Event<Int>> = _snackbarText

    private val _toastText = MutableLiveData<Event<Int>>()
    val toastText: LiveData<Event<Int>> = _toastText

    fun tabSelected(tab: TabLayout.Tab, activity: Activity) {
        tab.icon!!
            .setColorFilter(
                R.color.blue_grey_400,
                PorterDuff.Mode.SRC_IN
            )
        tabClicked(tab, activity)
    }

    fun tabClicked(tab: TabLayout.Tab, activity: Activity) {
        when (tab.position) {
            0 -> showToast(R.string.text_home)
            1 -> showToast(R.string.text_stats)
            2 -> showToast(R.string.text_communication)
            3 -> {
                showToast(R.string.text_settings)
                activity.launchSettings()
            }
        }
    }

    fun tabUnselected(tab: TabLayout.Tab, activity: Activity) {
        tab.icon!!.setColorFilter(
            R.color.grey_20,
            PorterDuff.Mode.SRC_IN
        )
    }

    fun clearCompletedTasks() {
        viewModelScope.launch {
            tasksRepository.clearCompletedTasks()
            showSnackbarMessage(R.string.completed_tasks_cleared)
        }
    }

    private fun showSnackbarMessage(message: Int) {
        _snackbarText.value = Event(message)
    }

    private fun showToast(message: Int) {
        _toastText.value = Event(message)
    }

}
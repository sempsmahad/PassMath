package com.kh69.passmath.ui.dashboard

import android.content.Intent
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.widget.NestedScrollView
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayout
import com.kh69.passmath.AppExecutors
import com.kh69.passmath.R
import com.kh69.passmath.Tools2
import com.kh69.passmath.binding.FragmentDataBindingComponent
import com.kh69.passmath.databinding.DashboardFragmentBinding
import com.kh69.passmath.extensions.launchSettings
import com.kh69.passmath.ui.questionCards.QuestionCards
import com.kh69.passmath.util.autoCleared
import javax.inject.Inject

class DashboardFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var appExecutors: AppExecutors

    var dataBindingComponent: DataBindingComponent = FragmentDataBindingComponent(this)

    var binding by autoCleared<DashboardFragmentBinding>()

    //    var adapter by autoCleared<RepoListAdapter>()
    val dashboardViewModel: DashboardViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.dashboard_fragment,
            container,
            false,
            dataBindingComponent
        )
        initComponent()
        return binding.root
    }

    private fun initComponent() {
        binding.cardForm6.setOnClickListener { view: View? ->
            startActivity(
                Intent(
                    this,
                    QuestionCards::class.java
                )
            )
        }

        binding.tabLayout.let {
            it.addTab(it.newTab().setIcon(R.drawable.ic_home), 0)
            it.addTab(it.newTab().setIcon(R.drawable.ic_data_usage), 1)
            it.addTab(it.newTab().setIcon(R.drawable.ic_chat), 2)
            it.addTab(it.newTab().setIcon(R.drawable.ic_settings), 3)

            // set icon color pre-selected
            it.getTabAt(0)!!
                .icon!!.setColorFilter(
                    resources.getColor(R.color.blue_grey_400),
                    PorterDuff.Mode.SRC_IN
                )
            it.getTabAt(1)!!
                .icon!!.setColorFilter(resources.getColor(R.color.grey_20), PorterDuff.Mode.SRC_IN)
            it.getTabAt(2)!!
                .icon!!.setColorFilter(resources.getColor(R.color.grey_20), PorterDuff.Mode.SRC_IN)
            it.getTabAt(3)!!
                .icon!!.setColorFilter(resources.getColor(R.color.grey_20), PorterDuff.Mode.SRC_IN)
            it.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab) {
                    dashboardViewModel.tabSelected(tab, activity)
                }

                override fun onTabUnselected(tab: TabLayout.Tab) {
                    tab.icon!!.setColorFilter(
                        resources.getColor(R.color.grey_20),
                        PorterDuff.Mode.SRC_IN
                    )
                }

                override fun onTabReselected(tab: TabLayout.Tab) {
                    onTabClicked(tab)
                }
            })
        }
        Tools2.setSystemBarColor(this, R.color.grey_5)
        Tools2.setSystemBarLight(this)
    }

    private fun onTabClicked(tab: TabLayout.Tab) {
        when (tab.position) {
            0 -> Toast.makeText(context, "Home", Toast.LENGTH_SHORT).show()
            1 -> Toast.makeText(context, "Statistics", Toast.LENGTH_SHORT).show()
            2 -> Toast.makeText(context, "Communication", Toast.LENGTH_SHORT).show()
            3 -> {
                Toast.makeText(context, "Settings", Toast.LENGTH_SHORT).show()
                launchSettings()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search_setting, menu)
        Tools2.changeMenuIconColor(menu, resources.getColor(R.color.grey_60))
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        } else {
            Toast.makeText(applicationContext, item.title, Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }


}
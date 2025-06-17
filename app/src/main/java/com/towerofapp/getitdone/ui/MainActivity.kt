package com.towerofapp.getitdone.ui

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.tabs.TabLayoutMediator
import com.towerofapp.getitdone.R
import com.towerofapp.getitdone.databinding.ActivityMainBinding
import com.towerofapp.getitdone.databinding.DialogAddTaskBinding
import com.towerofapp.getitdone.ui.tasks.StarredTaskFragment
import com.towerofapp.getitdone.ui.tasks.TaskFragment
import com.towerofapp.getitdone.util.InputValidator
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

            binding = ActivityMainBinding.inflate(layoutInflater).apply {
                lifecycleScope.launch {
                    viewModel.getTaskLists().collectLatest { taskLists->
                        pager.adapter = PagerActivity(this@MainActivity, taskLists.size + 2)
                        pager.currentItem = 1
                        TabLayoutMediator(tabs, pager) { tab, position ->
                            when (position) {
                                0 -> tab.icon =
                                    ContextCompat.getDrawable(this@MainActivity, R.drawable.ic_star)
                                taskLists.size + 1 -> tab.customView = Button(this@MainActivity).apply {
                                    text = "Add New List"
                                }
                                else -> tab.text = taskLists[position - 1].name
                            }
                        }.attach()
                    }
                }
                setContentView(root)
                fab.setOnClickListener { showAddTaskDialog() }
            }
    }

    private fun showAddTaskDialog() {
        DialogAddTaskBinding.inflate(layoutInflater).apply {
            val dialog = BottomSheetDialog(this@MainActivity) // returns BottomSheetDialog obj
            dialog.setContentView(root)

            btnSave.isEnabled = false

            editTextTaskTitle.addTextChangedListener {  input->
                btnSave.isEnabled  = InputValidator.isInputValid(input.toString())
            }

            btnShowDetails.setOnClickListener {  // Toggle visibility of the description on button press
                editTextTaskDescription.visibility =
                    if (editTextTaskDescription.visibility == View.VISIBLE) View.GONE
                    else View.VISIBLE
            }

            btnSave.setOnClickListener {
                viewModel.createTask(
                        title = editTextTaskTitle.text.toString(),
                        description = editTextTaskDescription.text.toString())
                dialog.dismiss()
            }
            dialog.show()
        }
    }


    inner class PagerActivity(activity: FragmentActivity, private val numberOfPages: Int) : FragmentStateAdapter(activity) {
        override fun getItemCount() = numberOfPages

        override fun createFragment(position: Int): Fragment {
            return when(position){
                0 -> StarredTaskFragment()
                else -> TaskFragment().apply {
                    Gravity.START
                }
            }
        }
    }
}
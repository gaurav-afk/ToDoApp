package com.towerofapp.getitdone.ui

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.tabs.TabLayoutMediator
import com.towerofapp.getitdone.R
import com.towerofapp.getitdone.databinding.ActivityMainBinding
import com.towerofapp.getitdone.databinding.DialogAddTaskBinding
import com.towerofapp.getitdone.ui.tasks.StarredTaskFragment
import com.towerofapp.getitdone.ui.tasks.TaskFragment
import com.towerofapp.getitdone.util.InputValidator

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            pager.adapter = PagerActivity(this@MainActivity)
            pager.currentItem = 1
            TabLayoutMediator(tabs, pager) { tab, position ->
                when(position){
                    0 -> tab.icon = ContextCompat.getDrawable(this@MainActivity, R.drawable.ic_star)
                    1 -> tab.text = "Tasks"
                    2 -> tab.customView = Button(this@MainActivity).apply {
                        text = "Add New List"
                    }
                }
            }.attach()
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

    inner class PagerActivity(activity: FragmentActivity) : FragmentStateAdapter(activity) {
        override fun getItemCount(): Int {
            return 3
        }

        override fun createFragment(position: Int): Fragment {
            return when(position){
                0 -> StarredTaskFragment()
                else -> TaskFragment()
            }
        }
    }
}
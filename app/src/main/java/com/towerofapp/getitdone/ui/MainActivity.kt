package com.towerofapp.getitdone.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.tabs.TabLayoutMediator
import com.towerofapp.getitdone.databinding.ActivityMainBinding
import com.towerofapp.getitdone.databinding.DialogAddTaskBinding
import com.towerofapp.getitdone.ui.tasks.TaskFragment
import com.towerofapp.getitdone.util.InputValidator

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            pager.adapter = PagerActivity(this@MainActivity)
            TabLayoutMediator(tabs, pager) { tab, _ ->
                tab.text = "Tasks"
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
            return 1
        }

        override fun createFragment(position: Int): Fragment {
            return TaskFragment()
        }
    }
}
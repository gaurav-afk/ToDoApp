package com.towerofapp.getitdone.ui

import com.towerofapp.getitdone.data.GetItDoneDatabase
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.tabs.TabLayoutMediator
import com.towerofapp.getitdone.data.Task
import com.towerofapp.getitdone.databinding.ActivityMainBinding
import com.towerofapp.getitdone.databinding.DialogAddTaskBinding
import com.towerofapp.getitdone.ui.tasks.TaskFragment
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val database: GetItDoneDatabase by lazy { GetItDoneDatabase.getDatabase(this) }
    private val taskDao by lazy { database.getTaskDao() }
    private val taskFragment: TaskFragment = TaskFragment()

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

            btnShowDetails.setOnClickListener {  // Toggle visibility of the description on button press
                editTextTaskDescription.visibility =
                    if (editTextTaskDescription.visibility == View.VISIBLE) View.GONE
                    else View.VISIBLE
            }

            btnSave.setOnClickListener {
                val task = Task(
                    title = editTextTaskTitle.text.toString(),
                    description = editTextTaskDescription.text.toString()
                )
                thread {
                    taskDao.createTask(task)
                    taskFragment.fetchAllTasks()
                }
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
            return taskFragment
        }
    }
}
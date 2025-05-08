package com.towerofapp.getitdone

import GetItDoneDatabase
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.tabs.TabLayoutMediator
import com.towerofapp.getitdone.databinding.ActivityMainBinding
import com.towerofapp.getitdone.databinding.DialogAddTaskBinding
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var database: GetItDoneDatabase
    private val taskDao by lazy { database.getTaskDao() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.pager.adapter = PagerActivity(this)
        TabLayoutMediator(binding.tabs, binding.pager) { tab, position ->
            tab.text = "Tasks"
        }.attach()


        binding.fab.setOnClickListener {
            val dialogBinding = DialogAddTaskBinding.inflate(layoutInflater)  // Inflate the dialog layout and store the view object in dialogBinding
            val dialog = BottomSheetDialog(this) // returns BottomSheetDialog obj
            dialog.setContentView(dialogBinding.root)  // passing root view obj to BottomSheetDialog to show
            dialog.show()
        }

        database = GetItDoneDatabase.createDatabase(context = this)

    }


    inner class PagerActivity(activity: FragmentActivity) : FragmentStateAdapter(activity) {
        override fun getItemCount(): Int {
            return 1
        }

        override fun createFragment(position: Int): Fragment {
            return TaskFragments()
        }
    }
}
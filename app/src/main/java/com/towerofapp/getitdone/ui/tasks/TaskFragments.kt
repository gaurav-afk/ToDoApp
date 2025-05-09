package com.towerofapp.getitdone.ui.tasks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.towerofapp.getitdone.data.GetItDoneDatabase
import com.towerofapp.getitdone.data.Task
import com.towerofapp.getitdone.data.TaskDao
import com.towerofapp.getitdone.databinding.FragmentsTasksBinding
import kotlin.concurrent.thread

class TaskFragments : Fragment() {

    private lateinit var binding: FragmentsTasksBinding
    private val taskDao by lazy {
        GetItDoneDatabase.createDatabase(requireContext()).getTaskDao()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentsTasksBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        thread {
            val tasksList = taskDao.getAllTask()
            requireActivity().runOnUiThread {
                binding.rvTask.adapter = TaskAdapter(tasksList)
            }
        }
    }

}
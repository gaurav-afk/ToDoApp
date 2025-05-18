package com.towerofapp.getitdone.ui.tasks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.towerofapp.getitdone.data.GetItDoneDatabase
import com.towerofapp.getitdone.data.Task
import com.towerofapp.getitdone.databinding.FragmentsTasksBinding
import kotlin.concurrent.thread

// Implements the adapter's listener to handle task updates.
class TaskFragment : Fragment(), TaskAdapter.TaskUpdatedListener {

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
        fetchAllTasks()
    }

    fun fetchAllTasks() {
        thread {
            val tasksList = taskDao.getAllTask()
            requireActivity().runOnUiThread {
                binding.rvTask.adapter = TaskAdapter(tasksList, this)
            }
        }
    }

    // Called by adapter when a task is checked/unchecked.
    override fun onTaskUpdated(task: Task) {
        thread {
            taskDao.updateTask(task)
            fetchAllTasks()
        }

    }
}
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
class TaskFragment : Fragment(), TaskAdapter.TaskItemClickListener {
    private val taskAdapter: TaskAdapter = TaskAdapter(this)

    private lateinit var binding: FragmentsTasksBinding
    private val taskDao by lazy {
        GetItDoneDatabase.getDatabase(requireContext()).getTaskDao()
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
        binding.rvTask.adapter = taskAdapter
        fetchAllTasks()
    }

    fun fetchAllTasks() {
        thread {
            val tasks = taskDao.getAllTask()
            requireActivity().runOnUiThread {
                taskAdapter.setTasks(tasks)
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

    override fun onTaskDeleted(task: Task) {
        thread {
            taskDao.deleteTask(task)
            fetchAllTasks()
        }
    }


}
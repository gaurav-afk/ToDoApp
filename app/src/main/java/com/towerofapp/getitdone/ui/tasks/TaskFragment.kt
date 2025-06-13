package com.towerofapp.getitdone.ui.tasks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.towerofapp.getitdone.data.model.Task
import com.towerofapp.getitdone.databinding.FragmentsTasksBinding
import kotlinx.coroutines.launch

// Implements the adapter's listener to handle task updates.
class TaskFragment : Fragment(), TaskAdapter.TaskItemClickListener {
    private val taskAdapter: TaskAdapter = TaskAdapter(this)
    private val viewModel: TasksViewModel by viewModels()
    private lateinit var binding: FragmentsTasksBinding

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
        lifecycleScope.launch {
            val tasks: List<Task> = viewModel.fetchAllTask()
            taskAdapter.setTasks(tasks = tasks)
        }
    }

    // Called by adapter when a task is checked/unchecked.
    override fun onTaskUpdated(task: Task) {
        viewModel.updateTask(task){
            fetchAllTasks()
        }

    }

    override fun onTaskDeleted(task: Task) {
        viewModel.deleteTask(task)
        fetchAllTasks()
    }
}
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
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class StarredTaskFragment: Fragment(), TaskAdapter.TaskItemClickListener {

    private val taskAdapter: TaskAdapter = TaskAdapter(this)
    private lateinit var binding: FragmentsTasksBinding
    private val viewModel: StarredTasksViewModel by viewModels()

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
        fetchStarredTask()
    }

    // Collects task list from ViewModel and updates the adapter in real-time
    // whenever the Room database changes (insert/update/delete).
    fun fetchStarredTask() {
        lifecycleScope.launch {
            viewModel.fetchStarredTask().collectLatest { tasks ->
                taskAdapter.setTasks(tasks)
            }
        }
    }

    override fun onTaskUpdated(task: Task) {
        viewModel.updateTask(task)
    }

    override fun onTaskDeleted(task: Task) {
        viewModel.deleteTask(task)
    }
}
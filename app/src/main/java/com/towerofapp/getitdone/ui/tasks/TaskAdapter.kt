package com.towerofapp.getitdone.ui.tasks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.towerofapp.getitdone.data.Task
import com.towerofapp.getitdone.databinding.FragmentsTasksBinding
import com.towerofapp.getitdone.databinding.ItemTaskBinding


class TaskAdapter(val tasks: List<Task>) : RecyclerView.Adapter<TaskAdapter.ViewHolder>() {

    override fun getItemCount() = tasks.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(ItemTaskBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(tasks[position])
    }

    inner class ViewHolder(val binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(task: Task) {
            binding.tvTitle.text = task.title
            binding.tvDetail.text = task.description
        }
    }
}
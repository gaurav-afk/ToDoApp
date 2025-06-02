package com.towerofapp.getitdone.ui.tasks

import android.graphics.Paint
import android.graphics.PaintFlagsDrawFilter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.checkbox.MaterialCheckBox
import com.towerofapp.getitdone.data.Task
import com.towerofapp.getitdone.databinding.FragmentsTasksBinding
import com.towerofapp.getitdone.databinding.ItemTaskBinding
import kotlin.concurrent.thread


class TaskAdapter(private val listener: TaskUpdatedListener) :
    RecyclerView.Adapter<TaskAdapter.ViewHolder>() {
    private var tasks: List<Task> = listOf()

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
            binding.checkBox.isChecked = task.isComplete
            binding.toggleStar.isChecked = task.isStarring
            if (task.isComplete){
                binding.tvTitle.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                binding.tvDetail.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            }else {
                binding.tvTitle.paintFlags = 0
                binding.tvDetail.paintFlags = 0
            }
            binding.tvTitle.text = task.title
            binding.tvDetail.text = task.description

            // Update task based on checkbox state and notify the fragment.
            binding.checkBox.addOnCheckedStateChangedListener { _, state ->
                val updatedTask = when (state) {
                    MaterialCheckBox.STATE_CHECKED -> task.copy(isComplete = true)
                    else -> task.copy(isComplete = false)
                }
                listener.onTaskUpdated(updatedTask)
            }

            binding.toggleStar.addOnCheckedStateChangedListener { _, state ->
                val updatedTask = when (state) {
                    MaterialCheckBox.STATE_CHECKED -> task.copy(isStarring = true)
                    else -> task.copy(isStarring = false)
                }
                listener.onTaskUpdated(updatedTask)
            }

        }
    }

    fun setTasks(tasks: List<Task>){
        this.tasks = tasks
        notifyDataSetChanged()
    }

    interface TaskUpdatedListener {  // Used to notify the fragment when a task is updated (e.g., checkbox toggled).
        fun onTaskUpdated(task: Task)
    }
}
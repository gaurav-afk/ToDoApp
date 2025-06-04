package com.towerofapp.getitdone.ui.tasks

import android.graphics.Paint
import android.graphics.PaintFlagsDrawFilter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.Visibility
import com.google.android.material.checkbox.MaterialCheckBox
import com.towerofapp.getitdone.data.Task
import com.towerofapp.getitdone.databinding.FragmentsTasksBinding
import com.towerofapp.getitdone.databinding.ItemTaskBinding
import kotlin.concurrent.thread


class TaskAdapter(private val listener: TaskItemClickListener) :
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
            binding.apply {
                root.setOnLongClickListener {
                    listener.onTaskDeleted(task)
                    true
                }
                checkBox.isChecked = task.isComplete
                toggleStar.isChecked = task.isStarring
                if (task.isComplete){
                    tvTitle.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                    tvDetail.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                }else {
                    tvTitle.paintFlags = 0
                    tvDetail.paintFlags = 0
                }
                tvTitle.text = task.title
                if (task.description.isNullOrEmpty()){
                    tvDetail.visibility = View.GONE
                }else{
                    tvDetail.visibility = View.VISIBLE
                    tvDetail.text = task.description
                }


                // Update task based on checkbox state and notify the fragment.
                checkBox.setOnClickListener{
                    val updatedTask = task.copy(isComplete = checkBox.isChecked)
                    listener.onTaskUpdated(updatedTask)
                }
                toggleStar.setOnClickListener{
                    val updatedTask = task.copy(isStarring = toggleStar.isChecked)
                    listener.onTaskUpdated(updatedTask)
                }
            }
        }

    }

    fun setTasks(tasks: List<Task>){
        this.tasks = tasks.sortedBy { task -> task.isComplete }
        notifyDataSetChanged()
    }
    

    interface TaskItemClickListener {  // Used to notify the fragment when a task is updated (e.g., checkbox toggled).
        fun onTaskUpdated(task: Task)
        fun onTaskDeleted(task: Task)
    }
}
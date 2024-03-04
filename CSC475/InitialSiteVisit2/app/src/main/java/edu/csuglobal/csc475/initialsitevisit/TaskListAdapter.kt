package edu.csuglobal.csc475.initialsitevisit

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

<<<<<<< HEAD
class TaskListAdapter(
    private val checkListItems: List<CheckListItem>,
    private val titleDao: ChecklistTitleDao
) : RecyclerView.Adapter<TaskListAdapter.ViewHolder>() {
=======
class TaskListAdapter(private val checkListItems: List<CheckListItem>, private val titleDao: ChecklistTitleDao) : RecyclerView.Adapter<TaskListAdapter.ViewHolder>() {
>>>>>>> origin/main


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val lblTask: TextView
        val intent: Intent
<<<<<<< HEAD

=======
>>>>>>> origin/main
        init {
            lblTask = view.findViewById(R.id.lblTask)
            intent = Intent(view.context, TaskDetail::class.java)
        }//end init function
    }//end CheckListItemViewHolder class
<<<<<<< HEAD

=======
>>>>>>> origin/main
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.tasks, parent, false)

        return ViewHolder(view)
    }//end onCreateViewHolder function

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
<<<<<<< HEAD
        holder.lblTask.text =
            titleDao.getChecklistTitle(checkListItems[position].checkListId)?.title
=======
        holder.lblTask.text = titleDao.getChecklistTitle(checkListItems[position].checkListId)?.title
>>>>>>> origin/main
        holder.itemView.setOnClickListener {
            holder.intent.putExtra("task", checkListItems[position].checkListId)
            holder.itemView.context.startActivity(holder.intent)
        } //end setOnClickListener function
    }//end onBindViewHolder function

    override fun getItemCount(): Int {
        return checkListItems.size
    }//end getItemCount function
}//end TaskListAdapter class


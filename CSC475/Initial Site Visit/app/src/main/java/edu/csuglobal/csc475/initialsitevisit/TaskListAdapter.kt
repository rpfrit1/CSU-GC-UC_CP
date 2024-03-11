package edu.csuglobal.csc475.initialsitevisit

import android.content.Intent
import android.graphics.Paint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

 class TaskListAdapter(
        private val checkListItems: List<CheckListItem>,
        private val titleDao: ChecklistTitleDao
    ) : RecyclerView.Adapter<TaskListAdapter.ViewHolder>() {


        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val lblTask: TextView
            val intent: Intent
            init {
                //log start of initialization of View Holder
                Log.d("ViewHolder", "Initializing View Holder")

                lblTask = view.findViewById(R.id.lblTask)
                intent = Intent(view.context, TaskDetail::class.java)

                //log end of initialization of View Holder
                Log.d("ViewHolder", "View Holder initialized")
            }//end init function
        }//end CheckListItemViewHolder class
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            //log start of onCreateViewHolder function
            Log.d("TaskListAdapter", "Creating View Holder")

            val view = LayoutInflater.from(parent.context).inflate(R.layout.tasks, parent, false)
            val holder = ViewHolder(view)
            //log end of onCreateViewHolder function
            Log.d("TaskListAdapter", "View Holder created")

            return ViewHolder(view)
        }//end onCreateViewHolder function

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            //log start of onBindViewHolder function
            Log.d("TaskListAdapter", "Binding View Holder")

            //log end of onBindViewHolder function
            Log.d("TaskListAdapter", "View Holder bound")

            holder.lblTask.text =
                titleDao.getChecklistTitle(checkListItems[position].checkListId)?.title
            //set lblTask backround based on checklist status. If completed successful, make a green backround. If complete with issues, make a red background if Not Applicable, set to strikethrough. If in process, set to yellow
            when (checkListItems[position].checkListItemState) {
                "Completed Successful" -> holder.lblTask.setBackgroundResource(R.color.green)
                "Completed with Issues" -> holder.lblTask.setBackgroundResource(R.color.red)
                "In Process" -> holder.lblTask.setBackgroundResource(R.color.yellow)
                "Not Applicable" -> holder.lblTask.paintFlags = holder.lblTask.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            }//end when statement

            holder.itemView.setOnClickListener {
                //log and create an intent to loadtask_detail activity
                Log.d("TaskListAdapter", "View Holder clicked")

                //add task id to intent
                holder.intent.putExtra("taskListId", checkListItems[position].checkListId)
                //add visit id to intent
                holder.intent.putExtra("visitId", checkListItems[position].visitId)
                holder.itemView.context.startActivity(holder.intent)

                //log end of onBindViewHolder function
                Log.d("TaskListAdapter", "View Holder bound")
            } //end setOnClickListener function
        }//end onBindViewHolder function

        override fun getItemCount(): Int {
            return checkListItems.size
        }//end getItemCount function
}//end TaskListAdapter class


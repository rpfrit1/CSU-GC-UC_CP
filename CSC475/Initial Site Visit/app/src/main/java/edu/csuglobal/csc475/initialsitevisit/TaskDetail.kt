package edu.csuglobal.csc475.initialsitevisit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class TaskDetail : AppCompatActivity() {
    private lateinit var db: SiteVisitDatabase
    private lateinit var checkListDao: CheckListItemDao
    private lateinit var checkTitleDao: ChecklistTitleDao
    private lateinit var checkListItem: CheckListItem
    private lateinit var lblTaskName:TextView
    private lateinit var txtTimer:EditText
    private lateinit var txtNotes:EditText
    private lateinit var btnNa:Button
    private lateinit var btnNotStarted:Button
    private lateinit var btnInProcess:Button
    private lateinit var btnSuccess:Button
    private lateinit var btnProblems:Button
    private lateinit var btnBackToList:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        //log start of loading the Task Detail
        Log.d("TaskDetail", "Loading Task Detail")

        super.onCreate(savedInstanceState)
        setContentView(R.layout.task_detail)

        //log loading database
        Log.d("TaskDetail", "Loading Database")
        db = SiteVisitDatabase.getInstance(this)
        checkListDao = db.checklistItemDao()
        checkTitleDao = db.checklistTitleDao()

         lblTaskName = findViewById<TextView>(R.id.lblTaskName)
         txtTimer = findViewById<EditText>(R.id.txtTimer)
         txtNotes = findViewById<EditText>(R.id.txtNotes)
         btnNa = findViewById<Button>(R.id.btnNa)
         btnNotStarted = findViewById<Button>(R.id.btnNotStarted)
         btnInProcess = findViewById<Button>(R.id.btnInProcess)
         btnSuccess = findViewById<Button>(R.id.btnSuccess)
         btnProblems = findViewById<Button>(R.id.btnProblems)
         btnBackToList = findViewById<Button>(R.id.btnBackToList)

        //log and receive tasklist and visit id from intent
        Log.d("TaskDetail", "Receiving TaskList and Visit ID")
        val taskListId = intent.getIntExtra("taskListId", 0)
        val visitId = intent.getIntExtra("visitId", 0)

        //log the values of taskListId and visitId
        Log.d("TaskDetail", "TaskList ID: $taskListId")
        Log.d("TaskDetail", "Visit ID: $visitId")

        //log completion of pulling from intent
        Log.d("TaskDetail", "Pulling TaskList and Visit ID completed")

        //log and pull tasklist item from database based on taskListID and visitID
        Log.d("TaskDetail", "Pulling TaskList Item")

        //if the tasklist item is null, create a new one and save it to the database
        if (checkListDao.getChecklistItem(visitId, taskListId) == null) {
            checkListItem = CheckListItem(0, visitId, taskListId, "", "", "")
            checkListDao.insertCheckListItem(checkListItem)
        }//end if
        //if the tasklist item is not null, load it into the checkListItem variable
        else {
            checkListItem = checkListDao.getChecklistItem(visitId, taskListId)!!
        }

        //log completion of pulling tasklist item
        Log.d("TaskDetail", "Pulling TaskList Item completed")

        //log and load information from the checklistItem into respective fields
        Log.d("TaskDetail", "Loading TaskList Item information")

        //if the checkListTitle is null, create a new one and save it to the database
        if (checkTitleDao.getChecklistTitle(checkListItem.checkListItem) == null) {
            checkTitleDao.insertChecklistTitle(ChecklistTitle(checkListItem.checkListItem, ""))
            lblTaskName.text = ""
        }//end if
        else {
            lblTaskName.text = checkTitleDao.getChecklistTitle(checkListItem.checkListItem)!!.title
        }//end else

        txtTimer.setText(checkListItem.timespent)
        txtNotes.setText(checkListItem.checkListComments)

        btnNa.setOnClickListener {
            checkListItem.checkListItemState = "Not Applicable"
            //if starttime is not "", set the timer to the difference between now and starttime + previous value
            if (checkListItem.startTime != "" && checkListItem.startTime == "0" ) {
                checkListItem.timespent = (System.currentTimeMillis() - checkListItem.startTime.toLong() + checkListItem.timespent.toLong()).toString()
            }//end if
            checkListDao.updateCheckListItem(checkListItem)
        }//end btnNa listener

        btnNotStarted.setOnClickListener {
            checkListItem.checkListItemState = "Not Started"
            checkListItem.timespent = "0"
            checkListItem.startTime = "0"
            checkListDao.updateCheckListItem(checkListItem)
        }//end btnNotStarted listener

        btnInProcess.setOnClickListener {
            checkListItem.checkListItemState = "In Process"
            checkListItem.startTime = System.currentTimeMillis().toString()
            checkListDao.updateCheckListItem(checkListItem)
        }//end btnInProcess listener

        btnSuccess.setOnClickListener {
            checkListItem.checkListItemState = "Completed Successful"
            //if starttime is not "", set the timer to the difference between now and starttime + previous value
            if (checkListItem.startTime != "") {
                checkListItem.timespent = (System.currentTimeMillis() - checkListItem.startTime.toLong() + checkListItem.timespent.toLong()).toString()
            }//end if
        }//end btnSuccess listener

        btnProblems.setOnClickListener {
            checkListItem.checkListItemState = "Completed with Issues"
            //if starttime is not "", set the timer to the difference between now and starttime + previous value
            if (checkListItem.startTime != "") {
                checkListItem.timespent = (System.currentTimeMillis() - checkListItem.startTime.toLong() + checkListItem.timespent.toLong()).toString()
            }//end if
        }//end btnProblems listener

        btnBackToList.setOnClickListener {
            finish()
            Log.d("TaskDetail", "Back to Task List")
            Log.d("TaskDetail", "Loading Task List")
            val intent = Intent(this, TaskList::class.java)
            intent.putExtra("visitId", visitId)
            startActivity(intent)
            Log.d("TaskDetail", "Loading Task List completed")
            Log.d("TaskDetail", "Loading Task Detail")
        }//end btnBackToList listener
    }//end onCreate function

}//end TaskDetail class
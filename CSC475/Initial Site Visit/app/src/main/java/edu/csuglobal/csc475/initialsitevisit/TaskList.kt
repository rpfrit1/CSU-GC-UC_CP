package edu.csuglobal.csc475.initialsitevisit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class TaskList : AppCompatActivity() {
    private lateinit var db: SiteVisitDatabase
    private lateinit var siteDao: SiteVisitDao
    private lateinit var checkListDao: CheckListItemDao
    private lateinit var checkTitleDao: ChecklistTitleDao
    private lateinit var checkListItems: List<CheckListItem>
    private var visitId: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        //log start of task list loading
        Log.d("TaskList", "task list loading")

        super.onCreate(savedInstanceState)
        setContentView(R.layout.task_list)

        //put a log that program is receiving data from the intent
        Log.d("TaskList", "Receiving from intent")

        visitId = intent.getIntExtra("visitId", 0)

        //log that visitId was received
        Log.d("TaskList", "visitId: $visitId received")

        //log data received data
        Log.d("TaskList", "Data received, Loading database instance")

        db = SiteVisitDatabase.getInstance(this)
        siteDao = db.siteVisitDao()
        checkListDao = db.checklistItemDao()
        checkTitleDao = db.checklistTitleDao()
        checkListItems = checkListDao.getChecklistItems(visitId)

        //Log database instance received, loading data into screen's RecyclerView
        Log.d("TaskList", "Loading data into screen's RecyclerView")

        val vwRecycle: RecyclerView = findViewById(R.id.vwRecycle)
        vwRecycle.adapter = TaskListAdapter(checkListItems, checkTitleDao)
        vwRecycle.visibility = View.VISIBLE
        vwRecycle.setHasFixedSize(true)
        vwRecycle.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        vwRecycle.setItemViewCacheSize(0)

        //log end of task list loading
        Log.d("TaskList", "task list loaded")

        //create a listener for btnVisit that returns to the main activity and log all actions
        findViewById<View>(R.id.btnVisit).setOnClickListener {
            Log.d("TaskList", "btnVisit clicked")
            finish()
            Log.d("TaskList", "btnVisit finished")
        }//end setOnClickListener


    }//end onCreate function
}//end TaskList class
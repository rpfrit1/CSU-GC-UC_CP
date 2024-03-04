package edu.csuglobal.csc475.initialsitevisit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView

class TaskList : AppCompatActivity() {
    private lateinit var db: SiteVisitDatabase
    private lateinit var siteDao: SiteVisitDao
    private lateinit var checkListDao: CheckListItemDao
    private lateinit var checkTitleDao: ChecklistTitleDao
    private lateinit var checkListItems: List<CheckListItem>
    private var visitId: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.task_list)

        visitId = intent.getIntExtra("visitId", 0)

        db = SiteVisitDatabase.getInstance(this)
        siteDao = db.siteVisitDao()
        checkListDao = db.checklistItemDao()
        checkTitleDao = db.checklistTitleDao()
        checkListItems = checkListDao.getChecklistItems(visitId)
        val vwRecycle: RecyclerView = findViewById(R.id.vwRecycle)
        vwRecycle.adapter = TaskListAdapter(checkListItems, checkTitleDao)
    }//end onCreate function
}//end TaskList class
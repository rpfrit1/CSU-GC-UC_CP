package edu.csuglobal.csc475.initialsitevisit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class TaskDetail : AppCompatActivity() {
<<<<<<< HEAD
    private lateinit var db: SiteVisitDatabase
=======
    private lateinit var db:SiteVisitDatabase
>>>>>>> origin/main
    private lateinit var checkListDao: CheckListItemDao
    private lateinit var checkTitleDao: ChecklistTitleDao
    private lateinit var checkListItem: CheckListItem
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.task_detail)
        db = SiteVisitDatabase.getInstance(this)
        checkListDao = db.checklistItemDao()
        checkTitleDao = db.checklistTitleDao()

        val lblTaskName = findViewById<TextView>(R.id.lblTaskName)
        val txtTimer = findViewById<EditText>(R.id.txtTimer)
        val txtNotes = findViewById<EditText>(R.id.txtNotes)
        val btnNa = findViewById<Button>(R.id.btnNa)
        val btnNotStarted = findViewById<Button>(R.id.btnNotStarted)
        val btnInProcess = findViewById<Button>(R.id.btnInProcess)
        val btnSuccess = findViewById<Button>(R.id.btnSuccess)
        val btnProblems = findViewById<Button>(R.id.btnProblems)
        val btnBackToList = findViewById<Button>(R.id.btnBackToList)

        //create a listener for btnNa that sets the state in
    }//end onCreate function

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }//end onSupportNavigateUp function

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }//end onBackPressed function

    override fun onPause() {
        super.onPause()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }//end onPause function

    override fun onResume() {
        super.onResume()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }//end onResume function

    override fun onStop() {
        super.onStop()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }//end onStop function

    override fun onDestroy() {
        super.onDestroy()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }//end onDestroy function

    override fun onRestart() {
        super.onRestart()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }//end onRestart function


}//end TaskDetail class
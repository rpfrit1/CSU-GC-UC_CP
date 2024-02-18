package edu.csuglobal.csc475.initialsitevisit

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import java.util.*

class MainActivity : AppCompatActivity() {//end MainActivity class

    private lateinit var txtSite: EditText
    private lateinit var txtDate: EditText
    private lateinit var txtStartTime: EditText
    private lateinit var txtEndTime: EditText
    private lateinit var btnDate:  ImageButton
    private lateinit var btnStartTime: ImageButton
    private lateinit var btnEndTime: ImageButton
    private lateinit var btnEmail: ImageButton
    private lateinit var btnToList: Button
    private lateinit var db: SiteVisitDatabase
    private lateinit var visit: SiteVisitDao
    private lateinit var title: ChecklistTitleDao
    private lateinit var item: CheckListItemDao
    private lateinit var titleList: List<ChecklistTitle>
    private lateinit var itemList: List<CheckListItem>
    private lateinit var siteVisit:SiteVisit
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        siteVisitSetup()
        //ask user for permission to manage external files so that Database can function
        requestPermissions(arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE), 0)

        dbInit()
        //set siteVisit to the visitId passed from Intent if it was passed by another activity
        if(intent.hasExtra("siteId")){
            siteVisit = db.siteVisitDao().getSiteVisit(intent.getIntExtra("siteVisit", 0))!!
        }//end if
        loadTitles()

        //format text for txtStartTime and txtEndTime to be HH:MM
        txtStartTime.setText("00:00")
        txtEndTime.setText("00:00")

        //set btnToList to go to list activity
        btnToList.setOnClickListener {
            updateSiteVisitObj()
            addVisit()
            val intent = Intent(this, TaskList::class.java)
            intent.putExtra("visitId", siteVisit.visitId)
            startActivity(intent)
        }//end btnToList setOnClickListener

        //set btnDate to select a date and set txtDate to value selected
        btnDate.setOnClickListener {
            val c = Calendar.getInstance()

            //create a datePickerDialog with today's date as a default
            val datePickerDialog = DatePickerDialog(
                this, { _, year, month, dayOfMonth -> txtDate.setText("$month/$dayOfMonth/$year") }//end setOnDateSetListener
                , c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH))

            datePickerDialog.show()//show datepicker
        }//end btnDate setOnClickListener

        //set btnStartTime to select a time and set txtStartTime to value selected
        btnStartTime.setOnClickListener {
            // create instance of calendar to manage the time settings
            val c = Calendar.getInstance()

            //create a timePickerDialog with defaults
            val timePickerDialog = TimePickerDialog(
                this,
                { _, hourOfDay, minute ->
                    // on below line we are setting selected
                    // time in our text view.
                    txtStartTime.setText("$hourOfDay:$minute")
                },
                c.get(Calendar.HOUR_OF_DAY),
                c.get(Calendar.MINUTE),
                true
            )
            timePickerDialog.show()//show timepicker
        }//end btnStartTime setOnClickListener

        //set btnEndTime to select a time and set txtEndTime to value selected
        btnEndTime.setOnClickListener {
            // create instance of calendar to manage the time settings
            val c = Calendar.getInstance()

            //create a timePickerDialog with defaults
            val timePickerDialog = TimePickerDialog(
                this,
                { _, hourOfDay, minute ->
                    // on below line we are setting selected
                    // time in our text view.
                    txtStartTime.setText("$hourOfDay:$minute")
                },
                c.get(Calendar.HOUR_OF_DAY),
                c.get(Calendar.MINUTE),
                true
            )
            timePickerDialog.show()//show timepicker
        }//end btnEndTime setOnClickListener

        //set listener for btnEmail to send email
        btnEmail.setOnClickListener {
            updateSiteVisitObj()
            addVisit()
            val emailIntent = Intent(Intent.ACTION_SEND)
            emailIntent.type = "text/plain"
            emailIntent.putExtra(Intent.EXTRA_EMAIL, "richard.fritsche@csuglobal.edu")
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Site Visit to ${siteVisit.siteName} on ${siteVisit.visitDate}")
            var body = siteVisit.toString()
            //add each checklist item with a title from checklistTitle to the body
            for (title in titleList) {
                body += "\n\tChecklist Item: ${title.title}"
                for (item in itemList) {
                    if (item.checkListItem == title.titleId) {
                        body += "\n\tStatus: ${item.checkListItemState}\n\tTime Spent: ${item.timespent}\n\tNotes: ${item.checkListComments}\n"
                        break
                    }//end if
                }//end for loop
            }//end for loop
            emailIntent.putExtra(Intent.EXTRA_TEXT, body)
            startActivity(Intent.createChooser(emailIntent, "Send Email"))
        }//end btnEmail setOnClickListener

    }//end onCreate function

    private fun updateSiteVisitObj() {
        //populate details of the site visit to siteVisit
        siteVisit.siteName = txtSite.text.toString()
        siteVisit.visitDate = txtDate.text.toString()
        siteVisit.visitStartTime = txtStartTime.text.toString()
        siteVisit.visitEndTime = txtEndTime.text.toString()
    }//end updateSiteVisitObj function

    private fun addVisit() {
        //If this site visit is not in the database, add site visit to database
        if (visit.getSiteVisit(siteVisit.siteName, siteVisit.visitDate) == null && visit.getSiteVisit(siteVisit.visitId) == null) {
            visit.insert(SiteVisit(0, txtSite.text.toString(), txtDate.text.toString(), txtStartTime.text.toString(), txtEndTime.text.toString()))
            //create ChecklistItems in the database for each ChecklistTitle in the Checklist
            addChecklistItems()
        }//end if
        else
        {
            visit.updateSiteVisit(SiteVisit(siteVisit.visitId, siteVisit.siteName, siteVisit.visitDate, siteVisit.visitStartTime, siteVisit.visitEndTime))
        }//end else
    }//end addVisit function


    private fun addChecklistItems() {
        //create ChecklistItems in the database for each ChecklistTitle in the Checklist
        val chkTitles = db.checklistTitleDao().getAllChecklistTitles()
        for (title in chkTitles) {
            db.checklistItemDao().insert(CheckListItem(0, siteVisit.visitId, title.titleId))
        }//end for loop
    }//end addChecklistItems function

    private fun addChecklistTitles() {
        //If they don't already exist, create a ChecklistTitle in the database for "Inspect the MDF", "Inspect the IDFs", "Talk with Site Leardership about issues", "Check for RMA returns", "Check for needed RMAs", "Check Wireless APs"
        if (db.checklistTitleDao().getChecklistTitle("Inspect the MDF") == null) {
            db.checklistTitleDao().insert(ChecklistTitle(0, "Inspect the MDF"))
        }//end if
        if (db.checklistTitleDao().getChecklistTitle("Inspect the IDFs") == null) {
            db.checklistTitleDao().insert(ChecklistTitle(0, "Inspect the IDFs"))
        }//end if
        if (db.checklistTitleDao()
                .getChecklistTitle("Talk with Site Leadership about issues") == null
        ) {
            db.checklistTitleDao()
                .insert(ChecklistTitle(0, "Talk with Site Leadership about issues"))
        }//end if
    }//end addChecklistTitles function

    private fun siteVisitSetup() {

        setContentView(R.layout.activity_main)
        txtSite = findViewById<EditText>(R.id.txtSite)
        txtDate = findViewById<EditText>(R.id.txtDate)
        txtStartTime = findViewById<EditText>(R.id.txtStartTime)
        txtEndTime = findViewById<EditText>(R.id.txtEndTime)
        btnDate = findViewById<ImageButton>(R.id.btnDate)
        btnStartTime = findViewById<ImageButton>(R.id.btnStartTime)
        btnEndTime = findViewById<ImageButton>(R.id.btnEndTime)
        btnEmail = findViewById<ImageButton>(R.id.btnEmail)
        btnToList = findViewById<Button>(R.id.btnToList)
    }

    private fun dbInit() {

        db = SiteVisitDatabase.getInstance(this)
        visit = db.siteVisitDao()
        siteVisit = SiteVisit()
        title = db.checklistTitleDao()
        item = db.checklistItemDao()
        titleList = title.getAllChecklistTitles()
        itemList = item.getAllChecklistItems()
    }//end dbInit function

    private fun loadTitles() {
        //If they don't already exist, create a ChecklistTitle in the database for "Inspect the MDF", "Inspect the IDFs", "Talk with Site Leardership about issues", "Check for RMA returns", "Check for needed RMAs", "Check Wireless APs"
        if (db.checklistTitleDao().getChecklistTitle("Inspect the MDF") == null) {
            db.checklistTitleDao().insert(ChecklistTitle(0, "Inspect the MDF"))
        }//end if
        if (db.checklistTitleDao().getChecklistTitle("Inspect the IDFs") == null) {
            db.checklistTitleDao().insert(ChecklistTitle(0, "Inspect the IDFs"))
        }//end if
        if (db.checklistTitleDao().getChecklistTitle("Talk with Site Leadership about issues") == null) {
            db.checklistTitleDao().insert(ChecklistTitle(0, "Talk with Site Leadership about issues"))
        }//end if
        if (db.checklistTitleDao().getChecklistTitle("Check for RMA returns") == null) {
            db.checklistTitleDao().insert(ChecklistTitle(0, "Check for RMA returns"))
        }//end if
        if (db.checklistTitleDao().getChecklistTitle("Check for needed RMAs") == null) {
            db.checklistTitleDao().insert(ChecklistTitle(0, "Check for needed RMAs"))
        }//end if
        if (db.checklistTitleDao().getChecklistTitle("Check Wireless APs") == null) {
            db.checklistTitleDao().insert(ChecklistTitle(0, "Check Wireless APs"))
        }//end if
    }//end loadTitles function

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        finishAffinity()
    }//end onSaveInstanceState function

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        finishAffinity()
    }//end onRestoreInstanceState function

}//end MainActivity class
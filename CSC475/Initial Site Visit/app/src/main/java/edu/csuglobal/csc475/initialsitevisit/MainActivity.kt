package edu.csuglobal.csc475.initialsitevisit


import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.util.Log
import java.util.*

class MainActivity : AppCompatActivity() {//end MainActivity class

    private lateinit var txtSite: EditText
    private lateinit var txtDate: EditText
    private lateinit var txtStartTime: EditText
    private lateinit var txtEndTime: EditText
    private lateinit var btnDate: ImageButton
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
    private lateinit var siteVisit: SiteVisit

    override fun onCreate(savedInstanceState: Bundle?) {
        //log a message that system main screen is loading
        Log.d("MainActivity", "main screen loading")

        super.onCreate(savedInstanceState)
        siteVisitSetup()
        //ask user for permission to manage external files so that Database can function
        requestPermissions(arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE), 0)

        dbInit()
        //set siteVisit to the visitId passed from Intent if it was passed by another activity
        if (intent.hasExtra("siteId")) {
            //log a message that siteId was passed
            Log.d("MainActivity", "siteId passed")

            siteVisit = db.siteVisitDao().getSiteVisit(intent.getIntExtra("siteVisit", 0))!!

            //log a message that siteVisit was found
            Log.d("MainActivity", "siteVisit found")
        }//end if

        loadTitles()

        //set btnToList to go to list activity
        btnToList.setOnClickListener {
            //log a message that btnToList was clicked
            Log.d("MainActivity", "btnToList clicked")

            updateSiteVisitObj()
            addVisit()
            //log a message stating that passing visitId to the intent, include the visitId
            Log.d("MainActivity", "passing visitId: ${siteVisit.visitId} to intent")
            intent = Intent(this, TaskList::class.java)
            intent.putExtra("visitId", siteVisit.visitId)
            //log site visit added to intent and passing to intent
            Log.d("MainActivity", "siteVisit added to intent")
            startActivity(intent)
        }//end btnToList setOnClickListener

        //set btnDate to select a date and set txtDate to value selected
        btnDate.setOnClickListener {
            //log that date selector was clicked
            Log.d("MainActivity", "date selector clicked")
            val c = Calendar.getInstance()

            //create a datePickerDialog with today's date as a default
            val datePickerDialog = DatePickerDialog(
                this, { _, year, month, dayOfMonth -> txtDate.setText("$month/$dayOfMonth/$year") }//end setOnDateSetListener
                , c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH))
            c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH) + 1, c.get(Calendar.DAY_OF_MONTH))
            datePickerDialog.show()//show datepicker

            //log that date selector was shown
            Log.d("MainActivity", "date selector shown")
        }//end btnDate setOnClickListener

        //set btnStartTime to select a time and set txtStartTime to value selected
        btnStartTime.setOnClickListener {
            // log that start time selector was clicked
            Log.d("MainActivity", "start time selector clicked")

            // create instance of calendar to manage the time settings
            val c = Calendar.getInstance()

            //create a timePickerDialog with defaults
            val timePickerDialog = TimePickerDialog(this, { _, hourOfDay, minute -> txtStartTime.setText("$hourOfDay:$minute")}, c.get(Calendar.HOUR_OF_DAY),c.get(Calendar.MINUTE),true)
            timePickerDialog.show()//show timepicker

            // log that start time selector was shown
            Log.d("MainActivity", "start time selector shown")
        }//end btnStartTime setOnClickListener

        //set btnEndTime to select a time and set txtEndTime to value selected
        btnEndTime.setOnClickListener {
            // log that end time selector was clicked
            Log.d("MainActivity", "end time selector clicked")

            // create instance of calendar to manage the time settings
            val c = Calendar.getInstance()

            //create a timePickerDialog with defaults
            val timePickerDialog = TimePickerDialog(this,{ _, hourOfDay, minute -> txtStartTime.setText("$hourOfDay:$minute") }, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), true)
            timePickerDialog.show()//show timepicker

            // log that end time selector was shown
            Log.d("MainActivity", "end time selector shown")
        }//end btnEndTime setOnClickListener

        //set listener for btnEmail to send email
        btnEmail.setOnClickListener {
            //log that btnEmail was clicked
            Log.d("MainActivity", "btnEmail clicked")

            updateSiteVisitObj()
            addVisit()
            val emailIntent = Intent(Intent.ACTION_SEND)
            emailIntent.type = "text/plain"
            emailIntent.putExtra(Intent.EXTRA_EMAIL, "richard.fritsche@csuglobal.edu")
            emailIntent.putExtra(Intent.EXTRA_SUBJECT,"Site Visit to ${siteVisit.siteName} on ${siteVisit.visitDate}")
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
            //log that email
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
        //log a message starting to add the visit to the database
        Log.d("MainActivity", "Starting to add the visit to the database")

        //If this site visit is not in the database, add site visit to database
        if (visit.getSiteVisit(siteVisit.siteName, siteVisit.visitDate) == null || visit.getSiteVisit(siteVisit.visitId) == null) {
            //log a message stating that the visit was not already in the database
            Log.d("MainActivity", "Visit was not already in the database")

            visit.insert(
                SiteVisit(
                    0,
                    txtSite.text.toString(),
                    txtDate.text.toString(),
                    txtStartTime.text.toString(),
                    txtEndTime.text.toString()
                )
            )
            //create ChecklistItems in the database for each ChecklistTitle in the Checklist
            addChecklistItems()
            //log a message stating that the visit was added to the database
            Log.d("MainActivity", "Visit was added to the database")
        }//end if
        else {
            //log a message stating that the visit was already in the database
            Log.d("MainActivity", "Visit was already in the database, updating the visit")

            visit.updateSiteVisit(SiteVisit(siteVisit.visitId,siteVisit.siteName,siteVisit.visitDate,siteVisit.visitStartTime,siteVisit.visitEndTime))

            //log a message stating that the visit was updated
            Log.d("MainActivity", "Visit was updated")
        }//end else
        //log a message stating that the visit was added to the database
        Log.d("MainActivity", "Visit was added to the database")
    }//end addVisit function


    private fun addChecklistItems() {
        //log a message stating that starting to add checklist items to the site visit
        Log.d("MainActivity", "Starting to add checklist items to the site visit")

        //create ChecklistItems in the database for each ChecklistTitle in the Checklist
        val chkTitles = db.checklistTitleDao().getAllChecklistTitles()
        for (title in chkTitles) {
            //log a message stating which checklist item is being added by title
            Log.d("MainActivity", "Adding checklist item: ${title.title}")

            db.checklistItemDao().insert(CheckListItem(0, siteVisit.visitId, title.titleId))

            //log a message stating that the checklist item has been added
            Log.d("MainActivity", "Checklist item: ${title.title} added")
        }//end for loop

        //log a message stating that all checklist items have been added
        Log.d("MainActivity", "All checklist items have been added")

        //log a message stating that starting to load titles
        Log.d("MainActivity", "Starting to load titles")

        //If they don't already exist, create a ChecklistTitle in the database for "Inspect the MDF", "Inspect the IDFs", "Talk with Site Leardership about issues", "Check for RMA returns", "Check for needed RMAs", "Check Wireless APs"
        if (db.checklistTitleDao().getChecklistTitle("Inspect the MDF") == null) {
            db.checklistTitleDao().insert(ChecklistTitle(0, "Inspect the MDF"))
        }
    }//end addChecklistItems function

    private fun siteVisitSetup() {
        //put a message in the log stating that starting to setup the site visit
        Log.d("MainActivity", "Starting to setup the site visit")

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

        //log completion of setup
        Log.d("MainActivity", "Setup complete")
    }//end siteVisitSetup function

    private fun dbInit() {
        //put a message in the log stating that starting to initialize the database
        Log.d("MainActivity", "Starting to initialize the database")

        //get a reference to the SiteVisitDatabase
        db = SiteVisitDatabase.getInstance(this)
        visit = db.siteVisitDao()
        siteVisit = SiteVisit()
        title = db.checklistTitleDao()
        item = db.checklistItemDao()
        titleList = title.getAllChecklistTitles()
        itemList = item.getAllChecklistItems()
        loadTitles()

        //log completion of initialization
        Log.d("MainActivity", "Database initialized")
    }//end dbInit function

    private fun loadTitles() {
        //put a message in the log stating that starting to load titles
        Log.d("MainActivity", "Starting to load titles")

        //If they don't already exist, create a ChecklistTitle in the database for "Inspect the MDF", "Inspect the IDFs", "Talk with Site Leardership about issues", "Check for RMA returns", "Check for needed RMAs", "Check Wireless APs"
        if (db.checklistTitleDao().getChecklistTitle("Inspect the MDF") == null) {
            db.checklistTitleDao().insert(ChecklistTitle(0, "Inspect the MDF"))
        }//end if
        if (db.checklistTitleDao().getChecklistTitle("Inspect the IDFs") == null) {
            db.checklistTitleDao().insert(ChecklistTitle(0, "Inspect the IDFs"))
        }//end if
        if (db.checklistTitleDao().
            getChecklistTitle("Talk with Site Leadership about issues") == null) {
            db.checklistTitleDao().
            insert(ChecklistTitle(0, "Talk with Site Leadership about issues"))
        }//end if
        if (db.checklistTitleDao().
            getChecklistTitle("Talk with Site Leadership about issues") == null) {
            db.checklistTitleDao()
                .insert(ChecklistTitle(0, "Talk with Site Leadership about issues"))
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

        //log completion of loading titles
        Log.d("MainActivity", "Titles loaded")
    }//end loadTitles function
}//end MainActivity class